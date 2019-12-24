package com.hc.mapper.shortMessFollow;

import com.hc.pojo.shortMessFollow.TbShortMessFollow;

public interface TbShortMessFollowMapper {
	
	int insertSelective(TbShortMessFollow follow);
	
	int deleteFollow(TbShortMessFollow follow);
	
	String isFollow(TbShortMessFollow follow);
	
}