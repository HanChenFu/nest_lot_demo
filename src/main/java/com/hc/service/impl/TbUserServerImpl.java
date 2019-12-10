package com.hc.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hc.common.exception.CustomException;
import com.hc.common.param_checkd.annotation.ParamCheck;
import com.hc.common.param_checkd.annotation.ParamChecks;
import com.hc.common.redis.RedisUtil;
import com.hc.common.result.ResultBase;
import com.hc.mapper.user.TbUserMapper;
import com.hc.pojo.base.TbAdmin;
import com.hc.service.TbUserServer;
import com.hc.utils.redis.LoginUserUtil;
import com.hc.utils.result.ResultUtil;

@Service("tbUserServer")
public class TbUserServerImpl implements TbUserServer{
	
	@Autowired
	RedisUtil redis;
	
	@Autowired
	TbUserMapper tbUserMapper;
	
	@Autowired
	LoginUserUtil loginUserUtil;
	
	
	@Transactional
	@ParamCheck(names = {"tbName","tbPassword"})
	public ResultBase login(TbAdmin tbAdmin) throws Exception, CustomException {
		TbAdmin user = tbUserMapper.checkUserLoginId(tbAdmin);
		if (user!=null) {
			int str = user.getTbId();
			if(redis.checkKey("web"+str)){//如果 存在则删除，防止多点登录
				loginUserUtil.logout(redis.get("web"+str).toString());
				loginUserUtil.setLoginUser(user);
			}else{
				loginUserUtil.setLoginUser(user);
			}
			return ResultUtil.getResultData(user);
		}
		return ResultUtil.getResultData("用户名或密码错误");
	}
	
	@Transactional
	@ParamCheck(names = {"tbName","tbPassword"})
	public ResultBase testzz(TbAdmin tbAdmin) throws Exception, CustomException {
//		TbAdmin t = loginUserUtil.getLoginUser(request.getHeader("token"));
		return ResultUtil.getResultData("jj");
	}

}
