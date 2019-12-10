package com.hc.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hc.common.result.ResultBase;
import com.hc.pojo.base.TbAdmin;
import com.hc.service.TbAdminService;

@Controller
@RequestMapping("/web/admin")
@ResponseBody
public class WebAdminController {

	@Autowired
	private TbAdminService tbAdminService;
	
	@RequestMapping("/login")
	public ResultBase getList(@RequestBody TbAdmin tbAdmin) throws Exception {
		return tbAdminService.adminLogin(tbAdmin);
	}
	
}
