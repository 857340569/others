package com.hnn.cd.action;

import com.opensymphony.xwork2.ActionSupport;

import zp.base.utils.DbConfig;
import zp.base.utils.SqlHelper.DbType;

public class BaseAction extends ActionSupport{

	public BaseAction() {
		DbConfig.config(DbType.MYSQL, "localhost:3306", "catchdoll", "root", "123654");;
	}
}
