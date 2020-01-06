package com.hc.mapper.admin;

import com.hc.pojo.base.TbAdmin;
import com.hc.pojo.reqBean.UpdateUserMess;

public interface TbAdminMapper {
	
	// 登陆验证
	TbAdmin AdminLogin(TbAdmin tbAdmin);
	
	TbAdmin getAdminByTbId(UpdateUserMess bean);
	
	int updateAdminPassword(UpdateUserMess bean);
	
}