package com.hc.mapper.tbAreaDynamics;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;

import com.hc.pojo.entity.TbAreaDynamics;

public interface TbAreaDynamicsMapper {
	
	// 查询各区动态
	List<T> queryEveryAreaDynamics();
	
}
