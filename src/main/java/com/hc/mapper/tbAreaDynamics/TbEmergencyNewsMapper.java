package com.hc.mapper.tbAreaDynamics;

import java.util.List;
import com.hc.pojo.entity.TbEmergencyNews;
import com.hc.pojo.entity.TbWorkDynamics;


public interface TbEmergencyNewsMapper {
	
	// 查询应急要闻
	List<TbEmergencyNews> queryEmergencyNews();
	int insertEmergencyNews(TbWorkDynamics tbWorkDynamics);
}
