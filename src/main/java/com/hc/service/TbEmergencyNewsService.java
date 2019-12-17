package com.hc.service;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;

public interface TbEmergencyNewsService {
	
	// 查询应急要闻
	List<T> queryEmergencyNews();

}
