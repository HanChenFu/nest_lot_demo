package com.hc.mapper.tbAreaDynamics;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;

import com.hc.pojo.entity.TbEmergencyNews;


public interface TbEmergencyNewsMapper {
	
	// 查询应急要闻
	List<T> queryEmergencyNews();
}
