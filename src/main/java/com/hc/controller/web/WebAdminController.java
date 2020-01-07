package com.hc.controller.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hc.common.result.ResultBase;
import com.hc.pojo.base.TbAdmin;
import com.hc.pojo.reqBean.UpdateUserMess;
import com.hc.service.TbAdminService;

@Controller
@RequestMapping("/web/admin")
@ResponseBody
public class WebAdminController {

	@Autowired
	private TbAdminService tbAdminService;
	
	@RequestMapping("/login")
	public ResultBase getList(@RequestBody(required = false) TbAdmin tbAdmin) throws Exception {
		return tbAdminService.adminLogin(tbAdmin);
	}
	
	@RequestMapping("/adminLogout")
	public ResultBase adminLogout(HttpServletRequest request) throws Exception {
		return tbAdminService.adminLogout(request);
	}
	
	@RequestMapping("/updateAdminMess")
	public ResultBase updateAdminMess(@RequestBody(required = false) UpdateUserMess bean,HttpServletRequest request) throws Exception {
		return tbAdminService.updateAdminMess(bean,request);
	}
	
}
