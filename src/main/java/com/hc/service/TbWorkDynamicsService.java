package com.hc.service;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;

import com.hc.pojo.entity.TbWorkDynamics;

public interface TbWorkDynamicsService {
	// 查询工作动态
	List<T> queryWorkDynamics();

}
