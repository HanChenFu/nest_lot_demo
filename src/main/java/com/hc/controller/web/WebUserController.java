package com.hc.controller.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hc.common.result.ResultBase;
import com.hc.common.result.ResultQuery;
import com.hc.pojo.user.TbUser;
import com.hc.service.TbUserService;

@Controller
@RequestMapping("/web/user")
@ResponseBody
public class WebUserController {
	
	@Autowired
	private TbUserService tbUserServer;
	
	@RequestMapping("/getUserMessByName")
	public ResultQuery<TbUser> getUserMessByName(@RequestBody(required = false) TbUser tbUser, HttpServletRequest request) throws Exception {
		return tbUserServer.getUserMessByName(tbUser,request);
	}
	
	@RequestMapping("/insertSelective")
	public ResultBase insertSelective(@RequestBody(required = false) TbUser tbUser, HttpServletRequest request) throws Exception {
		return tbUserServer.insertSelective(tbUser,request);
	}

}
