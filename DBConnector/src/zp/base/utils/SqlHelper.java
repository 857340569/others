package zp.base.utils;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import zp.base.bean.UpdateParam;


public class SqlHelper
{
	private Connection cc;
	private PreparedStatement ps;
	private ResultSet rs;
	public enum DbType
	{
		MYSQL,SQLSERVER,JDBCODBC
	}
	public SqlHelper() {
		if (!DbConfig.isInited()) {
			System.err.println(new Exception("还未配置数据库！"));
			return;
		}
		switch (DbConfig.getCurrentDBType()) {
		case MYSQL:
			mySqlConnect();
			break;
		case SQLSERVER:
			sqlServerConnect();	
			break;
		case JDBCODBC:
			odbcConnect();
			break;
		default:
			break;
		}
	}
	
	
	private void mySqlConnect()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			cc=DriverManager.getConnection("jdbc:mysql://"+DbConfig.getDbUrl()+"/"+DbConfig.getDbName()+"?useUnicode=true&characterEncoding=UTF-8",DbConfig.getUserName(),DbConfig.getPwd());
		} catch (Exception e)
		{
			e.printStackTrace();
		} 
	}
	
	private void sqlServerConnect()
	{
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			cc=DriverManager.getConnection("jdbc:sqlserver://"+DbConfig.getDbUrl()+";DatabaseName="+DbConfig.getDbName()+"?useUnicode=true&characterEncoding=UTF-8",DbConfig.getUserName(),DbConfig.getPwd());
		} catch (Exception e)
		{
			e.printStackTrace();
		} 
	}
	private void odbcConnect()
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			cc=DriverManager.getConnection("jdbc:odbc:"+DbConfig.getDbName(),DbConfig.getUserName(),DbConfig.getPwd());
		} catch (Exception e)
		{
			e.printStackTrace();
		} 
	}
	
	public void close()
	{
		try
		{
			if (rs!=null)
			{
				rs.close();
			}
			if (ps!=null)
			{
				ps.close();
			}
			if (cc!=null)
			{
				cc.close();
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public ResultSet findExecute(String sql,String [] param)
	{
		if (param==null||param.length==0)
		{
			try
			{
				ps=cc.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY );
				rs=ps.executeQuery();
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
			
		}
		else {
			try
			{
				ps=cc.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY );
				for (int i = 0; i < param.length; i++)
				{
					ps.setString(i+1, param[i]);
				}
				rs=ps.executeQuery();
			
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		
		return  rs;
	}
	public int updateExecute(String sql,String [] param)
	{
		int result=0;
		if (param.length==0)
		{
			try
			{
				ps=cc.prepareStatement(sql);
				result=ps.executeUpdate();
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
			finally
			{
				this.close();
			}
		}
		else {
			try
			{
				ps=cc.prepareStatement(sql);
				for (int i = 0; i < param.length; i++)
				{
					ps.setString(i+1, param[i]);
				}
				result=ps.executeUpdate();
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
			finally
			{
				this.close();
			}
		}
		return result;
	}
	
	public static <T> T getBeanFromDb(T t,ResultSet set)
	{
		if(t!=null&&set!=null)
		{
			Class<?> tClass=t.getClass();
			Field[] fields = tClass.getDeclaredFields();
			for(Field field:fields)
			{
				field.setAccessible(true);
				String fieldName=field.getName();
				
				try{
					Class<?> fClass=field.getType();
					Object value=set.getObject(fieldName);
					Method method=tClass.getMethod("set"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1), fClass);
					method.setAccessible(true);
					method.invoke(t, value);
				}catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}
		return t;
	}
	/**
	 * 根据变量名，得到实体类某个变量的值
	 * @param t
	 * @param fieldName
	 * @return
	 */
	public static <T> String getFieldVal(T t,String fieldName)
	{
		Class<?> tClass=t.getClass();
		try {
			Field field = tClass.getDeclaredField(fieldName);
			field.setAccessible(true);
			return String.valueOf(field.get(t));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	public enum UpdateType
	{
		ADD,MODIFY
	}
	/**
	 * 得到更新参数
	 * updateType:ADD如：(name,sex) values(?,?);
	 * MODIFY如：name=?,sex=?
	 * @param updateType
	 * @param t
	 * @param ignoreFieldName
	 * @return
	 */
	public static <T> UpdateParam getUpdateParam(UpdateType updateType,T t,String ...ignoreFieldName)
	{
		Class<?> tClass=t.getClass();
		Field[] fields = tClass.getDeclaredFields();
		String paramNames="";
		String paramSplit="";
		String addValPara="values(";
		if(updateType==UpdateType.ADD)
		{
			paramNames="(";
			paramSplit=",";
		}else
		{
			paramSplit="=?,";
		}
		List<String> paramValues=new ArrayList<String>();
		for(Field field:fields)
		{
			field.setAccessible(true);
			String fieldName=field.getName();
			int index=CollectionUtils.arrayIndexOf(ignoreFieldName, fieldName);
			if(index!=-1)
			{
				continue;
			}
			try {
				paramValues.add(String.valueOf(field.get(t)));
				paramNames+=fieldName+paramSplit;
				addValPara+="?,";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(updateType==UpdateType.ADD)
		{
			paramNames=StringUtils.trimEnd(paramNames, ",")+")"+StringUtils.trimEnd(addValPara, ",")+")";
		}else{
			paramNames=StringUtils.trimEnd(paramNames, ",");
		}
		UpdateParam param=new UpdateParam();
		param.setParamNames(paramNames);
		param.setParamVals(paramValues);
		return param;
	}
	
}
