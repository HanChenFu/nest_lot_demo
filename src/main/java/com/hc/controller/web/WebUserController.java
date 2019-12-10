package com.hc.controller.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hc.common.result.ResultBase;
import com.hc.pojo.base.TbAdmin;
import com.hc.service.TbUserServer;

@Controller
@RequestMapping("/web/user")
@ResponseBody
public class WebUserController {
	
	@Autowired
	private TbUserServer tbUserServer;
	
	@RequestMapping("/login")
	public ResultBase getList(@RequestBody TbAdmin tbAdmin) throws Exception {
		return tbUserServer.login(tbAdmin);
	}
	
	@RequestMapping("/testzz")
	public ResultBase testzz(@RequestBody TbAdmin tbAdmin) throws Exception {
		return tbUserServer.testzz(tbAdmin);
	}

}