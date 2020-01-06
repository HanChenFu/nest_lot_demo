package com.hc.pojo.callCenter;

import com.hc.pojo.base.ReqPageBean;

/**
 * @author Administrator
 *
 */
public class StationSetting extends ReqPageBean{

	
	private Integer stationSettingId;			 				//座机index
	private String  seatNumber;							 		//座机号
	private String  seatPassword;						 		//座机密码
	private String  ipURL; 									 	//ip连接路径
	private Integer createUserId;                 				//经手人
	private Integer updateUserId;                 				//经手人
	private String  createTime;              					//创建时间
	private String  updateTime;        						//修改时间
	private String  adminName;									//经手人名称
	
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
	public Integer getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	public Integer getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(Integer updateUserId) {
		this.updateUserId = updateUserId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	
}
