package com.hc.mapper.tbAreaDynamics;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;

import com.hc.pojo.entity.TbWorkDynamics;

public interface TbWorkDynamicsMapper {
	
	// 查询工作动态
	List<T> queryWorkDynamics();
}
