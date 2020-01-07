package com.hc.pojo.callCenter;

import com.hc.para.page_base.PageParam;

public class CallCenter extends PageParam{
	private Integer tbId;
	private Integer tbUserId;
	private String tbNumber;//编号(档案号)
	private Integer tbType;//呼叫类型(1 表示呼入 2表示呼出)
	private Integer tbHandleType;//处理结果即分类 （0 表示未分类 1表示维修）
	private Integer tbState;//接听状态(0 表示无法接通 1表示接通)
	private String tbDuration;//通话时长
	private String soundRecordFile;//录音文件路径
	private String delTime;
	private String createTime;
	private String callName;//呼叫名称
	
	public Integer getTbId() {
		return tbId;
	}
	public void setTbId(Integer tbId) {
		this.tbId = tbId;
	}
	public Integer getTbUserId() {
		return tbUserId;
	}
	public void setTbUserId(Integer tbUserId) {
		this.tbUserId = tbUserId;
	}
	public String getTbNumber() {
		return tbNumber;
	}
	public void setTbNumber(String tbNumber) {
		this.tbNumber = tbNumber;
	}
	public Integer getTbType() {
		return tbType;
	}
	public void setTbType(Integer tbType) {
		this.tbType = tbType;
	}
	public Integer getTbHandleType() {
		return tbHandleType;
	}
	public void setTbHandleType(Integer tbHandleType) {
		this.tbHandleType = tbHandleType;
	}
	public Integer getTbState() {
		return tbState;
	}
	public void setTbState(Integer tbState) {
		this.tbState = tbState;
	}
	public String getTbDuration() {
		return tbDuration;
	}
	public void setTbDuration(String tbDuration) {
		this.tbDuration = tbDuration;
	}
	public String getSoundRecordFile() {
		return soundRecordFile;
	}
	public void setSoundRecordFile(String soundRecordFile) {
		this.soundRecordFile = soundRecordFile;
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
	public String getCallName() {
		return callName;
	}
	public void setCallName(String callName) {
		this.callName = callName;
	}
	
	 
	
}
