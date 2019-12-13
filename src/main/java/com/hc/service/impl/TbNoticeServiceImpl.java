package com.hc.service.impl;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hc.mapper.tbAreaDynamics.TbNoticeMapper;
import com.hc.service.TbNoticeService;

@Service("tbNoticeService")
public class TbNoticeServiceImpl implements TbNoticeService{
	
	@Autowired
	private TbNoticeMapper tbNoticeMapper;
	
	@Override
	public List<T> queryNotice() {
		return tbNoticeMapper.queryNotice();
	}
	

}
