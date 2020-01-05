package com.hc.mapper.tbAreaDynamics;

import com.hc.pojo.entity.TbTechnologyDocument;

public interface TbTechnologyDocumentMapper {
    int deleteByPrimaryKey(Integer tbId);

    int insert(TbTechnologyDocument record);

    int insertSelective(TbTechnologyDocument record);

    TbTechnologyDocument selectByPrimaryKey(Integer tbId);

    int updateByPrimaryKeySelective(TbTechnologyDocument record);

    int updateByPrimaryKey(TbTechnologyDocument record);
    
    //获取技术文档
    TbTechnologyDocument selectNewDocument();
}