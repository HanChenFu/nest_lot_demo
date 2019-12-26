package com.hc.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hc.common.exception.CustomException;
import com.hc.common.result.ResultBase;
import com.hc.pojo.entity.TbCase;

public interface TbCaseService {
	int queryNumber(int tbCaseTypeId);

	List<TbCase> queryForPage(Integer tbCaseTypeId, String time, String tbNumber, String tbAddress, String tbSize,
			Integer tbStar);
	
	
	ResultBase updateCaseById(TbCase tbcase,HttpServletRequest request) throws Exception,CustomException;
}
