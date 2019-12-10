package com.hc.pojo.base;

public class TbAdmin {
	private int tbId;
	private int tbRoleId;
	private String tbAccount;
	private String tbPhone;
	private String tbPassword;
	private String tbName;
	private String tbHeadPath;
	private String tbEmail;
	private String delTime;
	private String createTime;
	private String tbUuid;
    
    private String token;//用户token

	public int getTbId() {
		return tbId;
	}

	public void setTbId(int tbId) {
		this.tbId = tbId;
	}

	public int getTbRoleId() {
		return tbRoleId;
	}

	public void setTbRoleId(int tbRoleId) {
		this.tbRoleId = tbRoleId;
	}

	public String getTbAccount() {
		return tbAccount;
	}

	public void setTbAccount(String tbAccount) {
		this.tbAccount = tbAccount;
	}

	public String getTbPhone() {
		return tbPhone;
	}

	public void setTbPhone(String tbPhone) {
		this.tbPhone = tbPhone;
	}

	public String getTbPassword() {
		return tbPassword;
	}

	public void setTbPassword(String tbPassword) {
		this.tbPassword = tbPassword;
	}

	public String getTbName() {
		return tbName;
	}

	public void setTbName(String tbName) {
		this.tbName = tbName;
	}

	public String getTbHeadPath() {
		return tbHeadPath;
	}

	public void setTbHeadPath(String tbHeadPath) {
		this.tbHeadPath = tbHeadPath;
	}

	public String getTbEmail() {
		return tbEmail;
	}

	public void setTbEmail(String tbEmail) {
		this.tbEmail = tbEmail;
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

	public String getTbUuid() {
		return tbUuid;
	}

	public void setTbUuid(String tbUuid) {
		this.tbUuid = tbUuid;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "TbAdmin [tbId=" + tbId + ", tbRoleId=" + tbRoleId + ", tbAccount=" + tbAccount + ", tbPhone=" + tbPhone
				+ ", tbPassword=" + tbPassword + ", tbName=" + tbName + ", tbHeadPath=" + tbHeadPath + ", tbEmail="
				+ tbEmail + ", delTime=" + delTime + ", createTime=" + createTime + ", tbUuid=" + tbUuid + ", token="
				+ token + "]";
	}
	

}
