package com.hc.service.impl;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import com.hc.common.exception.CustomException;
import com.hc.common.param_checkd.annotation.ParamCheck;
import com.hc.common.result.ResultBase;
import com.hc.pojo.email.TbEmail;
import com.hc.service.TbMailService;
import com.hc.utils.result.ResultUtil;

@Service("tbMailService")
public class TbMailServiceImpl implements TbMailService{

	private TbAsyncTaskImpl tbAsyncTaskImpl;
	
	@Override
	@ParamCheck(names = {"to","title","content","tbAdminId"})
	public ResultBase sendMail(TbEmail tbEmail, HttpServletRequest request) throws Exception,CustomException{
		tbAsyncTaskImpl.sendEmail(tbEmail);
        return ResultUtil.getResultBase("邮件已经发送!");
	}
	
}
