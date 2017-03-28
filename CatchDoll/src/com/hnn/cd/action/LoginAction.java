package com.hnn.cd.action;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.hnn.cd.bean.User;
import com.hnn.cd.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends BaseAction{
	private UserService userService;
	private User user;
	private List<User> users;
	private int count=0;
	public LoginAction()
	{
		userService=new UserService();
	}
	public String login() throws Exception{
		users=new ArrayList<>();
		for (int i = 0; i < count; i++) {
			User user=userService.queryById("1");
			users.add(user);
		}
		return "json";
	}
	public List<User> getUsers() {
		return users;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
