package com.hc.pojo.sendMess;

public class TbSendMess {

	private int tbId;
	private String tbTitle;
	private String tbContent;
	private String delTime;
	private String createTime;
	
	public TbSendMess(String tbTitle, String tbContent) {
		super();
		this.tbTitle = tbTitle;
		this.tbContent = tbContent;
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
