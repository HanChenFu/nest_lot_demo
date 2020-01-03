package com.hc.mapper.tbAreaDynamics;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hc.pojo.entity.TbCase;
import com.hc.pojo.reqBean.AddAndUpdateCaseReqBean;

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
	int insertCase2(AddAndUpdateCaseReqBean addAndUpdateCaseReqBean);
	
	int updateCaseById(TbCase tbCase);
	int updateCase(AddAndUpdateCaseReqBean addAndUpdateCaseReqBean);
	
	int deleCase(List<Integer> list);

	TbCase queryByTbNumber(String tbNumber);
	TbCase queryByTbId(Integer tbId);
}
