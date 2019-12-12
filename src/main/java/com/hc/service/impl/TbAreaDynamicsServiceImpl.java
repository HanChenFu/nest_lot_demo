package com.hc.service.impl;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hc.mapper.tbAreaDynamics.TbAreaDynamicsMapper;
import com.hc.service.TbAreaDynamicsService;

@Service("tbAreaDynamicsService")
public class TbAreaDynamicsServiceImpl implements TbAreaDynamicsService{
	
	@Autowired
	private TbAreaDynamicsMapper tbAreaDynamicsMapper;
	
	public List<T> queryEveryAreaDynamics() {
		return tbAreaDynamicsMapper.queryEveryAreaDynamics();
	}

}
