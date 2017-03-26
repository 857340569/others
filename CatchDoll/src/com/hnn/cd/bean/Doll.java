package com.hnn.cd.bean;

public class Doll {
	private int id;
	private String dollName;
	private String describ;
	private String outPrice;
	private String inPrice;
	private String imgUrl;
	private int catchCount;//剩余次数
	private int isDel;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDollName() {
		return dollName;
	}
	public void setDollName(String dollName) {
		this.dollName = dollName;
	}
	public String getDescrib() {
		return describ;
	}
	public void setDescrib(String describ) {
		this.describ = describ;
	}
	public String getOutPrice() {
		return outPrice;
	}
	public void setOutPrice(String outPrice) {
		this.outPrice = outPrice;
	}
	public String getInPrice() {
		return inPrice;
	}
	public void setInPrice(String inPrice) {
		this.inPrice = inPrice;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public int getCatchCount() {
		return catchCount;
	}
	public void setCatchCount(int catchCount) {
		this.catchCount = catchCount;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	
	
}
