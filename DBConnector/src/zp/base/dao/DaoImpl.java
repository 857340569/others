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
		try {
			SqlHelper helper=new SqlHelper();
			ResultSet set=helper.findExecute(sql, paramVals);
			while(set.next())
			{
				T t=SqlHelper.getBeanFromDb(claz,set);
				users.add(t);
			}
		} catch (Exception e) {
		}
		return users;
	}

	@Override
	public <T> T querySingle(Class<T> claz, String sql, String[] paramVals) {
		try {
			SqlHelper helper=new SqlHelper();
			ResultSet set=helper.findExecute(sql,paramVals);
			if(set.next())
			{
				T t=SqlHelper.getBeanFromDb(claz,set);
				return t;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public <T> boolean update(Class<T> claz, String sql, String[] paramVals) {
		SqlHelper helper=new SqlHelper();
		//result　表示执行成功的条数，大于零说明执行成功
		int result=helper.updateExecute(sql,paramVals);
		return result>0;
	}

	
	
}
