package zp.base.dao;

import java.util.List;

public abstract class Dao {
	/**
	 * ����������ѯ
	 * @param claz
	 * @param sql
	 * @param paramVals ������Ӧ��ֵ
	 */
	public abstract <T> List<T> query(Class<T> claz,String sql);
	public abstract <T> List<T> query(Class<T> claz,String sql,String[] paramVals);
	public abstract <T> T querySingle(Class<T> claz,String sql,String[] paramVals);
	public abstract <T>  boolean update(Class<T> claz,String sql,String[] paramVals);
	
}
