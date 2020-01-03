package com.hc.pojo.emailTimingTask;

public class TbEmailTimingTask {
	private int tbId;
	private String tbAdminId;//创建用户
	private String tbEmailTaskId;//邮箱内容id
	private String tbJobName;//定时任务的名字(即类的全路径)
	private String tbJobGroup;//定时任务组
	private String tbJobDesc;//定时任务的描述
	private String tbJobStatus;//定时任务的状态
	private String tbCron;//任务表达式
	private String tbNumber;//表示触发的次数
	private String delTime;
	private String createTime;//创建时间
	
	public TbEmailTimingTask() {
		super();
	}
	
	public TbEmailTimingTask(String tbAdminId, String tbEmailTaskId, String tbJobName, String tbJobGroup,
			String tbJobDesc, String tbCron) {
		super();
		this.tbAdminId = tbAdminId;
		this.tbEmailTaskId = tbEmailTaskId;
		this.tbJobName = tbJobName;
		this.tbJobGroup = tbJobGroup;
		this.tbJobDesc = tbJobDesc;
		this.tbCron = tbCron;
	}

	public String getTbAdminId() {
		return tbAdminId;
	}
	public void setTbAdminId(String tbAdminId) {
		this.tbAdminId = tbAdminId;
	}
	public String getTbEmailTaskId() {
		return tbEmailTaskId;
	}
	public void setTbEmailTaskId(String tbEmailTaskId) {
		this.tbEmailTaskId = tbEmailTaskId;
	}
	public String getTbJobName() {
		return tbJobName;
	}
	public void setTbJobName(String tbJobName) {
		this.tbJobName = tbJobName;
	}
	public String getTbJobGroup() {
		return tbJobGroup;
	}
	public void setTbJobGroup(String tbJobGroup) {
		this.tbJobGroup = tbJobGroup;
	}
	public String getTbJobDesc() {
		return tbJobDesc;
	}
	public void setTbJobDesc(String tbJobDesc) {
		this.tbJobDesc = tbJobDesc;
	}
	public String getTbJobStatus() {
		return tbJobStatus;
	}
	public void setTbJobStatus(String tbJobStatus) {
		this.tbJobStatus = tbJobStatus;
	}
	public String getTbCron() {
		return tbCron;
	}
	public void setTbCron(String tbCron) {
		this.tbCron = tbCron;
	}
	public String getTbNumber() {
		return tbNumber;
	}
	public void setTbNumber(String tbNumber) {
		this.tbNumber = tbNumber;
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
	public int getTbId() {
		return tbId;
	}
	public void setTbId(int tbId) {
		this.tbId = tbId;
	}
	
}
