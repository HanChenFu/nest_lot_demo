package com.hc.mapper.tbAreaDynamics;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hc.pojo.entity.TbEmergencyNews;
import com.hc.pojo.entity.TbWorkDynamics;


public interface TbEmergencyNewsMapper {
	
	// 查询应急要闻
	List<TbEmergencyNews> queryEmergencyNews(@Param("limitsTart")Integer limitsTart,@Param("limitsEnd")Integer limitsEnd);
	int insertEmergencyNews(TbWorkDynamics tbWorkDynamics);
	int queryEmergencyNewsCount();
}
