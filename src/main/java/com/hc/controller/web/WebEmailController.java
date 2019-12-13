package com.hc.controller.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hc.common.exception.CustomException;
import com.hc.common.result.ResultBase;
import com.hc.pojo.email.TbEmail;
import com.hc.service.TbMailService;

@Controller
@RequestMapping("/web/email")
@ResponseBody
public class WebEmailController {
	
	@Autowired
	private TbMailService tbMailService;
	
	@RequestMapping("/sendEmail")
	ResultBase sendMail(@RequestBody TbEmail tbEmail,HttpServletRequest request) throws Exception,CustomException{
		return tbMailService.sendMail(tbEmail, request);
	}

}


