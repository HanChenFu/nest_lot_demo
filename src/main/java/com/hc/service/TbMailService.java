package com.hc.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.hc.common.exception.CustomException;
import com.hc.common.result.ResultBase;
import com.hc.pojo.email.TbEmail;

public interface TbMailService {

	ResultBase sendMail(TbEmail tbEmail, MultipartFile file,HttpServletRequest request) throws Exception,CustomException;
	
}
