package com.hnn.cd.action;

import com.hnn.cd.bean.User;
import com.hnn.cd.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends BaseAction{
	private UserService userService;
	private User user;
	public LoginAction()
	{
		userService=new UserService();
	}
	public String login() throws Exception{
		
		user=userService.queryById("1");
		return "json";
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
