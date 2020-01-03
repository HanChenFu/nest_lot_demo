package com.hc.service.impl;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hc.common.code.StatusCode;
import com.hc.common.result.ResultData;
import com.hc.mapper.tbAreaDynamics.TbEmergencyNewsMapper;
import com.hc.para.page_base.BasePara;
import com.hc.pojo.base.PageUtilBean;
import com.hc.pojo.entity.TbEmergencyNews;
import com.hc.service.TbEmergencyNewsService;
import com.hc.utils.result.ResultUtil;

@Service("tbEmergencyService")
public class TbEmergencyNewsServiceImpl implements TbEmergencyNewsService{
	
	@Autowired
	private TbEmergencyNewsMapper tbEmergencyNewsMapper;
	
	@Override
	public ResultData<PageUtilBean> queryEmergencyNews(BasePara para) {
		
		int totalCount = tbEmergencyNewsMapper.queryEmergencyNewsCount();
		PageUtilBean pages = new PageUtilBean(para.getPageSize(), totalCount, para.getPage());
		List<TbEmergencyNews> tbEmergencyNewsList = tbEmergencyNewsMapper.queryEmergencyNews(pages.limitsTart(),pages.limitsEnd());
		pages.setResults(tbEmergencyNewsList);
		return ResultUtil.getResultData(true, StatusCode.SUCCESS, "操作成功！", pages);
	}
	

}
