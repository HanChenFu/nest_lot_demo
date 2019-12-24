package com.hc.service;

import java.util.List;

import com.hc.common.exception.CustomException;
import com.hc.pojo.entity.TbWorkDynamics;

public interface TbWorkDynamicsService {
	// 查询工作动态
	List<TbWorkDynamics> queryWorkDynamics();

	int insertReptileData(TbWorkDynamics tbWorkDynamics) throws Exception, CustomException;

}
