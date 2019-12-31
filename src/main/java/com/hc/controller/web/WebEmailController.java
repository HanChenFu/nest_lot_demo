package com.hc.controller.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
	ResultBase sendMail(TbEmail tbEmail, MultipartFile file,HttpServletRequest request) throws Exception,CustomException{
		return tbMailService.sendMail(tbEmail, file, request);
	}

}


