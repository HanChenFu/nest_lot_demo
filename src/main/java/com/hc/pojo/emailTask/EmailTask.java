package com.hc.pojo.emailTask;

public class EmailTask {
	private int tbId;
	private String target;//目标值
	private String tbSendMessId;//发送内容id
	private String appendixTitle;//附件标题
	private String appendixPath;//附件路径
	private String delTime;
	private String createTime;//创建时间
	
	public EmailTask() {
		super();
	}
	
	public EmailTask(String target, String tbSendMessId, String appendixTitle, String appendixPath) {
		super();
		this.target = target;
		this.tbSendMessId = tbSendMessId;
		this.appendixTitle = appendixTitle;
		this.appendixPath = appendixPath;
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
	public String getAppendixTitle() {
		return appendixTitle;
	}
	public void setAppendixTitle(String appendixTitle) {
		this.appendixTitle = appendixTitle;
	}
	public String getAppendixPath() {
		return appendixPath;
	}
	public void setAppendixPath(String appendixPath) {
		this.appendixPath = appendixPath;
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
