package com.hnn.cd.action;

import com.opensymphony.xwork2.ActionSupport;

import zp.base.utils.SqlHelper;

public class BaseAction extends ActionSupport{

	public BaseAction() {
		SqlHelper.db_config_name="db_catch_doll";
//		DbConfig.dbConfigName="db_catch_doll";
//		use c3p0-config.xml
//		DbConfig.config(DbType.MYSQL, "localhost:3306", "catchdoll", "root", "123654");;
	}
}
