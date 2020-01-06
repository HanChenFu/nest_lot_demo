package com.hc.mapper.tbAreaDynamics;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hc.pojo.entity.TbCase;
import com.hc.pojo.entity.TbDynamicMessageInfo;

public interface TbDynamicMessageInfoMapper {
    int deleteByPrimaryKey(Integer tbId);

    int insert(TbDynamicMessageInfo record);

    int insertSelective(TbDynamicMessageInfo record);

    TbDynamicMessageInfo selectByPrimaryKey(Integer tbId);

    int updateByPrimaryKeySelective(TbDynamicMessageInfo record);

    int updateByPrimaryKeyWithBLOBs(TbDynamicMessageInfo record);

    int updateByPrimaryKey(TbDynamicMessageInfo record);
    
    //删除
    int deleDynamicMessage(List<Integer> list);
    
	int selectDynamicMessageAllCount(@Param("tbType")String tbType);
	
	List<TbDynamicMessageInfo> selectDynamicMessageAll(@Param("tbType")String tbType,
			@Param("limitsTart")Integer limitsTart,@Param("limitsEnd")Integer limitsEnd);
}