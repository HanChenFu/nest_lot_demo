package com.hc.controller.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hc.common.result.ResultBase;
import com.hc.pojo.reqBean.UpdateUserPasswordReqBean;
import com.hc.service.TbAdminService;
import com.hc.service.TbUserService;

@Controller
@RequestMapping("/index/userInfo")
@ResponseBody
public class AdminCotroller {
	@Autowired
	private TbAdminService tbAdminService; 
	/**
	 * 修改用户密码与邮箱信息
	 * @param tbCaseTypeId 案件类型ID
	 * @param time 时间
	 * @param tbNumber  编号(档案号)
	 * @param tbAddress  案件地址
	 * @param tbSize  事件大小
	 * @param tbStar  关注星级
	 * @param tbCaseSaveCategory  案件保存类别（0：联网保存，1：草稿箱保存）
	 * @param pageSize  每页多少条
	 * @param page  当前页
	 * @return ResultData
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateAdminPassword", method = RequestMethod.POST,produces="application/json")
	public ResultBase updateAdminPassword(@RequestBody(required = false) UpdateUserPasswordReqBean bean) throws Exception {
		return tbAdminService.updateAdminPassword(bean);
	}
}
