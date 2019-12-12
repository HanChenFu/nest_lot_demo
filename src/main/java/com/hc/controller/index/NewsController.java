package com.hc.controller.index;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hc.para.page_base.BasePara;
import com.hc.service.TbAreaDynamicsService;
import com.hc.service.TbCaseService;
import com.hc.service.TbEmergencyNewsService;
import com.hc.service.TbNoticeService;
import com.hc.service.TbWorkDynamicsService;

@Controller
@RequestMapping("/index/news")
@ResponseBody
public class NewsController {
	
	@Autowired 
	private TbWorkDynamicsService tbWorkDynamicsService;
	@Autowired 
	private TbNoticeService tbNoticeService;
	@Autowired 
	private TbAreaDynamicsService tbAreaDynamicsService;
	@Autowired 
	private TbEmergencyNewsService 	tbEmergencyNewsService; 
	@Autowired
	private TbCaseService tbCaseService; 
	
	@RequestMapping("/everyAreaDynamics")
	public List<T> queryEveryAreaDynamics(@RequestBody(required=false) BasePara para) throws Exception {
		if (para!=null) {
			String type = para.getType();
			System.out.println("-*********************************-" + para.getType());
			if(type == null || "".equals(type)) {
				return tbWorkDynamicsService.queryWorkDynamics();
			}else {
				if(Integer.valueOf(type)==1) {
					return tbWorkDynamicsService.queryWorkDynamics();
				}else if(Integer.valueOf(type)==2) {
					return tbNoticeService.queryNotice();
				}else if(Integer.valueOf(type)==3) {
					return tbAreaDynamicsService.queryEveryAreaDynamics();
				}else if(Integer.valueOf(type)==4) {
					return tbEmergencyNewsService.queryEmergencyNews(); 
				}
			}
		}
		return null;
	}
	
	
	@RequestMapping("/queryNumber")
	public Map<String, Integer> queryNumbers() throws Exception {
		
		int crownCase=tbCaseService.queryNumber(1);
		int hillFire=tbCaseService.queryNumber(2);
		int commonCase=tbCaseService.queryNumber(3);
		int safetyProduction=tbCaseService.queryNumber(4);
		Map<String,Integer> map=new HashMap<String,Integer>();
		map.put("crownCase", crownCase);
		map.put("hillFire", hillFire);
		map.put("commonCase", commonCase);
		map.put("safetyProduction", safetyProduction);
		return map;
		
	}
	

}
