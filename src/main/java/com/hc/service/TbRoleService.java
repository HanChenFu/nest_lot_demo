package com.hc.service;

import javax.servlet.http.HttpServletRequest;

import com.hc.common.exception.CustomException;
import com.hc.common.result.ResultQuery;
import com.hc.pojo.role.Role;

public interface TbRoleService {
	
	ResultQuery<Role> getRoleMess(Role role ,HttpServletRequest request) throws Exception,CustomException;
	
}
