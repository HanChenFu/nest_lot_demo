package com.hc.pojo.letter;

public class TbLetter {
	private int tbId;
	private String tbNumber;
	private String tbAdminId;
	private int tbUserId;
	private int sendMessId;
	private String sendingState;
	private String delTime;
	private int tbType;//信件类型(1 表示是邮件 2表示是短信)
	private String createTime;
	
	public TbLetter() {
		super();
	}
	
	
	public TbLetter(String tbAdminId,String tbNumber, int tbUserId, int sendMessId, int tbType,String sendingState) {
		super();
		this.tbAdminId = tbAdminId;
		this.tbNumber = tbNumber;
		this.tbUserId = tbUserId;
		this.sendMessId = sendMessId;
		this.tbType = tbType;
		this.sendingState = sendingState;
	}
	public int getTbId() {
		return tbId;
	}
	public void setTbId(int tbId) {
		this.tbId = tbId;
	}
	public String getTbNumber() {
		return tbNumber;
	}
	public void setTbNumber(String tbNumber) {
		this.tbNumber = tbNumber;
	}
	public int getTbUserId() {
		return tbUserId;
	}
	public void setTbUserId(int tbUserId) {
		this.tbUserId = tbUserId;
	}
	public String getSendingState() {
		return sendingState;
	}
	public void setSendingState(String sendingState) {
		this.sendingState = sendingState;
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
	public int getSendMessId() {
		return sendMessId;
	}
	public void setSendMessId(int sendMessId) {
		this.sendMessId = sendMessId;
	}
	public String getTbAdminId() {
		return tbAdminId;
	}
	public void setTbAdminId(String tbAdminId) {
		this.tbAdminId = tbAdminId;
	}


	public int getTbType() {
		return tbType;
	}


	public void setTbType(int tbType) {
		this.tbType = tbType;
	}
	
}
