package com.hnn.cd.service;


import java.util.List;

import com.hnn.cd.bean.User;

import zp.base.bean.ExecuteParam;
import zp.base.dao.DaoImpl;
import zp.base.service.Service;
import zp.base.utils.CollectionUtils;
import zp.base.utils.SqlHelper;
import zp.base.utils.SqlHelper.ExecuteType;

public class UserService extends Service<User>{

	@Override
	public List<User> query() {
		DaoImpl impl=new DaoImpl();
		String sql="select "+SqlHelper.getUpdateParam(ExecuteType.QUERY, new User(), "pwd").getParamNames()+" from duser";
		return impl.query(User.class, sql);
	}

	@Override
	public User queryById(String id) {
		DaoImpl impl=new DaoImpl();
		String sql="select "+SqlHelper.getUpdateParam(ExecuteType.QUERY, new User(), "pwd").getParamNames()+" from duser where id=?";
		return impl.querySingle(User.class, sql, new String[]{id});
	}

	@Override
	public boolean delete(User t) {
		DaoImpl impl=new DaoImpl();
		String sql="delete from duser where id=?";
		return impl.update(User.class, sql, new String[]{t.getId()+""});
	}

	@Override
	public boolean add(User t) {
		DaoImpl impl=new DaoImpl();
		ExecuteParam param=SqlHelper.getUpdateParam(ExecuteType.ADD,User.class, "id");
		String sql="insert into duser"+param.getParamNames();
		List<String> paramValues=param.getParamVals();
		return impl.update(User.class, sql, CollectionUtils.listToArray(paramValues));
	}

	@Override
	public boolean update(User t) {
		DaoImpl impl=new DaoImpl();
		ExecuteParam param=SqlHelper.getUpdateParam(ExecuteType.MODIFY,User.class, "id");
		String sql="update duser set "+param.getParamNames()+" where id=?";
		List<String> paramValues=param.getParamVals();
		paramValues.add(t.getId()+"");
		return impl.update(User.class, sql, CollectionUtils.listToArray(paramValues));
	}
}
