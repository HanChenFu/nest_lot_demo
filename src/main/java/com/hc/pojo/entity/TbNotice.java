package com.hc.pojo.entity;

import java.sql.Timestamp;

public class TbNotice {
	
	private int tbId;
	private String tbTitle;
	private String tbContent;
	private String tbFigurePath;
	private Timestamp delTime;
	private Timestamp createTime;
	private String tbHtmlPath;
	
	public int getTbId() {
		return tbId;
	}
	public void setTbId(int tbId) {
		this.tbId = tbId;
	}
	public String getTbTitle() {
		return tbTitle;
	}
	public void setTbTitle(String tbTitle) {
		this.tbTitle = tbTitle;
	}
	public String getTbContent() {
		return tbContent;
	}
	public void setTbContent(String tbContent) {
		this.tbContent = tbContent;
	}
	public String getTbFigurePath() {
		return tbFigurePath;
	}
	public void setTbFigurePath(String tbFigurePath) {
		this.tbFigurePath = tbFigurePath;
	}
	public Timestamp getDelTime() {
		return delTime;
	}
	public void setDelTime(Timestamp delTime) {
		this.delTime = delTime;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getTbHtmlPath() {
		return tbHtmlPath;
	}
	public void setTbHtmlPath(String tbHtmlPath) {
		this.tbHtmlPath = tbHtmlPath;
	}
	
	

}
