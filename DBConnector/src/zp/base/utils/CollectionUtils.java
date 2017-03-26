package zp.base.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CollectionUtils {
	/**
	 * 判断集合是否为空
	 * @param collection
	 * @return
	 */
	public static <T> boolean isEmpty(Collection<T> collection)
	{
		return collection==null||collection.size()==0;
	}
	
	/**
	 * 从数组中查找
	 * @param tArray
	 * @param t
	 * @return
	 */
	public static <T> int arrayIndexOf(T[] tArray,T t)
	{
		if(tArray==null) return -1;
		List<T> ts=Arrays.asList(tArray);
		return ts.indexOf(t);
	}
	
	/**
	 * 将集合转换成数组
	 * @param list
	 * @return
	 */
	public static <T> T[] listToArray(List<T> list)
	{
		if(list==null) return null;
		//List如此设计是因为Java编译器不允许我们new范型数组。不能这么定义一个数组：
		//T arr=new T[list.size()];
		T[] t =  (T[])java.lang.reflect.Array.
				newInstance(list.get(0).getClass().getComponentType(), list.size());
		return list.toArray(t);
	}
}
