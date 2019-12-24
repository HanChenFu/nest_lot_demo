package com.hc.mapper.tbAreaDynamics;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;

import com.hc.pojo.entity.TbWorkDynamics;

public interface TbNoticeMapper {
	
	// 查询通知公告
	List<T> queryNotice();
	int insertNotice(TbWorkDynamics tbWorkDynamics);

}
