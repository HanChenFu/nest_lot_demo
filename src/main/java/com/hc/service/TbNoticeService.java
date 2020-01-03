package com.hc.service;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;

import com.hc.common.result.ResultData;
import com.hc.para.page_base.BasePara;
import com.hc.pojo.base.PageUtilBean;
import com.hc.pojo.entity.TbNotice;

public interface TbNoticeService {
	
	// 查询通知公告
	ResultData<PageUtilBean> queryNotice(BasePara para);
}
