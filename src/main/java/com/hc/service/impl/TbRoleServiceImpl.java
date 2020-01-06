package com.hc.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hc.common.exception.CustomException;
import com.hc.common.param_checkd.annotation.ParamCheck;
import com.hc.common.result.ResultQuery;
import com.hc.mapper.role.TbRoleMapper;
import com.hc.pojo.role.Role;
import com.hc.service.TbRoleService;
import com.hc.utils.result.ResultUtil;

@Service("tbRoleServer")
public class TbRoleServiceImpl implements TbRoleService{
	
	@Autowired
	TbRoleMapper tbRoleMapper;

	/**
	 */
	@Override
	@Transactional
	@ParamCheck(names = {"roleName"})
	public ResultQuery<Role> getRoleMess(Role role ,HttpServletRequest request) throws Exception, CustomException {
		List<Role> r = tbRoleMapper.getRoleMess(role);
		if (role==null) {
			 return ResultUtil.getResultQuery("没有数据！");
		}
		return ResultUtil.getResultQuery(r,tbRoleMapper.getRoleMessCount(role),role.getPage(),role.getSize());
	}

}
