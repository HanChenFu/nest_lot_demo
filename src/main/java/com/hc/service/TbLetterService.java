package com.hc.service;

import javax.servlet.http.HttpServletRequest;

import com.hc.common.exception.CustomException;
import com.hc.common.result.ResultBase;
import com.hc.common.result.ResultQuery;
import com.hc.para.page_base.BasePara;
import com.hc.pojo.letter.TbLetter;
import com.hc.pojo.usually.LetterPageData;

public interface TbLetterService {

	ResultQuery<LetterPageData> getLetterMess(BasePara para,HttpServletRequest request) throws Exception,CustomException;
	
	ResultBase insertSelective(TbLetter letter,HttpServletRequest request) throws Exception,CustomException;
	
	ResultBase deleLetter(BasePara para,HttpServletRequest request) throws Exception,CustomException;
	
}
