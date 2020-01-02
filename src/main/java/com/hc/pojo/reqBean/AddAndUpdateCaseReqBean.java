package com.hc.pojo.reqBean;

import org.springframework.web.multipart.MultipartFile;

public class AddAndUpdateCaseReqBean {
	private Integer tbId;//案件ID（update） 
	private String tbNumber;//编号(档案号)
	private MultipartFile[] files;//上传的图片流
	private String[] delImgs;//需要删除的图片数组（update）	
	private Integer tbUserId;//用户id
	private Integer tbCaseTypeId;//案件类型ID	
	private Integer tbCaseSaveCategory;//案件保存类别（0：联网保存，1：草稿箱保存）	
	private Integer tbFilingAreaId;//报案地址ID
	private String tbSize;//事件大小 1=小  2=中  3=大
	private Integer tbStar;//关注星级	
	private String tbReportAddress;//案件地址	
	private String tbAddress;//案件地址(详细)
	private Double tbLongitude;//经度
	private Double tbLatitude;//纬度	
	private String tbDesc;//案件经过
	private String tbRemarks;//案件备注
	private String  tbImages;//关键图片-----------------
	private String filedTime;//归档时间	
	private String caseTime;//案件发生时间	
	private String  delTime;
	private String  createTime;
	
	public String getTbImages() {
		return tbImages;
	}
	public void setTbImages(String tbImages) {
		this.tbImages = tbImages;
	}
	public Integer getTbUserId() {
		return tbUserId;
	}
	public void setTbUserId(Integer tbUserId) {
		this.tbUserId = tbUserId;
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
	public Integer getTbId() {
		return tbId;
	}
	public void setTbId(Integer tbId) {
		this.tbId = tbId;
	}
	public String getTbNumber() {
		return tbNumber;
	}
	public void setTbNumber(String tbNumber) {
		this.tbNumber = tbNumber;
	}
	public MultipartFile[] getFiles() {
		return files;
	}
	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}
	public String[] getDelImgs() {
		return delImgs;
	}
	public void setDelImgs(String[] delImgs) {
		this.delImgs = delImgs;
	}
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
	public Integer getTbFilingAreaId() {
		return tbFilingAreaId;
	}
	public void setTbFilingAreaId(Integer tbFilingAreaId) {
		this.tbFilingAreaId = tbFilingAreaId;
	}
	public String getTbReportAddress() {
		return tbReportAddress;
	}
	public void setTbReportAddress(String tbReportAddress) {
		this.tbReportAddress = tbReportAddress;
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
	public String getTbAddress() {
		return tbAddress;
	}
	public void setTbAddress(String tbAddress) {
		this.tbAddress = tbAddress;
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
	public Double getTbLongitude() {
		return tbLongitude;
	}
	public void setTbLongitude(Double tbLongitude) {
		this.tbLongitude = tbLongitude;
	}
	public Double getTbLatitude() {
		return tbLatitude;
	}
	public void setTbLatitude(Double tbLatitude) {
		this.tbLatitude = tbLatitude;
	}
	public String getCaseTime() {
		return caseTime;
	}
	public void setCaseTime(String caseTime) {
		this.caseTime = caseTime;
	}
	public String getFiledTime() {
		return filedTime;
	}
	public void setFiledTime(String filedTime) {
		this.filedTime = filedTime;
	}
	
}
