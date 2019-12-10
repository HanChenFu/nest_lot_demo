package com.hc.mapper.admin;

import com.hc.pojo.base.TbAdmin;

public interface TbAdminMapper {
	
	// 登陆验证
	TbAdmin AdminLogin(TbAdmin tbAdmin);
	
}