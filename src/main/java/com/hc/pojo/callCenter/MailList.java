package com.hc.pojo.callCenter;

import com.hc.pojo.base.ReqPageBean;

public class MailList extends ReqPageBean{

	
	private Integer mailListId;							//通讯录住建
	private String  contacts;							//联系人
	private String  phoneNumber;						//电话号码
	private String  mailboxNumber;						//邮箱
	private String  remarks;							//备注
	private Integer createUserId;						//经手人 id
	private Integer updateUserId;						//修改人 id
	private String  createTime;							//创建时间
	private String  updateTime;							//修改时间
	private Integer flag;								//1=可用 2=已删除
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
	public String getMailboxNumber() {
		return mailboxNumber;
	}
	public void setMailboxNumber(String mailboxNumber) {
		this.mailboxNumber = mailboxNumber;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	public Integer getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(Integer updateUserId) {
		this.updateUserId = updateUserId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
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
