package com.hc.service;

import com.hc.common.exception.CustomException;
import com.hc.common.result.ResultBase;
import com.hc.pojo.user.TbUser;
public interface TbUserService {
	/**
	 * 	用户登录
	 * @param id
	 * @param usePwd
	 * @return
	 * @throws Exception
	 * @throws CustomException
	 */
	ResultBase getUserMessByName(TbUser user)throws Exception,CustomException;
	
	
}
