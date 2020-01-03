package com.hc.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.hc.common.exception.CustomException;
import com.hc.common.result.ResultBase;
import com.hc.common.result.ResultData;
import com.hc.para.page_base.BasePara;
import com.hc.pojo.base.PageUtilBean;
import com.hc.pojo.entity.TbCase;
import com.hc.pojo.entity.TbDynamicMessageInfo;
import com.hc.pojo.reqBean.AddAndUpdateCaseReqBean;
import com.hc.pojo.reqBean.DynamicMessageGetInfoReqBean;
import com.hc.pojo.reqBean.QueryAllCaseListReqBean;

public interface TbDynamicMessageService {
	//增加动态消息
	ResultBase addInfo(TbDynamicMessageInfo bean)throws Exception;
	//删除动态消息
	ResultBase delInfo(Map<String,Object> map) throws Exception;
	//修改动态消息
	ResultBase updateInfo(TbDynamicMessageInfo bean);
	//查询所有动态消息
	ResultData<PageUtilBean> getAllInfo(DynamicMessageGetInfoReqBean bean);
	//查询动态消息详情通过ID
	ResultData<TbDynamicMessageInfo> getOneInfoById(Integer tbId);
}
