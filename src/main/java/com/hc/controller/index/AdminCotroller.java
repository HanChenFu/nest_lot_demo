package com.hc.controller.index;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hc.common.result.ResultBase;
import com.hc.common.result.ResultData;
import com.hc.pojo.reqBean.UpdateUserMess;
import com.hc.pojo.resBean.ResGetNameAndEmail;
import com.hc.service.TbAdminService;

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
	 * @return ResultData
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateAdminMess", method = RequestMethod.POST,produces="application/json")
	public ResultBase updateAdminMess(@RequestBody(required = false) UpdateUserMess bean,HttpServletRequest request) throws Exception {
		return tbAdminService.updateAdminPassword(bean,request);
	}
	/**
	 * 查询用户密码与邮箱信息
	 * @param tbCaseTypeId 案件类型ID
	 * @param time 时间
	 * @param tbNumber  编号(档案号)
	 * @param tbAddress  案件地址
	 * @param tbSize  事件大小
	 * @param tbStar  关注星级
	 * @param tbCaseSaveCategory  案件保存类别（0：联网保存，1：草稿箱保存）
	 * @return ResultData
	 * @throws Exception
	 */
	@RequestMapping(value = "/getNameAndEmail", method = RequestMethod.POST,produces="application/json")
	public ResultData<ResGetNameAndEmail> getNameAndEmail(@RequestBody(required = false)  UpdateUserMess bean) throws Exception {
		return tbAdminService.getNameAndEmail(bean);
	}
}
