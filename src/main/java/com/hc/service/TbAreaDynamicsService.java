package com.hc.service;

import java.util.List;

import com.hc.pojo.entity.TbAreaDynamics;

public interface TbAreaDynamicsService {
	// 查询各区动态
	List<TbAreaDynamics> queryEveryAreaDynamics();
}
