package com.hc.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hc.common.exception.CustomException;
import com.hc.common.param_checkd.annotation.ParamCheck;
import com.hc.common.result.ResultBase;
import com.hc.mapper.tbAreaDynamics.TbCaseMapper;
import com.hc.pojo.entity.TbCase;
import com.hc.service.TbCaseService;
import com.hc.utils.result.ResultUtil;


@Service("tbCaseService")
public class TbCaseServiceImpl implements TbCaseService{
	@Autowired
	private TbCaseMapper tbCaseMapper;
	
	public int queryNumber(int tbCaseTypeId) {
		System.out.println(1);
		//System.out.println(Object(tbCaseService.queryNumber(tbCaseTypeId)));
		return tbCaseMapper.queryNumber(tbCaseTypeId);
	}

	@Override
	public List<TbCase> queryForPage(Integer tbCaseTypeId, String time, String tbNumber, String tbAddress,
			String tbSize, Integer tbStar) {
//		List<TbCase> d = tbCaseMapper.queryForPage(tbCaseTypeId, time, tbNumber, tbAddress, tbSize, tbStar);
		return tbCaseMapper.queryForPage(tbCaseTypeId, time, tbNumber, tbAddress, tbSize, tbStar);
	}

	@Override
	@ParamCheck(names = "tbId")
	public ResultBase updateCaseById(TbCase tbcase, HttpServletRequest request) throws Exception, CustomException {
		int size = tbCaseMapper.updateCaseById(tbcase);
		if (size>0) {
			return ResultUtil.getResultBase("修改成功！");
		}
		return ResultUtil.getResultBase("修改失败！");
	}
	
}
