package com.hc.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hc.common.exception.CustomException;
import com.hc.common.param_checkd.annotation.ParamCheck;
import com.hc.common.redis.RedisUtil;
import com.hc.common.result.ResultBase;
import com.hc.mapper.letterFollow.TbLetterFollowMapper;
import com.hc.pojo.letterFollow.TbLetterFollow;
import com.hc.service.TbLetterFollowService;
import com.hc.utils.redis.LoginUserUtil;
import com.hc.utils.result.ResultUtil;

@Service("tbLetterFollowService")
public class TbLetterFollowServiceImpl implements TbLetterFollowService{

	@Autowired
	RedisUtil redis;
	
	@Autowired
	TbLetterFollowMapper tbLetterFollowMapper;
	
	@Autowired
	LoginUserUtil loginUserUtil;
	
	@Override
	@ParamCheck(names = {"tbLetterId","tbAdminId"})
	public ResultBase insertSelective(TbLetterFollow follow, HttpServletRequest request)
			throws Exception, CustomException {
		String s = tbLetterFollowMapper.isFollow(follow);
		if (s==null) {
			int size = tbLetterFollowMapper.insertSelective(follow);
			if (size>0) {
				return ResultUtil.getResultBase("操作成功！");
			}
		}
		return ResultUtil.getResultBase("操作失败！");
	}

	@Override
	@ParamCheck(names = {"tbLetterId","tbAdminId"})
	public ResultBase deleteFollow(TbLetterFollow follow, HttpServletRequest request)
			throws Exception, CustomException {
		int size = tbLetterFollowMapper.deleteFollow(follow);
		if (size>0) {
			return ResultUtil.getResultBase("操作成功！");
		}
		return ResultUtil.getResultBase("操作失败！");
	}

}
