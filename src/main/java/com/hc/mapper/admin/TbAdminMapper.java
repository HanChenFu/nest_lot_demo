package com.hc.mapper.admin;

import com.hc.pojo.base.TbAdmin;
import com.hc.pojo.reqBean.UpdateUserPasswordReqBean;
import com.hc.pojo.user.TbUser;

public interface TbAdminMapper {
	
	// 登陆验证
	TbAdmin AdminLogin(TbAdmin tbAdmin);
	
	
	
	TbAdmin getAdminByTbId(Integer tbId);
	int updateAdminPassword(UpdateUserPasswordReqBean bean);
}