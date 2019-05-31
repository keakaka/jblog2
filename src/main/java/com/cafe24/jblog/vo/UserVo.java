package com.cafe24.jblog.vo;

public class UserVo {
	private String id;
	private String name;
	private String pwd;
	private String regDate;
	
	@Override
	public String toString() {
		return "UserVo [id=" + id + ", name=" + name + ", pwd=" + pwd + ", regDate=" + regDate + "]";
	}
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getPwd() {
		return pwd;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
	
}
