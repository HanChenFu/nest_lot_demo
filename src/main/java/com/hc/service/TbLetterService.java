package com.hc.service;

import javax.servlet.http.HttpServletRequest;

import com.hc.common.exception.CustomException;
import com.hc.common.result.ResultQuery;
import com.hc.para.page_base.BasePara;
import com.hc.pojo.usually.LetterPageData;

public interface TbLetterService {

	/**
	 * 	用户登录
	 * @param id
	 * @param usePwd
	 * @return
	 * @throws Exception
	 * @throws CustomException
	 */
	ResultQuery<LetterPageData> getLetterMess(BasePara para,HttpServletRequest request) throws Exception,CustomException;
	
}
