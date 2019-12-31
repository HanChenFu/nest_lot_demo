package com.hc.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.hc.common.exception.CustomException;
import com.hc.common.result.ResultBase;
import com.hc.common.result.ResultData;
import com.hc.common.result.ResultQuery;
import com.hc.para.page_base.BasePara;
import com.hc.pojo.base.PageUtilBean;
import com.hc.pojo.entity.TbCase;
import com.hc.pojo.reqBean.QueryAllCaseListReqBean;

public interface TbCaseService {
	int queryNumber(int tbCaseTypeId);

	List<TbCase> queryForPage(Integer tbCaseTypeId, String time, String tbNumber, String tbAddress, String tbSize,
			Integer tbStar);
	
	
	ResultBase updateCaseById(MultipartFile file, Integer tbCaseTypeId, Integer tbFilingAreaId,String tbReportAddress,String tbSize,Integer tbStar,String tbAddress,String tbDesc,String tbRemarks,Double tbLongitude,Double tbLatitude,String tbId,String caseTime,String filedTime,HttpServletRequest request) throws Exception,CustomException;
	
	ResultBase deleCase(BasePara para,HttpServletRequest request) throws Exception,CustomException;


	//获取案件编号
	ResultData<PageUtilBean> queryAllCaseList(QueryAllCaseListReqBean bean);
	//获取案件编号
	ResultData<Map<String,Object>> getCaseSerialNum();
	//获取案件类型列表
	ResultData<PageUtilBean> getCaseTypeList();
	//获取报案地址列表
	ResultData<PageUtilBean> getReportAddressList();
	//导出Excel/word/PDF案件文件 
	ResultData<Map<String,Object>> exportCaseFile(String tbNumber,int type);
	//删除导出的Excel/word/PDF案件文件 
	ResultData<Object> deleteExportCaseFile(String tbNumber,int type);

}
