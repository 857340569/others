package zp.base.service;

import java.util.List;

public abstract class Service<T> {
	public abstract List<T> query();

	public abstract T queryById(String id);

	public abstract boolean delete(T t);

	public abstract boolean add(T t);

	public abstract boolean update(T t);

}
