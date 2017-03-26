package com.hnn.cd.bean;

public class Order {
	private int id;
	private String orderNo;
	private int userId;
	private String revAddress;
	private int orderStatus;
	private String remark;
	private String dollId;
	private String orderDate;
	private String deviceInfos;
	private int isDel;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getRevAddress() {
		return revAddress;
	}
	public void setRevAddress(String revAddress) {
		this.revAddress = revAddress;
	}
	public int getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDollId() {
		return dollId;
	}
	public void setDollId(String dollId) {
		this.dollId = dollId;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getDeviceInfos() {
		return deviceInfos;
	}
	public void setDeviceInfos(String deviceInfos) {
		this.deviceInfos = deviceInfos;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	
	
}
