package com.hc.pojo.reqBean;

public class UpdateUserPasswordReqBean {
	private Integer tbId;
	private String tbName;
	private String passwordOld;
	private String passwordNew;
	private String tbEmail;
	
	public Integer getTbId() {
		return tbId;
	}
	public void setTbId(Integer tbId) {
		this.tbId = tbId;
	}
	public String getTbName() {
		return tbName;
	}
	public void setTbName(String tbName) {
		this.tbName = tbName;
	}
	public String getPasswordOld() {
		return passwordOld;
	}
	public void setPasswordOld(String passwordOld) {
		this.passwordOld = passwordOld;
	}
	public String getPasswordNew() {
		return passwordNew;
	}
	public void setPasswordNew(String passwordNew) {
		this.passwordNew = passwordNew;
	}
	public String getTbEmail() {
		return tbEmail;
	}
	public void setTbEmail(String tbEmail) {
		this.tbEmail = tbEmail;
	}
	
}
