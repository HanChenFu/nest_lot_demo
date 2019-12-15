package com.hc.controller.voice;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/voice")
@ResponseBody
public class VoiceCallCenterController {
	
//	private HttpClientRequest httpClientRequest;
//	
//	public HttpClientRequest getHttpClientRequest() {
//		return httpClientRequest;
//	}
//
//	public void setHttpClientRequest(HttpClientRequest httpClientRequest) {
//		this.httpClientRequest = httpClientRequest;
//	}

	Logger log=Logger.getLogger(getClass());
	private final String CALL_SERVER_URL = "http://114.116.87.75:12121/bridge/callctrl";
	private final String caller = "805";
	
	@RequestMapping("/dial")
	public String dial(String callee) throws Exception {
		
		if(callee==null || callee.equals("")) {
			return "405";
		}else {
//			System.out.println("caller:"+caller+" callee:"+callee);
			String url = CALL_SERVER_URL + "?caller="+caller+"&callee="+callee+"&authtype=no&opt=CLICK_TO_DIAL";
			HttpClientRequest httpClientRequest = new HttpClientRequest();
			String str = httpClientRequest.requestVoiceServer(url);
			log.error("-*********************************-str:"+str);
			return str;
		}
	}
	
	@RequestMapping("/hungup")
	public String hungup(String callee) throws Exception {
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

}
