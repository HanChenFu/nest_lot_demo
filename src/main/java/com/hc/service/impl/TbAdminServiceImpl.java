package com.hc.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hc.common.exception.CustomException;
import com.hc.common.param_checkd.annotation.ParamCheck;
import com.hc.common.redis.RedisUtil;
import com.hc.common.result.ResultBase;
import com.hc.mapper.admin.TbAdminMapper;
import com.hc.pojo.base.TbAdmin;
import com.hc.service.TbAdminService;
import com.hc.utils.redis.LoginUserUtil;
import com.hc.utils.result.ResultUtil;
import com.hc.utils.string.MD5Util;

@Service("tbAdminService")
public class TbAdminServiceImpl implements TbAdminService{

	@Autowired
	RedisUtil redis;
	
	@Autowired
	TbAdminMapper tbAdminMapper;
	
	@Autowired
	LoginUserUtil loginUserUtil;
	
	/**
	 * 后台管理员登陆
	 */
	@Override
	@Transactional
	@ParamCheck(names = {"tbAccount","tbPassword"})
	public ResultBase adminLogin(TbAdmin tbAdmin) throws Exception, CustomException {
		tbAdmin.setTbPassword(MD5Util.MD5(tbAdmin.getTbPassword()));
		TbAdmin admin = tbAdminMapper.AdminLogin(tbAdmin);
		if (admin!=null) {
			int tbId = admin.getTbId();
			if(redis.checkKey("web"+tbId)){//如果 存在则删除，防止多点登录
				loginUserUtil.logout(redis.get("web"+tbId).toString());
				loginUserUtil.setLoginUser(admin);
			}else{
				loginUserUtil.setLoginUser(admin);
			}
			return ResultUtil.getResultData(admin);
		
		}
		return ResultUtil.getResultData("用户名或密码错误");
	}


	@Override
	public ResultBase adminLogout(HttpServletRequest request) throws Exception, CustomException {
		TbAdmin admin = loginUserUtil.getLoginUser(request.getHeader("token"));
		if(admin!=null) {
			int tbId = admin.getTbId();
			loginUserUtil.logout(redis.get("web"+tbId).toString());
			return ResultUtil.getResultData("退出登录成功");
		}
		return ResultUtil.getResultData("退出登录失败");
	}

	
	
}
