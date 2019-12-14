package com.hc.mapper.user;

import java.util.List;

import com.hc.pojo.user.TbUser;

public interface TbUserMapper {
	
	// 登陆验证
//	TbAdmin checkUserLoginId(TbAdmin tbAdmin);
	/**
	 * 这边是根据用户的昵称获取用户信息
	 */
	List<TbUser> getUserMessByName(TbUser user);
	
}