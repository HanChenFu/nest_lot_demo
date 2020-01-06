package com.hc.pojo.task;

public class TaskData {
	private int tbId;
	private int tbAdminId;
	private int	tbNumber;//一共执行次数
	private String	target;//目标邮箱
	private String	appendixTitle;//附件标题
	private String	appendixPath;//附件地址
	private String	tbTitle;//标题
	private String	tbContent;//内容
	private String cron;//表达式
	private String jobName;//定时任务名称
	private String jobGroup;//定时任务组
	
	public TaskData() {
		super();
	}
	
	public TaskData(String cron, String jobName, String jobGroup) {
		super();
		this.cron = cron;
		this.jobName = jobName;
		this.jobGroup = jobGroup;
	}

	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
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
	public String getTbTitle() {
		return tbTitle;
	}
	public void setTbTitle(String tbTitle) {
		this.tbTitle = tbTitle;
	}
	public String getTbContent() {
		return tbContent;
	}
	public void setTbContent(String tbContent) {
		this.tbContent = tbContent;
	}
	public int getTbAdminId() {
		return tbAdminId;
	}
	public void setTbAdminId(int tbAdminId) {
		this.tbAdminId = tbAdminId;
	}
	public int getTbNumber() {
		return tbNumber;
	}
	public void setTbNumber(int tbNumber) {
		this.tbNumber = tbNumber;
	}
	public int getTbId() {
		return tbId;
	}
	public void setTbId(int tbId) {
		this.tbId = tbId;
	}
	public String getCron() {
		return cron;
	}
	public void setCron(String cron) {
		this.cron = cron;
	}
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
	
}
