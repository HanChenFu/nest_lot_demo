package com.hc.pojo.letterTask;

public class LetterTask {
	private int tbId;
	private String target;//目标值
	private String tbSendMessId;//发送内容id
	private String delTime;
	private String createTime;//创建时间
	
	public LetterTask() {
		super();
	}
	
	public LetterTask(String target, String tbSendMessId) {
		super();
		this.target = target;
		this.tbSendMessId = tbSendMessId;
	}
	
	public int getTbId() {
		return tbId;
	}
	public void setTbId(int tbId) {
		this.tbId = tbId;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getTbSendMessId() {
		return tbSendMessId;
	}
	public void setTbSendMessId(String tbSendMessId) {
		this.tbSendMessId = tbSendMessId;
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
	
}
