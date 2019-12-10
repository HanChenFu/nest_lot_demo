package com.hc.service;

import com.hc.common.exception.CustomException;
import com.hc.common.result.ResultBase;
import com.hc.pojo.base.TbAdmin;

public interface TbAdminService {
	/**
	 * 	用户登录
	 * @param id
	 * @param usePwd
	 * @return
	 * @throws Exception
	 * @throws CustomException
	 */
	ResultBase adminLogin(TbAdmin tbAdmin)throws Exception,CustomException;
}
