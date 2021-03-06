package com.hc.pojo.shortMess;

public class TbShortPara {
	private String from;
	private String tbAdminId;
	private String to; //发送给的用户
	private String title;
	private String content;
	
	public TbShortPara() {
		super();
	}
	
	public TbShortPara(String tbAdminId, String to, String content) {
		super();
		this.tbAdminId = tbAdminId;
		this.to = to;
		this.content = content;
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
}
