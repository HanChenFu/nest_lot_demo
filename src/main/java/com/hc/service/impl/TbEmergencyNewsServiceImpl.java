package com.hc.service.impl;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hc.mapper.tbAreaDynamics.TbEmergencyNewsMapper;
import com.hc.pojo.entity.TbEmergencyNews;
import com.hc.service.TbEmergencyNewsService;

@Service("tbEmergencyService")
public class TbEmergencyNewsServiceImpl implements TbEmergencyNewsService{
	
	@Autowired
	private TbEmergencyNewsMapper tbEmergencyNewsMapper;
	
	@Override
	public List<TbEmergencyNews> queryEmergencyNews() {
		return tbEmergencyNewsMapper.queryEmergencyNews();
	}
	

}
