package zp.base.utils;

import zp.base.utils.SqlHelper.DbType;

public class DbConfig {
	private static DbType currentDBType=DbType.MYSQL;
	private static String dbUrl="localhost:3306";
	private static String dbName="hr";
	private static String userName="root";
	private static String pwd="123654";
	private static boolean isInited=false;
	public  static void config(DbType currentDBType,String dbUrl,String dbName,String userName,String pwd)
	{
		if(!isInited)
		{
			DbConfig.currentDBType=currentDBType;
			DbConfig.dbUrl=dbUrl;
			DbConfig.dbName=dbName;
			DbConfig.userName=userName;
			DbConfig.pwd=pwd;
			isInited=true;
		}
	}
	public static DbType getCurrentDBType() {
		return currentDBType;
	}
	public static String getDbUrl() {
		return dbUrl;
	}
	public static String getDbName() {
		return dbName;
	}
	public static String getUserName() {
		return userName;
	}
	public static String getPwd() {
		return pwd;
	}
	public static boolean isInited() {
		return isInited;
	}
	
	
}
