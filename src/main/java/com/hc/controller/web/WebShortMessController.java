package com.hc.controller.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hc.common.result.ResultBase;
import com.hc.pojo.email.TbEmail;

@Controller
@RequestMapping("/web/sm")
@ResponseBody
public class WebShortMessController {

	/**
	 * 这边是发送短信
	 * @param tbUser
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/sendSM")
	public ResultBase sendSM(@RequestBody TbEmail tbEmail, HttpServletRequest request) throws Exception {
		
		return null;
	}
	
}
