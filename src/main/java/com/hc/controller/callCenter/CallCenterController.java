package com.hc.controller.callCenter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URLDecoder;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hc.common.code.StatusCode;
import com.hc.common.exception.CustomException;
import com.hc.common.result.ResultBase;
import com.hc.common.result.ResultData;
import com.hc.common.result.ResultQuery;
import com.hc.controller.callCenter.webSocke.ImportDictValueSocket;
import com.hc.pojo.callCenter.CallCenter;
import com.hc.service.CallCenterService;
import com.hc.utils.conig.SystemConfigUtil;
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
	
	/*Logger log=Logger.getLogger(getClass());*/
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
//			log.error("-*********************************-str:"+str);
			int tbState = 0;
			if(str.trim().equals("200")) {
				tbState = 1;
			}
			Integer index = callCenterService.insertCallCenter(callee,tbState);
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
//			log.error("-*********************************-str:"+str);	
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
	
	/**
	   *   接收语音实时翻译成文本
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/voiceTranslate")
	public String voiceTranslate(HttpServletRequest request,HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String caller = request.getParameter("caller");
		String callee = request.getParameter("callee");
		String callid = request.getParameter("callid");
		String txt = request.getParameter("txt");
		txt = URLDecoder.decode(txt, "UTF-8");
		System.out.println("caller:"+caller+"===callee:"+callee+"===callid:"+callid+"===txt:"+txt);
		 
		return "caller:"+caller+"===callee:"+callee+"===callid:"+callid+"===txt:"+txt;
		 
	}
	
	/**
	   *   测试消息推送
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/testNewsText")
	public String testNewsText(HttpServletRequest request,HttpServletResponse response, @PathParam("phoneNumber") String phoneNumber) throws Exception {
		 
		String d = "";
//		 CopyOnWriteArraySet<ImportDictValueSocket> webSocket = ImportDictValueSocket.webSocketSet;
//		 for (Iterator<ImportDictValueSocket> iterator = webSocket.iterator(); iterator.hasNext();) {
//			ImportDictValueSocket importDictValueSocket = (ImportDictValueSocket) iterator.next();
//			Session session = importDictValueSocket.getSession();
//			d = d + session.getId();
//			importDictValueSocket.sendMessage("okokokokokok");
//			System.out.println("session.Id:====="+session.getId());
//		}
		
		ConcurrentHashMap<String, ImportDictValueSocket> webSocket = ImportDictValueSocket.webSocketMap;
		ImportDictValueSocket importDictValueSocket = webSocket.get(phoneNumber);
		Session session = importDictValueSocket.getSession();
		d = d + session.getId()+"==="+phoneNumber;
		importDictValueSocket.sendMessage("okokokokokok"+phoneNumber);
		System.out.println("session.Id:====="+session.getId());
		
		String path = SystemConfigUtil.getValue("upload_path")+phoneNumber+".txt";
		File file = new File(path);
		if(!file.exists()){
          file.getParentFile().mkdirs();          
		}
		file.createNewFile();

		// write
		FileWriter fw = new FileWriter(file, true);
		BufferedWriter bw = new BufferedWriter(fw);
		String tt = "哈喽，哈喽，  你好你好\r\n";
		bw.write(tt);
		bw.flush();
		bw.close();
		fw.close();
      
		/*
		 * // read FileReader fr = new FileReader(file); BufferedReader br = new
		 * BufferedReader(fr); String str = br.readLine(); System.out.println(str);
		 */
		return d;
		 
	}
	
	/*
	 * 获取 翻译语音  把保存的文本读出来
	 */
	@SuppressWarnings("resource")
	@RequestMapping("/fileReader")
	public ResultBase fileReader(@RequestBody(required = false) JSONObject jsonObject) throws Exception {
		 
	  String str = "";
	  String phoneNumber = jsonObject == null ? null : jsonObject.getString("phoneNumber");
	  String path = SystemConfigUtil.getValue("upload_path")+phoneNumber+".txt";
      File file = new File(path);
      
      if(file.exists()){
      	FileReader fr = new FileReader(file);
	        BufferedReader br = new BufferedReader(fr);
	        
	        while(br.read()!=-1){
	          str += br.readLine()+"\r\n"; 
	        } 
//	        str = br.readLine();
	        System.out.println(str);
      }
		
		return ResultUtil.getResultBase(str);
	}
}
