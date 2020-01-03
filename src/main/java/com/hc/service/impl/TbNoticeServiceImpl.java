package com.hc.service.impl;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hc.common.code.StatusCode;
import com.hc.common.result.ResultData;
import com.hc.mapper.tbAreaDynamics.TbNoticeMapper;
import com.hc.para.page_base.BasePara;
import com.hc.pojo.base.PageUtilBean;
import com.hc.pojo.entity.TbNotice;
import com.hc.service.TbNoticeService;
import com.hc.utils.result.ResultUtil;

@Service("tbNoticeService")
public class TbNoticeServiceImpl implements TbNoticeService{
	
	@Autowired
	private TbNoticeMapper tbNoticeMapper;
	
	@Override
	public ResultData<PageUtilBean> queryNotice(BasePara para) {
		
		int totalCount = tbNoticeMapper.queryNoticeCount();
		PageUtilBean pages = new PageUtilBean(para.getPageSize(), totalCount, para.getPage());
		List<TbNotice> tbNoticeList = tbNoticeMapper.queryNotice(pages.limitsTart(),pages.limitsEnd());
		pages.setResults(tbNoticeList);
		return ResultUtil.getResultData(true, StatusCode.SUCCESS, "操作成功！", pages);
	}
	

}
