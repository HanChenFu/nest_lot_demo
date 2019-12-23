package com.hc.pojo.letter;

/**
 * 	这边是邮件发送的表
 * @author Administrator
 *
 */
public class TbLetter {
	private int tbId;
	private String tbNumber;
	private String tbAdminId;
	private int tbUserId;
	private String target;//目标用户邮箱
	private int sendMessId;
	private String sendingState;
	private String delTime;
	private String createTime;
	
	public TbLetter() {
		super();
	}
	
	public TbLetter(String tbAdminId,String tbNumber, int tbUserId, String target,int sendMessId,String sendingState) {
		super();
		this.tbAdminId = tbAdminId;
		this.tbNumber = tbNumber;
		this.tbUserId = tbUserId;
		this.target = target;
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
	public String getTbAdminId() {
		return tbAdminId;
	}
	public void setTbAdminId(String tbAdminId) {
		this.tbAdminId = tbAdminId;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

}
