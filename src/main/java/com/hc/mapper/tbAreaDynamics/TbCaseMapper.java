package com.hc.mapper.tbAreaDynamics;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hc.pojo.entity.TbCase;

public interface TbCaseMapper {
	
	int queryNumber(int tbCaseTypeId);
	
	List<TbCase> queryForPage(@Param("tbCaseTypeId")Integer tbCaseTypeId,@Param("time")String time,
			@Param("tbNumber")String tbNumber,@Param("tbAddress")String tbAddress,
			@Param("tbSize")String tbSize,@Param("tbStar")Integer tbStar);
	
	
	
	int selectCaseByConditionsCount(@Param("tbCaseTypeId")Integer tbCaseTypeId,@Param("time")String time,
			@Param("tbNumber")String tbNumber,@Param("tbAddress")String tbAddress,
			@Param("tbSize")String tbSize,@Param("tbStar")Integer tbStar,Integer tbCaseSaveCategory);
	
	List<TbCase> selectCaseByConditions(@Param("tbCaseTypeId")Integer tbCaseTypeId,@Param("time")String time,
			@Param("tbNumber")String tbNumber,@Param("tbAddress")String tbAddress,
			@Param("tbSize")String tbSize,@Param("tbStar")Integer tbStar,Integer tbCaseSaveCategory,
			@Param("limitsTart")Integer limitsTart,@Param("limitsEnd")Integer limitsEnd);
	
	int insertCase(TbCase tbCase);
	
	int updateCaseById(TbCase tbCase);
	
	int deleCase(List<Integer> list);

	TbCase queryByTbNumber(String tbNumber);
}
