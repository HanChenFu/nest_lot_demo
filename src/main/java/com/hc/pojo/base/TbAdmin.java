package com.hc.pojo.base;

public class TbAdmin {
    private Integer tbId;

    private String tbUuid;

    private String tbAccount;

    private String tbPhone;

    private String tbPassword;
    
    private String tbName;

    private String tbHeadPath;

    private Integer adminType;

    private String delTime;

    private String createTime;
    
    private String token;//用户token

    public String getTbUuid() {
        return tbUuid;
    }

    public void setTbUuid(String tbUuid) {
        this.tbUuid = tbUuid;
    }

    public String getTbName() {
		return tbName;
	}

	public void setTbName(String tbName) {
		this.tbName = tbName;
	}

	public Integer getTbId() {
        return tbId;
    }

    public void setTbId(Integer tbId) {
        this.tbId = tbId;
    }

    public String getTbAccount() {
        return tbAccount;
    }

    public void setTbAccount(String tbAccount) {
        this.tbAccount = tbAccount == null ? null : tbAccount.trim();
    }

    public String getTbPhone() {
        return tbPhone;
    }

    public void setTbPhone(String tbPhone) {
        this.tbPhone = tbPhone == null ? null : tbPhone.trim();
    }

    public String getTbPassword() {
        return tbPassword;
    }

    public void setTbPassword(String tbPassword) {
        this.tbPassword = tbPassword == null ? null : tbPassword.trim();
    }

    public String getTbHeadPath() {
        return tbHeadPath;
    }

    public void setTbHeadPath(String tbHeadPath) {
        this.tbHeadPath = tbHeadPath == null ? null : tbHeadPath.trim();
    }

    public Integer getAdminType() {
        return adminType;
    }

    public void setAdminType(Integer adminType) {
        this.adminType = adminType;
    }

    public String getDelTime() {
        return delTime;
    }

    public void setDelTime(String delTime) {
        this.delTime = delTime == null ? null : delTime.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
    
}
