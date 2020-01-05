package com.hc.pojo.resBean;

import java.util.List;

public class ResGetOneTyphoonWarningBean {
	private String title;
	private String author;
	private String time;
	private List<String> writingText;
	private List<String> writingImg;
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
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public List<String> getWritingText() {
		return writingText;
	}
	public void setWritingText(List<String> writingText) {
		this.writingText = writingText;
	}
	public List<String> getWritingImg() {
		return writingImg;
	}
	public void setWritingImg(List<String> writingImg) {
		this.writingImg = writingImg;
	}
	
}
