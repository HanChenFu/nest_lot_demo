package com.hc.mapper.user;

import com.hc.pojo.base.TbAdmin;

public interface TbUserMapper {
	
	// 登陆验证
	TbAdmin checkUserLoginId(TbAdmin tbAdmin);
	
}