package com.hc.pojo.entity;

import java.math.BigDecimal;

public class TbCase {
	private int  tbId;
	private String  tbNumber;
	private int  tbUserId;
	private int  tbCaseTypeId;
	private int	 tbFilingAreaId;
	private String  tbSize;
	private int  tbStar;
	private String tbReportAddress;
	private String  tbAddress;
	private BigDecimal  tbLongitude;
	private BigDecimal  tbLatitude;
	private String  tbDesc;
	private String  tbRemarks;
	private String  tbImages;
	private String  delTime;
	private String  createTime;
	
	
	public String getTbReportAddress() {
		return tbReportAddress;
	}
	public void setTbReportAddress(String tbReportAddress) {
		this.tbReportAddress = tbReportAddress;
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
	public int getTbCaseTypeId() {
		return tbCaseTypeId;
	}
	public void setTbCaseTypeId(int tbCaseTypeId) {
		this.tbCaseTypeId = tbCaseTypeId;
	}
	public int getTbFilingAreaId() {
		return tbFilingAreaId;
	}
	public void setTbFilingAreaId(int tbFilingAreaId) {
		this.tbFilingAreaId = tbFilingAreaId;
	}
	public String getTbSize() {
		return tbSize;
	}
	public void setTbSize(String tbSize) {
		this.tbSize = tbSize;
	}
	public int getTbStar() {
		return tbStar;
	}
	public void setTbStar(int tbStar) {
		this.tbStar = tbStar;
	}
	public String getTbAddress() {
		return tbAddress;
	}
	public void setTbAddress(String tbAddress) {
		this.tbAddress = tbAddress;
	}
	public BigDecimal getTbLongitude() {
		return tbLongitude;
	}
	public void setTbLongitude(BigDecimal tbLongitude) {
		this.tbLongitude = tbLongitude;
	}
	public BigDecimal getTbLatitude() {
		return tbLatitude;
	}
	public void setTbLatitude(BigDecimal tbLatitude) {
		this.tbLatitude = tbLatitude;
	}
	public String getTbDesc() {
		return tbDesc;
	}
	public void setTbDesc(String tbDesc) {
		this.tbDesc = tbDesc;
	}
	public String getTbRemarks() {
		return tbRemarks;
	}
	public void setTbRemarks(String tbRemarks) {
		this.tbRemarks = tbRemarks;
	}
	public String getTbImages() {
		return tbImages;
	}
	public void setTbImages(String tbImages) {
		this.tbImages = tbImages;
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
