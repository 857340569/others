package zp.base.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CollectionUtils {
	/**
	 * �жϼ����Ƿ�Ϊ��
	 * @param collection
	 * @return
	 */
	public static <T> boolean isEmpty(Collection<T> collection)
	{
		return collection==null||collection.size()==0;
	}
	
	/**
	 * �������в���
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
	 * ������ת��������
	 * @param list
	 * @return
	 */
	public static <T> T[] listToArray(List<T> list)
	{
		if(list==null) return null;
		//List����������ΪJava����������������new�������顣������ô����һ�����飺
		//T arr=new T[list.size()];
		T[] t =  (T[])java.lang.reflect.Array.
				newInstance(list.get(0).getClass().getComponentType(), list.size());
		return list.toArray(t);
	}
}
