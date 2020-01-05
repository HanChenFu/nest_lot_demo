package com.hc.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hc.common.code.StatusCode;
import com.hc.common.exception.CustomException;
import com.hc.common.param_checkd.annotation.ParamCheck;
import com.hc.common.result.ResultBase;
import com.hc.pojo.email.TbEmail;
import com.hc.service.TbMailService;
import com.hc.utils.file.FileUtil;
import com.hc.utils.result.ResultUtil;
import com.hc.utils.string.FormatCheck;

@Service("tbMailService")
public class TbMailServiceImpl implements TbMailService{

	@Autowired
	private TbAsyncTaskImpl tbAsyncTaskImpl;
	 
	@Override
	@ParamCheck(names = {"to","title","content","tbAdminId"})
	public ResultBase sendMail(TbEmail tbEmail, MultipartFile file,HttpServletRequest request) throws Exception,CustomException{
		MultipartFile file2 = null;
		if(file!=null) {
			if(!FileUtil.checkEnclosureFormat(file)) {
				return ResultUtil.getResultBase("附件需为压缩格式!");
			}
			byte[] pdfFile = IOUtils.toByteArray(file.getInputStream());
			file2 = new org.springframework.mock.web.MockMultipartFile("file",file.getOriginalFilename(),ContentType.APPLICATION_OCTET_STREAM.toString(), pdfFile);
		}
		String[] str = tbEmail.getTo().split(",");
		for (int i = 0; i < str.length; i++) {
			if (!FormatCheck.isEmail(str[i])) {
				return ResultUtil.getResultBase(false, StatusCode.PARAM_ERROR, "邮箱格式不对!");
			}
		}
		tbAsyncTaskImpl.sendEmail(tbEmail,file2);
		file = null;
	    return ResultUtil.getResultBase("邮件已经发送!");
	}
	
}
