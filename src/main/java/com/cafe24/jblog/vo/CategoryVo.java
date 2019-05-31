package com.cafe24.jblog.vo;

public class CategoryVo {
	private Long no;
	private String name;
	private String comment;
	private String regDate;
	private String id;
	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", name=" + name + ", comment=" + comment + ", regDate=" + regDate + ", id="
				+ id + "]";
	}
	public Long getNo() {
		return no;
	}
	public String getName() {
		return name;
	}
	public String getComment() {
		return comment;
	}
	public String getRegDate() {
		return regDate;
	}
	public String getId() {
		return id;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
