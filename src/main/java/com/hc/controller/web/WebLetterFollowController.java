package com.hc.controller.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hc.common.exception.CustomException;
import com.hc.common.result.ResultBase;
import com.hc.pojo.letterFollow.TbLetterFollow;
import com.hc.service.TbLetterFollowService;

@Controller
@RequestMapping("/web/letterFollow")
@ResponseBody
public class WebLetterFollowController {

	@Autowired
	private TbLetterFollowService tbLetterFollowService;
	
	@RequestMapping("/insertSelective")
	ResultBase insertSelective(@RequestBody TbLetterFollow follow,HttpServletRequest request) throws Exception,CustomException {
		return tbLetterFollowService.insertSelective(follow, request);
	}
	
	@RequestMapping("/deleteFollow")
	ResultBase deleteFollow(@RequestBody TbLetterFollow follow,HttpServletRequest request) throws Exception,CustomException{
		return tbLetterFollowService.deleteFollow(follow, request);
	}
	
}
