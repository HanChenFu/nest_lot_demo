package com.hc.controller.callCenter;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hc.common.code.StatusCode;
import com.hc.common.exception.CustomException;
import com.hc.common.result.ResultData;
import com.hc.common.result.ResultQuery;
import com.hc.para.page_base.BasePara;
import com.hc.pojo.callCenter.CallCenter;
import com.hc.service.CallCenterService;
import com.hc.utils.result.ResultUtil;


@Controller
@RequestMapping("/voice")
@ResponseBody
public class CallCenterController {
	
//	private HttpClientRequest httpClientRequest;
//	
//	public HttpClientRequest getHttpClientRequest() {
//		return httpClientRequest;
//	}
//
//	public void setHttpClientRequest(HttpClientRequest httpClientRequest) {
//		this.httpClientRequest = httpClientRequest;
//	}
	@Autowired
	private CallCenterService callCenterService;
	
	Logger log=Logger.getLogger(getClass());
	private final String CALL_SERVER_URL = "http://114.116.87.75:12121/bridge/callctrl";
	private final String caller = "805";
	
	/**
	 * 拨打电话
	 * callee 拨打号码
	 * str 返回参数
	 */
	@RequestMapping("/dial") 
	public ResultData<CallCenter> dial(@RequestBody(required = false) JSONObject jsonObject) throws Exception {
		String callee = jsonObject == null ? null : jsonObject.getString("callee");
		if(callee==null || callee.equals("")) {
			ResultUtil.getResultData(false,StatusCode.NULL,"号码为空",null);
		}else {
//			System.out.println("caller:"+caller+" callee:"+callee);
			String url = CALL_SERVER_URL + "?caller="+caller+"&callee="+callee+"&authtype=no&opt=CLICK_TO_DIAL";
			HttpClientRequest httpClientRequest = new HttpClientRequest();
			String str = httpClientRequest.requestVoiceServer(url);
			log.error("-*********************************-str:"+str);
			Integer index = callCenterService.insertCallCenter(callee);
			CallCenter callCenter = new CallCenter();
			callCenter.setTbId(index);
			return ResultUtil.getResultData(true,StatusCode.SUCCESS,"拨打中...",callCenter);
		}
		return ResultUtil.getResultData(false,StatusCode.NULL,"号码为空",null);
	}
	
	/**
	   *   挂断电话
	 * callee 挂断号码
	 * str 返回参数
	 */
	@RequestMapping("/hungup")
	public String hungup(@RequestBody(required = false) JSONObject jsonObject) throws Exception {
		String callee = jsonObject == null ? null : jsonObject.getString("callee");
		if( callee==null || callee.equals("")) {
			return "405";
		}else {
//			System.out.println("caller:"+caller+" callee:"+callee);
			String url = CALL_SERVER_URL + "?caller="+caller+"&callee="+callee+"&opt=CLICK_TO_HUNGUP";
			HttpClientRequest httpClientRequest = new HttpClientRequest();
			String str = httpClientRequest.requestVoiceServer(url);
			log.error("-*********************************-str:"+str);
			return str;
		}
	}
	
	/**
	 * 查询通话记录
	 */
	@RequestMapping("getCallCenterRecord")
	ResultQuery<CallCenter> getCallCenterRecord(@RequestBody(required = false) CallCenter callCenter) throws Exception,CustomException{
		return callCenterService.getCallCenterRecord(callCenter);
	}
	
	
}
