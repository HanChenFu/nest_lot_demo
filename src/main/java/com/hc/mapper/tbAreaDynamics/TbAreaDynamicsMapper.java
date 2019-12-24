package com.hc.mapper.tbAreaDynamics;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;

import com.hc.pojo.entity.TbAreaDynamics;
import com.hc.pojo.entity.TbWorkDynamics;

public interface TbAreaDynamicsMapper {
	
	// 查询各区动态
	List<T> queryEveryAreaDynamics();
	int insertAreaDynamics(TbWorkDynamics tbWorkDynamics);
	
}
