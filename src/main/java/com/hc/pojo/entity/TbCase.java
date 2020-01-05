package com.hc.pojo.entity;

import java.math.BigDecimal;

import com.hc.utils.conig.SystemConfigUtil;

public class TbCase {
	private int  tbId;
	private String  tbNumber;//案件标号
	private int  tbUserId;//用户id
	private int  tbCaseTypeId;//案件类型id
	/*
	 * 案件保存类型  0：联网保存，1：草稿箱保存
	 */
	private int tbCaseSaveCategory;
	/*
	 * 1 刑事案件 2 森林火灾 3 普通案件 4 安全生产
	 */
	private int	 tbFilingAreaId;//报案地区
	/*
	 * 1 马石警局 2 龙华警局 3 龙岗警局 4 南山警局
	 */
	private String  tbSize;//事件大小
	private int  tbStar;//关注星级
	private String tbReportAddress;//案件地址
	private String  tbAddress;//案件地址(详细)
	private BigDecimal  tbLongitude;//经度
	private BigDecimal  tbLatitude;//纬度
	private String  tbDesc;//案件经过
	private String  tbRemarks;//案件备注
	private String  tbImages;//关键图片
	private String filedTime;//归档时间
	private String caseTime;//案件发生时间
	private String  delTime;
	private String  createTime;
	
	private String[]  tbImagesFomat;
	
	public String[] getTbImagesFomat() {
		return tbImagesFomat;
	}
	/*public void setTbImagesFomat(String[] tbImagesFomat) {
		this.tbImagesFomat = tbImagesFomat;
	}*/
	public int getTbCaseSaveCategory() {
		return tbCaseSaveCategory;
	}
	public void setTbCaseSaveCategory(int tbCaseSaveCategory) {
		this.tbCaseSaveCategory = tbCaseSaveCategory;
	}
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
		if(tbImages!=null&&!"".equals(tbImages)){
			String[] tbImagesUrl = tbImages.split(",");
			for(int i = 0 ; i<tbImagesUrl.length ; i++){
				tbImagesUrl[i] = SystemConfigUtil.getValue("aliyun_oss_url") + tbImagesUrl[i];
			}
			this.tbImagesFomat = tbImagesUrl;
		}
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
