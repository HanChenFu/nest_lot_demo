package com.hc.controller.onlineService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hc.common.code.StatusCode;
import com.hc.common.result.ResultData;
import com.hc.service.TbOnlineServiceService;
import com.hc.utils.result.ResultUtil;

@Controller
@RequestMapping("/onlineService")
@ResponseBody
public class OnlineServiceController {
	
	@Autowired
	private TbOnlineServiceService tbOnlineServiceService;
	/**
	 *   拨打电话
	 * callee 拨打号码
	 * str 返回参数
	 */
	@RequestMapping("/chat")
	public  ResultData<String> chat(@RequestBody(required = false) JSONObject jsonObject) throws Exception {
		
		String chatContent = jsonObject == null ? null : jsonObject.getString("chatContent");
		if(chatContent==null || "".equals(chatContent)) {
			return ResultUtil.getResultData(true,StatusCode.SUCCESS,"您好，请问有什么可以帮到您", null);
		}
		String chatContents =  tbOnlineServiceService.getOnlineServiceChatContent(chatContent);
		if(chatContents==null || "".equals(chatContents)) {
			chatContents = "你问的问题太深奥了，可以重新再问一次吗^_^";
		}
		return ResultUtil.getResultData(true,StatusCode.SUCCESS,chatContents, null);
	}
	

}
