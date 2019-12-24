package com.hc.controller.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hc.common.exception.CustomException;
import com.hc.common.result.ResultBase;
import com.hc.pojo.shortMessFollow.TbShortMessFollow;
import com.hc.service.TbShortMessFollowService;

@Controller
@RequestMapping("/web/shortMessFollow")
@ResponseBody
public class WebShortMessFollowController {

	@Autowired
	private TbShortMessFollowService tbShortMessFollowService;
	
	@RequestMapping("/insertSelective")
	ResultBase insertSelective(@RequestBody TbShortMessFollow follow,HttpServletRequest request) throws Exception,CustomException {
		return tbShortMessFollowService.insertSelective(follow, request);
	}
	
	@RequestMapping("/deleteFollow")
	ResultBase deleteFollow(@RequestBody TbShortMessFollow follow,HttpServletRequest request) throws Exception,CustomException{
		return tbShortMessFollowService.deleteFollow(follow, request);
	}
	
}
