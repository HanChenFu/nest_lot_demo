package com.hc.controller.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hc.common.result.ResultQuery;
import com.hc.pojo.role.Role;
import com.hc.service.TbRoleService;

@Controller
@RequestMapping("/web/role")
@ResponseBody
public class WebRoleController {
	
	@Autowired
	private TbRoleService tbRoleServer;
	
	@RequestMapping("/getRoleMess")
	public ResultQuery<Role> getRoleMess(@RequestBody(required = false) Role role, HttpServletRequest request) throws Exception {
		return tbRoleServer.getRoleMess(role,request);
	}
	
}
