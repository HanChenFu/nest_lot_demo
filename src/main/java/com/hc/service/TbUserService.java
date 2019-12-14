package com.hc.service;

import javax.servlet.http.HttpServletRequest;

import com.hc.common.exception.CustomException;
import com.hc.common.result.ResultBase;
import com.hc.common.result.ResultQuery;
import com.hc.pojo.user.TbUser;
public interface TbUserService {
	/**
	 * 	根据关键字查询用户
	 * @param id
	 * @param usePwd
	 * @return
	 * @throws Exception
	 * @throws CustomException
	 */
	ResultQuery<TbUser> getUserMessByName(TbUser tbUser, HttpServletRequest request)throws Exception,CustomException;
	
	ResultBase insertSelective(TbUser tbUser, HttpServletRequest request)throws Exception,CustomException;
}
