package com.hc.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hc.common.exception.CustomException;
import com.hc.common.param_checkd.annotation.ParamCheck;
import com.hc.common.redis.RedisUtil;
import com.hc.common.result.ResultBase;
import com.hc.common.result.ResultQuery;
import com.hc.mapper.user.TbUserMapper;
import com.hc.pojo.base.TbAdmin;
import com.hc.pojo.user.TbUser;
import com.hc.service.TbUserService;
import com.hc.utils.redis.LoginUserUtil;
import com.hc.utils.result.ResultUtil;

@Service("tbUserServer")
public class TbUserServiceImpl implements TbUserService{
	
	@Autowired
	RedisUtil redis;
	
	@Autowired
	TbUserMapper tbUserMapper;
	
	@Autowired
	LoginUserUtil loginUserUtil;

	@Override
	@ParamCheck(names = {"nickname"})
	public ResultQuery<TbUser> getUserMessByName(TbUser tbUser, HttpServletRequest request) throws Exception, CustomException {
		List<TbUser> li = tbUserMapper.getUserMessByName(tbUser);
		if(li==null) {
			return ResultUtil.getResultQuery("没有数据！");
		}
		return ResultUtil.getResultQuery(li,tbUserMapper.getUserMessByNameCount(tbUser),tbUser.getPage(),tbUser.getSize());
	}

	@Override
	@ParamCheck(names = {"nickname","phone","tbEmail"})
	public ResultBase insertSelective(TbUser tbUser, HttpServletRequest request) throws Exception, CustomException {
		String name = tbUserMapper.checkUserPhone(tbUser);
		if(name==null) {
			int size = tbUserMapper.insertSelective(tbUser);
			if (size > 0) {
				return ResultUtil.getResultBase("新增数据成功！");
			}
			return ResultUtil.getResultBase("新增数据失败！");
		}
		return ResultUtil.getResultBase("该手机号已经存在！");
	}

	@Override
	@ParamCheck(names = {"nickname"})
	public ResultBase updateNickNameByPhoneOrEmail(TbUser tbUser, HttpServletRequest request) throws Exception, CustomException {
		int s = tbUserMapper.updateNickNameByPhoneOrEmail(tbUser);
		if (s > 0) {
			return ResultUtil.getResultBase("更新成功");
		}
		return ResultUtil.getResultBase("更新失败");
	}

	@Override
	@ParamCheck(names = {"tbId","nickname"})
	public ResultBase updateUserById(TbUser tbUser, HttpServletRequest request) throws Exception, CustomException {
		int s = tbUserMapper.updateUserById(tbUser);
		if (s > 0) {
			return ResultUtil.getResultBase("更新成功");
		}
		return ResultUtil.getResultBase("更新失败");
	}

	@Override
	public ResultBase tokenCheck(HttpServletRequest request) throws Exception, CustomException {
		TbAdmin t = loginUserUtil.getLoginUser(request.getHeader("token"));
		return ResultUtil.getResultData(t);
	}

}
