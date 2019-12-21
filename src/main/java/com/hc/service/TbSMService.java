package com.hc.service;

import javax.servlet.http.HttpServletRequest;

import com.hc.common.exception.CustomException;
import com.hc.common.result.ResultBase;
import com.hc.common.result.ResultQuery;
import com.hc.para.page_base.BasePara;
import com.hc.pojo.email.TbEmail;
import com.hc.pojo.usually.LetterPageData;

public interface TbSMService {

	ResultBase sendSM(TbEmail tbEmail,HttpServletRequest request) throws Exception,CustomException;
	
	ResultQuery<LetterPageData> getShortMess(BasePara para,HttpServletRequest request) throws Exception,CustomException;
	
}
