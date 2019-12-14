package com.hc.mapper.letterFollow;

import com.hc.pojo.letterFollow.TbLetterFollow;

public interface TbLetterFollowMapper {
	
	int insertSelective(TbLetterFollow follow);
	
	int deleteFollow(TbLetterFollow follow);
	
}