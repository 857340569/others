package zp.base.utils;

public class StringUtils {
	public static boolean isEmpty(String str)
	{
		if(str==null||str.trim().equals(""))
		{
			return true;
		}
		return false;
	}
	
	public static boolean isEmpty(String ... strs)
	{
		if(strs.length==0)
		{
			return true;
		}
		for(int i=0;i<strs.length;i++)
		{
			if (isEmpty(strs[i])) {
				return true;
			}
		}
		
		return false;
	}
	/**
	 * È¥³ý×Ö·û´®Î²²¿Ö¸¶¨×Ö·û´®
	 * @param str
	 * @param prefix
	 * @return
	 */
	public static String trimEnd(String str,String prefix)
	{
		if(isEmpty(str))
			return "";
		try {
			return str.substring(0,str.lastIndexOf(prefix));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
}
