package com.hc.mapper.tbAreaDynamics;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.formula.functions.T;

import com.hc.pojo.entity.TbNotice;
import com.hc.pojo.entity.TbWorkDynamics;

public interface TbNoticeMapper {
	
	// 查询通知公告
	List<TbNotice> queryNotice(@Param("limitsTart")Integer limitsTart,@Param("limitsEnd")Integer limitsEnd);
	int insertNotice(TbWorkDynamics tbWorkDynamics);
	int queryNoticeCount();
	int queryNoticeTbTitle(@Param("tbTitle")String tbTitle);
	

}
