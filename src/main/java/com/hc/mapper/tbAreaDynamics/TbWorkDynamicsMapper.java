package com.hc.mapper.tbAreaDynamics;

import java.util.List;

import com.hc.pojo.entity.TbWorkDynamics;

public interface TbWorkDynamicsMapper {
	
	// 查询工作动态
	List<TbWorkDynamics> queryWorkDynamics();
	int insertWorkDynamics(TbWorkDynamics tbWorkDynamics);
}
