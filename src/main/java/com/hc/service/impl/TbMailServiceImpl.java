package com.hc.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hc.common.exception.CustomException;
import com.hc.common.param_checkd.annotation.ParamCheck;
import com.hc.common.result.ResultBase;
import com.hc.pojo.email.TbEmail;
import com.hc.service.TbMailService;
import com.hc.utils.file.FileUtil;
import com.hc.utils.result.ResultUtil;

@Service("tbMailService")
public class TbMailServiceImpl implements TbMailService{

	@Autowired
	private TbAsyncTaskImpl tbAsyncTaskImpl;
	
	@Override
	@ParamCheck(names = {"to","title","content","tbAdminId"})
	public ResultBase sendMail(TbEmail tbEmail, MultipartFile file,HttpServletRequest request) throws Exception,CustomException{
		if(!FileUtil.checkEnclosureFormat(file)) {
			return ResultUtil.getResultBase("附件需为压缩格式");
		}
		tbAsyncTaskImpl.sendEmail(tbEmail,file);
	    return ResultUtil.getResultBase("邮件已经发送!");
	}
	
}
