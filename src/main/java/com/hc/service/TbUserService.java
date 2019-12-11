package com.hc.service;

import com.hc.common.exception.CustomException;
import com.hc.common.result.ResultBase;
import com.hc.pojo.base.TbAdmin;

public interface TbUserService {
	
	/**
	 * 	用户登录
	 * @param id
	 * @param usePwd
	 * @return
	 * @throws Exception
	 * @throws CustomException
	 */
	ResultBase login(TbAdmin tbAdmin)throws Exception,CustomException;
	
	ResultBase testzz(TbAdmin tbAdmin) throws Exception,CustomException;
	
}
