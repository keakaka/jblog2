package com.cafe24.jblog.vo;

public class PostVo {
	private Long no;
	private String title;
	private String content;
	private String regDate;
	private Long categoryNo;
	@Override
	public String toString() {
		return "PostVo [no=" + no + ", title=" + title + ", content=" + content + ", regDate=" + regDate
				+ ", categoryNo=" + categoryNo + "]";
	}
	public Long getNo() {
		return no;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public String getRegDate() {
		return regDate;
	}
	public Long getCategoryNo() {
		return categoryNo;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public void setCategoryNo(Long categoryNo) {
		this.categoryNo = categoryNo;
	}
	
	
}
