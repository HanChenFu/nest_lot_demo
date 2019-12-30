package com.hc.pojo.reqBean;

import com.hc.pojo.base.ReqPageBean;

public class QueryAllCaseListReqBean extends ReqPageBean{
	private Integer tbCaseTypeId;
	private String time;
	private String tbNumber;
	private String tbAddress;
	private String tbSize;
	private Integer tbStar;
	private Integer tbCaseSaveCategory;
	
	public Integer getTbCaseSaveCategory() {
		return tbCaseSaveCategory;
	}
	public void setTbCaseSaveCategory(Integer tbCaseSaveCategory) {
		this.tbCaseSaveCategory = tbCaseSaveCategory;
	}
	public Integer getTbCaseTypeId() {
		return tbCaseTypeId;
	}
	public void setTbCaseTypeId(Integer tbCaseTypeId) {
		this.tbCaseTypeId = tbCaseTypeId;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTbNumber() {
		return tbNumber;
	}
	public void setTbNumber(String tbNumber) {
		this.tbNumber = tbNumber;
	}
	public String getTbAddress() {
		return tbAddress;
	}
	public void setTbAddress(String tbAddress) {
		this.tbAddress = tbAddress;
	}
	public String getTbSize() {
		return tbSize;
	}
	public void setTbSize(String tbSize) {
		this.tbSize = tbSize;
	}
	public Integer getTbStar() {
		return tbStar;
	}
	public void setTbStar(Integer tbStar) {
		this.tbStar = tbStar;
	}
	
}
