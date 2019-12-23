package com.hc.service;

import javax.servlet.http.HttpServletRequest;

import com.hc.common.exception.CustomException;
import com.hc.common.result.ResultBase;
import com.hc.pojo.shortMessFollow.TbShortMessFollow;

public interface TbShortMessFollowService {

	ResultBase insertSelective(TbShortMessFollow follow,HttpServletRequest request) throws Exception,CustomException;
	
	ResultBase deleteFollow(TbShortMessFollow follow,HttpServletRequest request) throws Exception,CustomException;
	
}
