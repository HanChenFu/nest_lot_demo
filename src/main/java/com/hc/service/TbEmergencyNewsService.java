package com.hc.service;


import com.hc.common.result.ResultData;
import com.hc.para.page_base.BasePara;
import com.hc.pojo.base.PageUtilBean;
import com.hc.pojo.entity.TbEmergencyNews;

public interface TbEmergencyNewsService {
	
	// 查询应急要闻
	ResultData<PageUtilBean> queryEmergencyNews(BasePara para);

}
