package com.hc.mapper.tbAreaDynamics;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hc.pojo.entity.TbWorkDynamics;

public interface TbWorkDynamicsMapper {
	
	// 查询工作动态
	List<TbWorkDynamics> queryWorkDynamics(@Param("limitsTart")Integer limitsTart,@Param("limitsEnd")Integer limitsEnd);
	int insertWorkDynamics(TbWorkDynamics tbWorkDynamics);
	int queryWorkDynamicsCount();
	int queryWorkDynamicsTbTitle(@Param("tbTitle")String tbTitle);
}
