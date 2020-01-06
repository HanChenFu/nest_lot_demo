package com.hc.controller.callCenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hc.common.exception.CustomException;
import com.hc.common.result.ResultBase;
import com.hc.common.result.ResultData;
import com.hc.common.result.ResultQuery;
import com.hc.pojo.base.PageUtilBean;
import com.hc.pojo.callCenter.CallCenter;
import com.hc.pojo.callCenter.MailList;
import com.hc.service.CallCenterService;
import com.hc.service.MailListService;
import com.hc.utils.result.ResultUtil;

@Controller
@RequestMapping("/mailList")
@ResponseBody
public class MailListController {
	
	
	@Autowired
	private MailListService mailListService;
	/**
	 *  添加通讯录
	 */
	@RequestMapping("/insertMailList")
	public ResultBase insertMailList(@RequestBody(required = false) MailList mailList) throws Exception {
		return ResultUtil.getResultBase(mailListService.insertMailList(mailList)+"");
	}

	/**
	 * 查询通讯录
	 */
	@RequestMapping("getMailList")
	public ResultData<PageUtilBean> getMailList(@RequestBody(required = false) MailList mailList) throws Exception,CustomException{
		return mailListService.getMailList(mailList);
	}
	
	/**
	 * 删除通讯录
	 */
	@RequestMapping("deleteMailList")
	public ResultBase deleteMailList(@RequestBody(required = false) JSONObject jsonObject) throws Exception,CustomException{
		String mailListId = jsonObject == null ? null : jsonObject.getString("mailListId");
		String updateUserId = jsonObject == null ? null : jsonObject.getString("updateUserId");
		return mailListService.deleteMailList(mailListId==null?null:Integer.valueOf(mailListId),Integer.valueOf(updateUserId));
	}
	
	/**
	 * 修改通讯录
	 */
	@RequestMapping("updateMailList")
	public ResultBase updateMailList(@RequestBody(required = false) MailList mailList) throws Exception,CustomException{
		return mailListService.updateMailList(mailList);
	}

}
