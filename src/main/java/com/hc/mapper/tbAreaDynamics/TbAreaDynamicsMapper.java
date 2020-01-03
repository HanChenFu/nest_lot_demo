package com.hc.mapper.tbAreaDynamics;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hc.pojo.entity.TbAreaDynamics;
import com.hc.pojo.entity.TbWorkDynamics;

public interface TbAreaDynamicsMapper {
	
	// 查询各区动态
	List<TbAreaDynamics> queryEveryAreaDynamics(@Param("limitsTart")Integer limitsTart,@Param("limitsEnd")Integer limitsEnd);
	
	int insertAreaDynamics(TbWorkDynamics tbWorkDynamics);
	int queryEveryAreaDynamicsCount();
	
}
