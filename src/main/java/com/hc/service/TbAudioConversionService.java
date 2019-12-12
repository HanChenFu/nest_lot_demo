package com.hc.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.hc.common.exception.CustomException;
import com.hc.common.result.ResultBase;

public interface TbAudioConversionService {

	ResultBase AudioToText(MultipartFile file,HttpServletRequest request)throws Exception,CustomException;
	
}
