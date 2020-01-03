package com.hc.pojo.callCenter;

import com.hc.pojo.base.ReqPageBean;

public class StationSetting extends ReqPageBean{

	
	private Integer stationSettingId;			 				//座机index
	private String  seatNumber;							 		//座机号
	private String  seatPassword;						 		//座机密码
	private String  ipURL; 									 	//ip连接路径
	private Integer adminId;                 					//经手人
	private String  createTime;              					//创建时间
	private String  modificationTime;        					//修改时间
	
	
	public Integer getStationSettingId() {
		return stationSettingId;
	}
	public void setStationSettingId(Integer stationSettingId) {
		this.stationSettingId = stationSettingId;
	}
	public String getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}
	public String getSeatPassword() {
		return seatPassword;
	}
	public void setSeatPassword(String seatPassword) {
		this.seatPassword = seatPassword;
	}
	public String getIpURL() {
		return ipURL;
	}
	public void setIpURL(String ipURL) {
		this.ipURL = ipURL;
	}
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getModificationTime() {
		return modificationTime;
	}
	public void setModificationTime(String modificationTime) {
		this.modificationTime = modificationTime;
	}
	
}
