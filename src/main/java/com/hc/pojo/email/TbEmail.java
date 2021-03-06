package com.hc.pojo.email;

public class TbEmail {
	private String from;
	private String tbAdminId;
	private String to; //发送给的用户
	private String title;
	private String content;
	private String appendixTitle;//这边是显示附件标题
	
	public TbEmail() {
		super();
	}
	
	public TbEmail(String tbAdminId, String to, String title, String content, String appendixTitle) {
		super();
		this.tbAdminId = tbAdminId;
		this.to = to;
		this.title = title;
		this.content = content;
		this.appendixTitle = appendixTitle;
	}

	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTbAdminId() {
		return tbAdminId;
	}
	public void setTbAdminId(String tbAdminId) {
		this.tbAdminId = tbAdminId;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getAppendixTitle() {
		return appendixTitle;
	}
	public void setAppendixTitle(String appendixTitle) {
		this.appendixTitle = appendixTitle;
	}

}
