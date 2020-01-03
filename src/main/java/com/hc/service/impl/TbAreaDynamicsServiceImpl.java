package com.hc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hc.common.code.StatusCode;
import com.hc.common.result.ResultData;
import com.hc.mapper.tbAreaDynamics.TbAreaDynamicsMapper;
import com.hc.para.page_base.BasePara;
import com.hc.pojo.base.PageUtilBean;
import com.hc.pojo.entity.TbAreaDynamics;
import com.hc.service.TbAreaDynamicsService;
import com.hc.utils.result.ResultUtil;

@Service("tbAreaDynamicsService")
public class TbAreaDynamicsServiceImpl implements TbAreaDynamicsService{
	
	@Autowired
	private TbAreaDynamicsMapper tbAreaDynamicsMapper;
	
	public ResultData<PageUtilBean> queryEveryAreaDynamics(BasePara para) {
		
		int totalCount = tbAreaDynamicsMapper.queryEveryAreaDynamicsCount();
		PageUtilBean pages = new PageUtilBean(para.getPageSize(), totalCount, para.getPage());
		List<TbAreaDynamics> tbAreaDynamicsList = tbAreaDynamicsMapper.queryEveryAreaDynamics(pages.limitsTart(),pages.limitsEnd());
		pages.setResults(tbAreaDynamicsList);
		return ResultUtil.getResultData(true, StatusCode.SUCCESS, "操作成功！", pages);
	}

}
