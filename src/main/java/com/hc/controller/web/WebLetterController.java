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
import com.hc.pojo.letter.TbLetter;
import com.hc.pojo.usually.LetterPageData;
import com.hc.service.TbLetterService;

@Controller
@RequestMapping("/web/letter")
@ResponseBody
public class WebLetterController {

	@Autowired
	private TbLetterService tbLetterService;
	
	@RequestMapping("/getLetterMess")
	ResultQuery<LetterPageData> getLetterMess(@RequestBody(required = false) BasePara para,HttpServletRequest request) throws CustomException, Exception {
		return tbLetterService.getLetterMess(para, request);
	}
	
	@RequestMapping("/insertSelective")
	ResultBase insertSelective(@RequestBody TbLetter letter,HttpServletRequest request) throws Exception,CustomException{
		return tbLetterService.insertSelective(letter, request);
	}
	
	@RequestMapping("/deleLetter")
	ResultBase deleLetter(@RequestBody(required = false) BasePara para,HttpServletRequest request) throws Exception,CustomException{
		return tbLetterService.deleLetter(para, request);
	}
	
}
