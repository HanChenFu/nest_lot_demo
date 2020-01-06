package com.hc.pojo.shortMess;

/**
 * 	这边是短信发送的表
 * @author Administrator
 *
 */
public class TbShortMess {
	private int tbId;
	private String tbNumber;
	private String tbAdminId;
	private int tbUserId;
	private String target;
	private int sendMessId;
	private String sendingState;
	private String delTime;
	private String createTime;
	private String name;
	
	public TbShortMess() {
		super();
	}
	
	public TbShortMess(String tbAdminId,String tbNumber, String target,int sendMessId,String sendingState) {
		super();
		this.tbAdminId = tbAdminId;
		this.tbNumber = tbNumber;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
