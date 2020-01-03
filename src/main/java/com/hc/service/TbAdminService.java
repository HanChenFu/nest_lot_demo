package com.hc.service;

import javax.servlet.http.HttpServletRequest;

import com.hc.common.exception.CustomException;
import com.hc.common.result.ResultBase;
import com.hc.pojo.base.TbAdmin;
import com.hc.pojo.reqBean.UpdateUserPasswordReqBean;

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
	
	
	/**
	 * 	退出登录
	 * @param id
	 * @param usePwd
	 * @return
	 * @throws Exception
	 * @throws CustomException
	 */
	ResultBase adminLogout(HttpServletRequest request)throws Exception,CustomException;
	
	//修改用户密码与邮箱信息
	ResultBase updateAdminPassword(UpdateUserPasswordReqBean bean) throws Exception,CustomException;
}
