package com.hc.pojo.user;

import com.hc.para.page_base.PageParam;

public class TbUser extends PageParam{
	private String tbId;
	private String wpopenid;
	private String minopenid;
	private String phone;
	private String tbEmail;
	private String passwordMD5;
	private String nickname;
	private String username;
	private String sex;
	private String birthday;
	private String height;
	private String tbProvinceName;
	private String tbProvinceId;
	private String tbCityName;
	private String tbCityId;
	private String tbAreaName;
	private String tbAreaId;
	private String detailedAddress;
	private String headPortrait;
	private String loginTime;
	private String delTime;
	private String createTime;
	private String tbUuid;
	
	public TbUser(String tbEmail) {
		super();
		this.tbEmail = tbEmail;
	}
	public String getTbId() {
		return tbId;
	}
	public void setTbId(String tbId) {
		this.tbId = tbId;
	}
	public String getWpopenid() {
		return wpopenid;
	}
	public void setWpopenid(String wpopenid) {
		this.wpopenid = wpopenid;
	}
	public String getMinopenid() {
		return minopenid;
	}
	public void setMinopenid(String minopenid) {
		this.minopenid = minopenid;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTbEmail() {
		return tbEmail;
	}
	public void setTbEmail(String tbEmail) {
		this.tbEmail = tbEmail;
	}
	public String getPasswordMD5() {
		return passwordMD5;
	}
	public void setPasswordMD5(String passwordMD5) {
		this.passwordMD5 = passwordMD5;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getTbProvinceName() {
		return tbProvinceName;
	}
	public void setTbProvinceName(String tbProvinceName) {
		this.tbProvinceName = tbProvinceName;
	}
	public String getTbProvinceId() {
		return tbProvinceId;
	}
	public void setTbProvinceId(String tbProvinceId) {
		this.tbProvinceId = tbProvinceId;
	}
	public String getTbCityName() {
		return tbCityName;
	}
	public void setTbCityName(String tbCityName) {
		this.tbCityName = tbCityName;
	}
	public String getTbCityId() {
		return tbCityId;
	}
	public void setTbCityId(String tbCityId) {
		this.tbCityId = tbCityId;
	}
	public String getTbAreaName() {
		return tbAreaName;
	}
	public void setTbAreaName(String tbAreaName) {
		this.tbAreaName = tbAreaName;
	}
	public String getTbAreaId() {
		return tbAreaId;
	}
	public void setTbAreaId(String tbAreaId) {
		this.tbAreaId = tbAreaId;
	}
	public String getDetailedAddress() {
		return detailedAddress;
	}
	public void setDetailedAddress(String detailedAddress) {
		this.detailedAddress = detailedAddress;
	}
	public String getHeadPortrait() {
		return headPortrait;
	}
	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
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
	public String getTbUuid() {
		return tbUuid;
	}
	public void setTbUuid(String tbUuid) {
		this.tbUuid = tbUuid;
	}
	
	
}
