package zp.base.utils;


import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import zp.base.bean.ExecuteParam;


public class SqlHelper
{
	private Connection cc;
	private PreparedStatement ps;
	private ResultSet rs;
    private final DataSource dataSource;
	private SqlHelper(String db_config_name) {
		// dataSource资源只能初始化一次 c3p0-config.xml <named-config name="db_catch_doll"> 
		dataSource = new ComboPooledDataSource(db_config_name);
	}
	public static String db_config_name="";
	public static SqlHelper getInstance()
	{
		if(StringUtils.isEmpty(db_config_name))
		{
			System.err.println("请配置SqlHelper.db_config_name");
		}
		return InstanceTools.sqlHelper;
	}
	
	private static class InstanceTools{
		private final static SqlHelper sqlHelper=new SqlHelper(db_config_name);
	}
	
	public void prepareTransaction(){
		if(cc!=null)
		{
			try {
				cc.setAutoCommit(false);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void commitTransaction(){
		if(cc!=null)
		{
			try {
				cc.setAutoCommit(true);
				cc.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
	public void close(ResultSet rs)
	{
		this.rs=rs;
		close();
	}
	public ResultSet findExecute(String sql,String [] param)
	{
		if (param==null||param.length==0)
		{
			try
			{
				cc=dataSource.getConnection();
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
				cc=dataSource.getConnection();
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
		if (param==null||param.length==0)
		{
			try
			{
				cc=dataSource.getConnection();
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
				cc=dataSource.getConnection();
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
	/**
	 * 从数据库记录ResultSet中得到实体对象
	 * @param tClass
	 * @param set
	 * @return
	 */
	public static <T> T getBeanFromDb(Class<T> tClass,ResultSet set)
	{
		if(tClass!=null&&set!=null)
		{
			try {
				T t=tClass.newInstance();
				ResultSetMetaData metaData = set.getMetaData();
				int columnCount=metaData.getColumnCount();
				for (int i = 1; i <=columnCount; i++) {
					try{
						String labelName=metaData.getColumnName(i);
						Field field=tClass.getDeclaredField(labelName);
						field.setAccessible(true);
						Object value=set.getObject(labelName);
//						Class<?> fClass=field.getType();
//						Method method=tClass.getMethod("set"+labelName.substring(0, 1).toUpperCase()+labelName.substring(1), fClass);
//						method.setAccessible(true);
//						method.invoke(t, value);
						field.set(t, String.valueOf(value));
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
				return t;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return null;
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
	public enum ExecuteType
	{
		QUERY,ADD,MODIFY
	}
	/**
	 * 得到更新参数
	 * executeType：ADD如：(name,sex) values(?,?);
	 * MODIFY 如：name=?,sex=?
	 * QUERY 如：name,sex
	 * @param executeType
	 * @param t
	 * @param ignoreFieldName
	 * @return
	 */
	public static <T> ExecuteParam getUpdateParam(ExecuteType executeType,T t,String ...ignoreFieldName)
	{
		Class<?> tClass=t.getClass();
		Field[] fields = tClass.getDeclaredFields();
		String paramNames="";
		String paramSplit="";
		String addValPara="values(";
		if(executeType==ExecuteType.QUERY)
		{
			paramSplit=",";
		}
		else if(executeType==ExecuteType.ADD)
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
				//此字段已忽略，跳过此次循环
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
		if(executeType==ExecuteType.ADD)
		{
			paramNames=StringUtils.trimEnd(paramNames, ",")+")"+StringUtils.trimEnd(addValPara, ",")+")";
		}else{//QUERY和MODIFY 都一样
			paramNames=StringUtils.trimEnd(paramNames, ",");
		}
		ExecuteParam param=new ExecuteParam();
		param.setParamNames(paramNames);
		param.setParamVals(paramValues);
		return param;
	}
	
}
