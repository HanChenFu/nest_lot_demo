package com.hc.pojo.callCenter;

public class MailList {

	
	private Integer mailListId;							//通讯录住建
	private String  contacts;							//联系人
	private String  phoneNumber;						//电话号码
	private String  remarks;							//备注
	private Integer adminId;							//经手人 index
	private String  modificationTime;					//修改时间
	private String  createTime;							//创建时间
	
	 
	public Integer getMailListId() {
		return mailListId;
	}
	public void setMailListId(Integer mailListId) {
		this.mailListId = mailListId;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	public String getModificationTime() {
		return modificationTime;
	}
	public void setModificationTime(String modificationTime) {
		this.modificationTime = modificationTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
}
