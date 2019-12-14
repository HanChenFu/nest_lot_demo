package com.hc.pojo.letter;

public class TbLetter {
	private int tbId;
	private String tbNumber;
	private int tbUserId;
	private int sendMessId;
	private String sendingState;
	private String delTime;
	private String createTime;
	
	public TbLetter(String tbNumber, int tbUserId, int sendMessId, String sendingState) {
		super();
		this.tbNumber = tbNumber;
		this.tbUserId = tbUserId;
		this.sendMessId = sendMessId;
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

}
