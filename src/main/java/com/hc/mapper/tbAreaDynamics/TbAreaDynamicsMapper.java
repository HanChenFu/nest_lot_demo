package com.hc.mapper.tbAreaDynamics;

import java.util.List;

import com.hc.pojo.entity.TbAreaDynamics;
import com.hc.pojo.entity.TbWorkDynamics;

public interface TbAreaDynamicsMapper {
	
	// 查询各区动态
	List<TbAreaDynamics> queryEveryAreaDynamics();
	
	int insertAreaDynamics(TbWorkDynamics tbWorkDynamics);
	
}
