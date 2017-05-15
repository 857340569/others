package zp.base.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import zp.base.utils.SqlHelper;

public class DaoImpl extends Dao {

	@Override
	public <T> List<T> query(Class<T> claz, String sql) {
		return query(claz, sql,null);
	}

	@Override
	public <T> List<T> query(Class<T> claz, String sql, String[] paramVals) {
		List<T> users=new ArrayList<T>();
		SqlHelper helper=SqlHelper.getInstance();
		ResultSet set=null;
		try {
			set=helper.findExecute(sql,paramVals);
			while(set.next())
			{
				T t=SqlHelper.getBeanFromDb(claz,set);
				users.add(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			helper.close(set);
		}
		return users;
	}

	@Override
	public <T> T querySingle(Class<T> claz, String sql, String[] paramVals) {
		SqlHelper helper=SqlHelper.getInstance();
		ResultSet set=null;
		try {
			set=helper.findExecute(sql,paramVals);
			if(set.next())
			{
				T t=SqlHelper.getBeanFromDb(claz,set);
				return t;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			helper.close(set);
		}
		return null;
	}

	@Override
	public <T> boolean update(Class<T> claz, String sql, String[] paramVals) {
		SqlHelper helper=SqlHelper.getInstance();
		//result　表示执行成功的条数，大于零说明执行成功
		int result=helper.updateExecute(sql,paramVals);
		helper.close();
		return result>0;
	}
	
}
