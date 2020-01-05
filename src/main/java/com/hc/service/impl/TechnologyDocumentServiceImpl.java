package com.hc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hc.common.code.StatusCode;
import com.hc.common.result.ResultData;
import com.hc.mapper.tbAreaDynamics.TbTechnologyDocumentMapper;
import com.hc.pojo.entity.TbTechnologyDocument;
import com.hc.service.TechnologyDocumentService;
import com.hc.utils.result.ResultUtil;

@Service("technologyDocumentService")
public class TechnologyDocumentServiceImpl implements TechnologyDocumentService{
	@Autowired
	private TbTechnologyDocumentMapper tbTechnologyDocumentMapper;
	//获取技术文档
	@Override
	public ResultData<String> getUrl() {
		TbTechnologyDocument newDocument = tbTechnologyDocumentMapper.selectNewDocument();
		if(newDocument==null){
			return ResultUtil.getResultData(true, StatusCode.SUCCESS, "没有技术文档！", "");
		}else{
			return ResultUtil.getResultData(true, StatusCode.SUCCESS, "操作成功！", newDocument.getTbUrl());
		}
	}
	
	
}
