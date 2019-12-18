package com.hc.service;

import javax.servlet.http.HttpServletRequest;

import com.hc.common.exception.CustomException;
import com.hc.common.result.ResultBase;
import com.hc.common.result.ResultQuery;
import com.hc.para.page_base.BasePara;
import com.hc.pojo.askRecord.TbAskRecord;

public interface TbAskRecordService {

	ResultQuery<TbAskRecord> getAskRecord(TbAskRecord ask,HttpServletRequest request) throws Exception,CustomException;
	
	ResultBase insertSelective(TbAskRecord ask,HttpServletRequest request) throws Exception,CustomException;
	
	ResultBase deleAskRecord(BasePara base,HttpServletRequest request) throws Exception,CustomException;
	
	ResultBase updateAskRecord(TbAskRecord ask,HttpServletRequest request) throws Exception,CustomException;
	
}
