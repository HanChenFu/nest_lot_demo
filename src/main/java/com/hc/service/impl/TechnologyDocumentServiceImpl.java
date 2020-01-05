package com.hc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hc.common.result.ResultData;
import com.hc.mapper.tbAreaDynamics.TbCaseMapper;
import com.hc.service.TechnologyDocumentService;

@Service("technologyDocumentServiceImpl")
public class TechnologyDocumentServiceImpl implements TechnologyDocumentService{
	@Autowired
	private TbCaseMapper tbCaseMapper;

	@Override
	public ResultData<String> getUrl() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
