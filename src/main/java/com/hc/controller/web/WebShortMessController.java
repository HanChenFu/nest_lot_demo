package com.hc.controller.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hc.common.exception.CustomException;
import com.hc.common.result.ResultBase;
import com.hc.common.result.ResultQuery;
import com.hc.para.page_base.BasePara;
import com.hc.pojo.shortMess.TbShortPara;
import com.hc.pojo.usually.LetterPageData;
import com.hc.service.TbSMService;

@Controller
@RequestMapping("/web/sm")
@ResponseBody
public class WebShortMessController {

	@Autowired
	private TbSMService tbSMService;
	
	/**
	 * 这边是发送短信
	 * @param tbUser
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/sendSM")
	public ResultBase sendSM(@RequestBody TbShortPara shortPara, HttpServletRequest request) throws Exception {
		return tbSMService.sendSM(shortPara, request);
	}
	
	
	@RequestMapping("/getShortMess")
	ResultQuery<LetterPageData> getShortMess(@RequestBody(required = false) BasePara para,HttpServletRequest request) throws CustomException, Exception {
		return tbSMService.getShortMess(para, request);
	}
	
	@RequestMapping("/deleShortMess")
	ResultBase deleShortMess(@RequestBody(required = false) BasePara para,HttpServletRequest request) throws Exception,CustomException{
		return tbSMService.deleShort(para, request);
	}
	
}
