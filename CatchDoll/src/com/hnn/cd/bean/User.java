package com.hnn.cd.bean;

/**
 * 用户表
 */
public class User {
	private int id;
	private String nickName;
	private String tel;
	private String thirdAccount;
	private String email;
	private String pwd;
	private double account;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getThirdAccount() {
		return thirdAccount;
	}
	public void setThirdAccount(String thirdAccount) {
		this.thirdAccount = thirdAccount;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public double getAccount() {
		return account;
	}
	public void setAccount(double account) {
		this.account = account;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", nickName=" + nickName + ", tel=" + tel + ", thirdAccount=" + thirdAccount
				+ ", email=" + email + ", pwd=" + pwd + ", account=" + account + "]";
	}
	
}
