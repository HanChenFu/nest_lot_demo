package com.hc.service;

import javax.servlet.http.HttpServletRequest;

import com.hc.common.exception.CustomException;
import com.hc.common.result.ResultBase;
import com.hc.pojo.letterFollow.TbLetterFollow;

public interface TbLetterFollowService {

	ResultBase insertSelective(TbLetterFollow follow,HttpServletRequest request) throws Exception,CustomException;
	
	ResultBase deleteFollow(TbLetterFollow follow,HttpServletRequest request) throws Exception,CustomException;
	
}
