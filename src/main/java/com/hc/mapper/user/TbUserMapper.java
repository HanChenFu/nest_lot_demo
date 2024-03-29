package com.hc.mapper.user;

import java.util.List;

import com.hc.pojo.user.TbUser;

public interface TbUserMapper {
	
	/**
	 * 这边是根据用户的昵称获取用户信息
	 */
	List<TbUser> getUserMessByName(TbUser user);
	
	int getUserMessByNameCount(TbUser user);
	
	int insertSelective(TbUser user);
	
	String checkUserPhone(TbUser user);
	
	String getUserIdByEmail(String email);
	
	String getUserIdByPhone(String phone);
	
	int updateNickNameByPhoneOrEmail(TbUser tbUser);
	
	int updateUserById(TbUser tbUser);
	
//	TbUser getUserMessByUserId(int tbId);

}