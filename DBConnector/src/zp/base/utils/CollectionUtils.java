package zp.base.utils;

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
	 * 将String集合转换成数组
	 * @param list
	 * @return
	 */
	public static String[] listToArray(List<String> list)
	{
		String[] strArray=new String[list.size()];
		return list.toArray(strArray);
	}
	
}
