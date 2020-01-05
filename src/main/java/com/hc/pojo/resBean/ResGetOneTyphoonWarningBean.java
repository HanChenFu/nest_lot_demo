package com.hc.pojo.resBean;

import java.util.List;

public class ResGetOneTyphoonWarningBean {
	private String interfaces;
	private String timeTitle;
	private String title;
	private String author;
	private List<String> textList;
	private List<String> imgList;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public List<String> getTextList() {
		return textList;
	}
	public void setTextList(List<String> textList) {
		this.textList = textList;
	}
	public List<String> getImgList() {
		return imgList;
	}
	public void setImgList(List<String> imgList) {
		this.imgList = imgList;
	}
	public String getInterfaces() {
		return interfaces;
	}
	public void setInterfaces(String interfaces) {
		this.interfaces = interfaces;
	}
	public String getTimeTitle() {
		return timeTitle;
	}
	public void setTimeTitle(String timeTitle) {
		this.timeTitle = timeTitle;
	}
	
}
