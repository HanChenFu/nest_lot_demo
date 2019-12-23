package com.hc.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hc.common.exception.CustomException;
import com.hc.common.param_checkd.annotation.ParamCheck;
import com.hc.common.redis.RedisUtil;
import com.hc.common.result.ResultBase;
import com.hc.mapper.shortMessFollow.TbShortMessFollowMapper;
import com.hc.pojo.shortMessFollow.TbShortMessFollow;
import com.hc.service.TbShortMessFollowService;
import com.hc.utils.redis.LoginUserUtil;
import com.hc.utils.result.ResultUtil;

@Service("tbShortMessFollowService")
public class TbShortMessFollowServiceImpl implements TbShortMessFollowService{

	@Autowired
	RedisUtil redis;
	
	@Autowired
	TbShortMessFollowMapper tbShortMessFollowMapper;
	
	@Autowired
	LoginUserUtil loginUserUtil;
	
	
	@Override
	@ParamCheck(names = {"tbShortMessId","tbAdminId"})
	public ResultBase insertSelective(TbShortMessFollow follow, HttpServletRequest request)
			throws Exception, CustomException {
		String s = tbShortMessFollowMapper.isFollow(follow);
		if (s==null) {
			int size = tbShortMessFollowMapper.insertSelective(follow);
			if (size>0) {
				return ResultUtil.getResultBase("操作成功！");
			}
		}
		return ResultUtil.getResultBase("操作失败！");
	}

	@Override
	@ParamCheck(names = {"tbShortMessId","tbAdminId"})
	public ResultBase deleteFollow(TbShortMessFollow follow, HttpServletRequest request)
			throws Exception, CustomException {
		int size = tbShortMessFollowMapper.deleteFollow(follow);
		if (size>0) {
			return ResultUtil.getResultBase("操作成功！");
		}
		return ResultUtil.getResultBase("操作失败！");
	
	}

}
