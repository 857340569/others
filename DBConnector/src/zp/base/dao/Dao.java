package zp.base.dao;

import java.util.List;

public abstract class Dao {
	/**
	 * 根据条件查询
	 * @param claz
	 * @param sql
	 * @param paramVals 条件对应的值
	 */
	public abstract <T> List<T> query(Class<T> claz,String sql);
	public abstract <T> List<T> query(Class<T> claz,String sql,String[] paramVals);
	public abstract <T> T querySingle(Class<T> claz,String sql,String[] paramVals);
	public abstract <T>  boolean update(Class<T> claz,String sql,String[] paramVals);
	
}
