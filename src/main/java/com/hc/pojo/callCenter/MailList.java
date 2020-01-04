package com.hc.pojo.callCenter;

import com.hc.pojo.base.ReqPageBean;

public class MailList extends ReqPageBean{

	
	private Integer mailListId;							//通讯录住建
	private String  contacts;							//联系人
	private String  phoneNumber;						//电话号码
	private String  remarks;							//备注
	private Integer adminId;							//经手人 index
	private String  modificationTime;					//修改时间
	private String  createTime;							//创建时间
	private String  adminName;							//用户名
	private Integer limitsTart;							//开始页数
	private Integer limitsEnd;							//结束页数
	 
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
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public Integer getLimitsTart() {
		return limitsTart;
	}
	public void setLimitsTart(Integer limitsTart) {
		this.limitsTart = limitsTart;
	}
	public Integer getLimitsEnd() {
		return limitsEnd;
	}
	public void setLimitsEnd(Integer limitsEnd) {
		this.limitsEnd = limitsEnd;
	}
	
}
