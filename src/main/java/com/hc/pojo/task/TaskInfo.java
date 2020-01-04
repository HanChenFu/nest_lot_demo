package com.hc.pojo.task;

public class TaskInfo {
	private static final long serialVersionUID = -8054692082716173379L;
	private int id;
	/**任务名称*/
	private String jobName;
	
	/**任务分组*/
	private String jobGroup;
	
	/**任务描述*/
	private String jobDescription;
	
	/**任务状态*/
	private String jobStatus;
	
	/**任务表达式*/
	private String cronExpression;
	
	private String createTime;
	private String tbNumber;//发送的次数
	
	private String tbAdminId;
	private String to; //发送给的用户
	private String title;
	private String content;
	private String appendixTitle;//这边是显示附件标题

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTbAdminId() {
		return tbAdminId;
	}

	public void setTbAdminId(String tbAdminId) {
		this.tbAdminId = tbAdminId;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAppendixTitle() {
		return appendixTitle;
	}

	public void setAppendixTitle(String appendixTitle) {
		this.appendixTitle = appendixTitle;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getTbNumber() {
		return tbNumber;
	}

	public void setTbNumber(String tbNumber) {
		this.tbNumber = tbNumber;
	}
	
	
}
