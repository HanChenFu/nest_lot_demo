package com.hc.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hc.common.exception.CustomException;
import com.hc.common.result.ResultBase;
import com.hc.pojo.email.TbEmail;
import com.hc.service.TbSMService;
import com.hc.utils.result.ResultUtil;

@Service("tbSMService")
public class TbSMServiceImpl implements TbSMService{
	
	@Autowired
	private TbAsyncTaskImpl tbAsyncTaskImpl;
	
	@Override
	public ResultBase sendSM(TbEmail tbEmail, HttpServletRequest request) throws Exception, CustomException {
		tbAsyncTaskImpl.sendSM(tbEmail);
	   return ResultUtil.getResultBase("短信已经发送!");
	}

}
