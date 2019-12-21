package com.hc.pojo.usually;

public class LetterPageData {
	
	private int tbId;
	private String tbNumber;//编号
	private int sendingState;//发送状态 0表示未发送 1表示发送中 2表示已经发送
	private String phone;//手机号
	private String tbEmail;//邮箱
	private String nickname;//昵称
	private String createTime;//发送时间
	private int follow;//是否关注 0 表示未关注 1表示已经关注
	private int tbType;//信件类型(1 表示是邮件 2表示是短信)
	
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
	public int getSendingState() {
		return sendingState;
	}
	public void setSendingState(int sendingState) {
		this.sendingState = sendingState;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTbEmail() {
		return tbEmail;
	}
	public void setTbEmail(String tbEmail) {
		this.tbEmail = tbEmail;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public int getFollow() {
		return follow;
	}
	public void setFollow(int follow) {
		this.follow = follow;
	}
	public int getTbType() {
		return tbType;
	}
	public void setTbType(int tbType) {
		this.tbType = tbType;
	}
}
