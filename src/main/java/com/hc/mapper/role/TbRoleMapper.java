package com.hc.mapper.role;

import java.util.List;

import com.hc.pojo.role.Role;

public interface TbRoleMapper {
	
	List<Role> getRoleMess(Role role);
	
	int getRoleMessCount(Role role);
	
}