package com.hc.service.impl;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hc.mapper.tbAreaDynamics.TbWorkDynamicsMapper;
import com.hc.pojo.entity.TbWorkDynamics;
import com.hc.service.TbWorkDynamicsService;

@Service("tbWorkDynamicsService")
public class TbWorkDynamicsServiceImpl implements TbWorkDynamicsService{
	@Autowired
	private TbWorkDynamicsMapper tbWorkDynamicsMapper;
	
	@Override
	public List<T> queryWorkDynamics() {
		return tbWorkDynamicsMapper.queryWorkDynamics();
	}

}
