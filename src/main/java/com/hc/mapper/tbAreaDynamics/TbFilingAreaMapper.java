package com.hc.mapper.tbAreaDynamics;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hc.pojo.entity.TbCase;
import com.hc.pojo.entity.TbCaseType;
import com.hc.pojo.entity.TbFilingArea;

public interface TbFilingAreaMapper {
	
	List<TbFilingArea> queryAll(@Param("limitsTart")Integer limitsTart,@Param("limitsEnd")Integer limitsEnd);
	int queryAllCount();
	
	TbFilingArea queryByTbId(@Param("tbId")Integer tbId);
}
