package com.hc.pojo.askRecord;

import com.hc.para.page_base.PageParam;

public class TbAskRecord extends PageParam{
	private String tbId;
	private String tbUserId;
	private String tbAdminId;
	private String tbNumber;
	private String tbName;
	private String tbEnclosure;
	private String tbHistory;
	private String tbDraft;
	private String delTime;
	private String updateTime;//修改时间
	private String createTime;
	private String tbDesc;
	
	public String getTbId() {
		return tbId;
	}
	public void setTbId(String tbId) {
		this.tbId = tbId;
	}
	public String getTbUserId() {
		return tbUserId;
	}
	public void setTbUserId(String tbUserId) {
		this.tbUserId = tbUserId;
	}
	public String getTbNumber() {
		return tbNumber;
	}
	public void setTbNumber(String tbNumber) {
		this.tbNumber = tbNumber;
	}
	public String getTbName() {
		return tbName;
	}
	public void setTbName(String tbName) {
		this.tbName = tbName;
	}
	public String getTbEnclosure() {
		return tbEnclosure;
	}
	public void setTbEnclosure(String tbEnclosure) {
		this.tbEnclosure = tbEnclosure;
	}
	public String getTbHistory() {
		return tbHistory;
	}
	public void setTbHistory(String tbHistory) {
		this.tbHistory = tbHistory;
	}
	public String getTbDraft() {
		return tbDraft;
	}
	public void setTbDraft(String tbDraft) {
		this.tbDraft = tbDraft;
	}
	public String getDelTime() {
		return delTime;
	}
	public void setDelTime(String delTime) {
		this.delTime = delTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getTbDesc() {
		return tbDesc;
	}
	public void setTbDesc(String tbDesc) {
		this.tbDesc = tbDesc;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getTbAdminId() {
		return tbAdminId;
	}
	public void setTbAdminId(String tbAdminId) {
		this.tbAdminId = tbAdminId;
	}
}
