package com.hc.mapper.tbAreaDynamics;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hc.pojo.entity.TbCase;
import com.hc.pojo.entity.TbCaseType;

public interface TbCaseTypeMapper {
	
	List<TbCaseType> queryAll(@Param("limitsTart")Integer limitsTart,@Param("limitsEnd")Integer limitsEnd);
	int queryAllCount();
	
	TbCaseType queryByTbId(@Param("tbId")Integer tbId);
}
