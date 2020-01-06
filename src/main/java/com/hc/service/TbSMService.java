package com.hc.service;

import javax.servlet.http.HttpServletRequest;

import com.hc.common.exception.CustomException;
import com.hc.common.result.ResultBase;
import com.hc.common.result.ResultQuery;
import com.hc.para.page_base.BasePara;
import com.hc.pojo.shortMess.TbShortMess;
import com.hc.pojo.shortMess.TbShortPara;
import com.hc.pojo.usually.LetterPageData;

public interface TbSMService {

	ResultBase sendSM(TbShortPara shortPara,HttpServletRequest request) throws Exception,CustomException;
	
	ResultQuery<LetterPageData> getShortMess(BasePara para,HttpServletRequest request) throws Exception,CustomException;
	
	ResultBase deleShort(BasePara para,HttpServletRequest request) throws Exception,CustomException;
	
	ResultBase updateNameById(TbShortMess letter,HttpServletRequest request) throws Exception,CustomException;
	
}
