package com.hc.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.hc.common.exception.CustomException;
import com.hc.common.result.ResultBase;
import com.hc.pojo.entity.TbCase;

public interface TbCaseService {
	int queryNumber(int tbCaseTypeId);

	List<TbCase> queryForPage(Integer tbCaseTypeId, String time, String tbNumber, String tbAddress, String tbSize,
			Integer tbStar);
	
	
	ResultBase updateCaseById(MultipartFile file, Integer tbCaseTypeId, Integer tbFilingAreaId,String tbReportAddress,String tbSize,int tbStar,String tbAddress,String tbDesc,String tbRemarks,Double tbLongitude,Double tbLatitude,String tbId,HttpServletRequest request) throws Exception,CustomException;
}
