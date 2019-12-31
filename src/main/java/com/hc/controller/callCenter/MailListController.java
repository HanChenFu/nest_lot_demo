package com.hc.controller.callCenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hc.common.result.ResultBase;
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

}
