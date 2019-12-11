package com.hc.pojo.role;

import com.hc.para.page_base.PageParam;

public class Role extends PageParam{
	private int roleId;
	private String roleUuid;
	private String roleName;
	private String roleTime;
	
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleUuid() {
		return roleUuid;
	}
	public void setRoleUuid(String roleUuid) {
		this.roleUuid = roleUuid;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleTime() {
		return roleTime;
	}
	public void setRoleTime(String roleTime) {
		this.roleTime = roleTime;
	}
	
	
}
