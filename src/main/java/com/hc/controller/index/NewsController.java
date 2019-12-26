package com.hc.controller.index;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.StaticResourceLocation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hc.common.code.StatusCode;
import com.hc.common.result.ResultBase;
import com.hc.common.result.ResultData;
import com.hc.common.tools.Tools;
import com.hc.mapper.tbAreaDynamics.TbCaseMapper;
import com.hc.para.page_base.BasePara;
import com.hc.pojo.entity.TbCase;
import com.hc.service.TbAreaDynamicsService;
import com.hc.service.TbCaseService;
import com.hc.service.TbEmergencyNewsService;
import com.hc.service.TbNoticeService;
import com.hc.service.TbWorkDynamicsService;
import com.hc.utils.result.ResultUtil;

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
	private TbEmergencyNewsService tbEmergencyNewsService; 
	@Autowired
	private TbCaseService tbCaseService; 
	@Autowired
	private TbCaseMapper tbCaseMapper; 
	
	
	//时间戳转化成 日期
	public static String stampToDate(String s) {
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long lt = new Long(s);
		Date date = new Date(lt);
		res = simpleDateFormat.format(date);
		return res;
	}
	
	//查询首页的工作动态、通知公告、各区动态、应急要闻
	@RequestMapping("/everyAreaDynamics")
	public List<?> queryEveryAreaDynamics(@RequestBody(required = false) BasePara para) throws Exception {
		if (para!=null) {
			String type = para.getType();
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
	
	
	//查询首页的统计数字
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
	
	
	//查询所有案件
	@RequestMapping("/queryAllCase")
	public List<TbCase> queryAllCase(@RequestBody(required = false) JSONObject jsonObject, HttpServletRequest request) throws Exception {
		Integer tbCaseTypeId = jsonObject == null ? null : jsonObject.getInteger("tbCaseTypeId");
		String  time= jsonObject == null ? null : jsonObject.getString("time");
		String tbNumber=jsonObject == null ? null : jsonObject.getString("tbNumber");
		String tbAddress=jsonObject == null ? null : jsonObject.getString("tbAddress");
		String tbSize=jsonObject == null ? null : jsonObject.getString("tbSize");
		Integer tbStar=jsonObject == null ? null : jsonObject.getInteger("tbStar");
		return tbCaseService.queryForPage(tbCaseTypeId, time, tbNumber, tbAddress, tbSize, tbStar);
	}
	
	//查询所有案件
		@RequestMapping("/queryAllCase2")
		public ResultData<Object> queryAllCase2(@RequestBody(required = false) JSONObject jsonObject, HttpServletRequest request) throws Exception {
			Integer tbCaseTypeId = jsonObject == null ? null : jsonObject.getInteger("tbCaseTypeId");
			String  time= jsonObject == null ? null : jsonObject.getString("time");
			String tbNumber=jsonObject == null ? null : jsonObject.getString("tbNumber");
			String tbAddress=jsonObject == null ? null : jsonObject.getString("tbAddress");
			String tbSize=jsonObject == null ? null : jsonObject.getString("tbSize");
			Integer tbStar=jsonObject == null ? null : jsonObject.getInteger("tbStar");
			List<TbCase> tbCase=tbCaseService.queryForPage(tbCaseTypeId, time, tbNumber, tbAddress, tbSize, tbStar);
			if(tbCase.size()==0) {
				return ResultUtil.getResultData(false,StatusCode.ERROR,"没数据",null);
			}
			return ResultUtil.getResultData(true,StatusCode.SUCCESS,"操作成功",tbCase);
//			return tbCaseService.queryForPage(tbCaseTypeId, time, tbNumber, tbAddress, tbSize, tbStar);
			
		}
	
	//联网备案（这个接口包含的图片上传我没写）
	@RequestMapping("/insertCase")
	public String insertCase(@RequestBody(required = false) JSONObject jsonObject, HttpServletRequest request) throws Exception {
		//前端必须要传的参数：tbCaseTypeId  tbFilingAreaId   涉及到后端的外键
		String tbNumber=jsonObject.getString("tbNumber");
//		String time= jsonObject == null ? tools.getAPIresponseDateTime() : jsonObject.getString("time");
		String time= Tools.getAPIresponseDateTime();
		int tbCaseTypeId=jsonObject.getIntValue("tbCaseTypeId");
		int tbFilingAreaId=jsonObject.getIntValue("tbFilingAreaId");
		String tbReportAddress=jsonObject.getString("tbReportAddress");
		String tbSize=jsonObject.getString("tbSize");
		int tbStar=jsonObject.getIntValue("tbStar");
		String tbAddress=jsonObject.getString("tbAddress");
		String tbDesc=jsonObject.getString("tbDesc");
		String tbRemarks=jsonObject.getString("tbRemarks");
		String tbImages=jsonObject.getString("tbImages");
		TbCase tbCase=new TbCase();
		tbCase.setTbNumber(tbNumber);
		tbCase.setCreateTime(time);
		tbCase.setTbCaseTypeId(tbCaseTypeId);
		tbCase.setTbFilingAreaId(1);
		tbCase.setTbUserId(1);//把userId写死得了，不需要前端传用户id
		tbCase.setTbReportAddress(tbReportAddress);
		tbCase.setTbSize(tbSize);
		tbCase.setTbStar(tbStar);
		tbCase.setTbAddress(tbAddress);
		tbCase.setTbDesc(tbDesc);
		tbCase.setTbRemarks(tbRemarks);
		tbCase.setTbImages(tbImages);
		int a=tbCaseMapper.insertCase(tbCase);
		if(a<1) {
			return "案件内容上传失败";
		}
		return "案件内容上传成功";
	}
	
	//这边是更新案件
	@RequestMapping("/updateCaseById")
	public ResultBase updateCaseById(@RequestBody(required = false) TbCase tbcase,HttpServletRequest request) throws Exception {
		return tbCaseService.updateCaseById(tbcase,request);
	}
	
	//案号查验     
	//这个接口  和上面的 查询所有案件共用，  传一个tbNumber就好了
	//还是调用上面哪个接口 http://localhost:8080//index/news/queryAllCase    
	//传参：
	//{
	//"tbNumber":"DC09875514"
	// }
	
	/**
	 * 爬虫 (工作动态、通知公告、各区动态、应急要闻) 的数据
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/insertReptileData")
	public ResultData<Object> insertReptileData() throws Exception {
		int index = tbWorkDynamicsService.insertReptileData(null);
		if(index == 0) {
			return ResultUtil.getResultData(false,StatusCode.ERROR,"没数据",null);
		}
		return ResultUtil.getResultData(true,StatusCode.SUCCESS,"操作成功",index);
	}
		
	@RequestMapping("/testImg")
	public String testImg() throws Exception {
//		 String url = StaticResourceController.class.getResource("/").getPath();
		 String url = StaticResourceLocation.class.getResource("/").getPath();
		 System.out.println(url+"=====");
		 return url;
		 
	}
	
}
