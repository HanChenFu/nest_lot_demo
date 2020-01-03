package com.hc.controller.index;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.hc.common.code.StatusCode;
import com.hc.common.exception.CustomException;
import com.hc.common.result.ResultBase;
import com.hc.common.result.ResultData;
import com.hc.mapper.tbAreaDynamics.TbCaseMapper;
import com.hc.para.page_base.BasePara;
import com.hc.pojo.base.PageUtilBean;
import com.hc.pojo.entity.TbCase;
import com.hc.pojo.reqBean.AddAndUpdateCaseReqBean;
import com.hc.pojo.reqBean.QueryAllCaseListReqBean;
import com.hc.service.TbAreaDynamicsService;
import com.hc.service.TbCaseService;
import com.hc.service.TbEmergencyNewsService;
import com.hc.service.TbNoticeService;
import com.hc.service.TbWorkDynamicsService;
import com.hc.utils.aliyunOss.StsServiceSample;
import com.hc.utils.date.MyDateUtil;
import com.hc.utils.documentSequence.CreateSequence;
import com.hc.utils.file.FileUtil;
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
	public ResultData<PageUtilBean> queryEveryAreaDynamics(@RequestBody(required = false) BasePara para) throws Exception {
		if (para!=null) {
			String type = para.getType();
			if(type == null || "".equals(type)) {
				return tbWorkDynamicsService.queryWorkDynamics(para);
			}else {
				if(Integer.valueOf(type)==1) {
					return tbWorkDynamicsService.queryWorkDynamics(para);
				}else if(Integer.valueOf(type)==2) {
					return tbNoticeService.queryNotice(para);
				}else if(Integer.valueOf(type)==3) {
					return tbAreaDynamicsService.queryEveryAreaDynamics(para);
				}else if(Integer.valueOf(type)==4) {
					return tbEmergencyNewsService.queryEmergencyNews(para); 
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
		}
	
	//联网备案（这个接口包含的图片上传我没写）
		/**
		 *  caseTime
		 *  tbCaseTypeId
			tbFilingAreaId
			tbSize
			tbStar
			tbAddress
			tbDesc
		 * @throws Exception
		 */
	@RequestMapping("/insertCase")
	public ResultBase insertCase(MultipartFile file, Integer tbCaseTypeId, Integer tbFilingAreaId,String tbReportAddress,String tbSize,Integer tbStar,String tbAddress,String tbDesc,String tbRemarks,Double tbLongitude,Double tbLatitude,String caseTime,String filedTime,HttpServletRequest request) throws Exception {
		//前端必须要传的参数：tbCaseTypeId  tbFilingAreaId   涉及到后端的外键
//		String tbNumber=jsonObject.getString("tbNumber");
        // 文件原始名称
//		1,1,3,null,2019-12-14 0:0:0,null,1,null
		System.out.println(file +","+tbCaseTypeId + "," + tbFilingAreaId +"," + tbSize +"," + tbAddress +"," + caseTime + ","+tbDesc +"," + tbStar +","+ filedTime);
		if(tbCaseTypeId ==null || tbFilingAreaId == null|| tbSize == null|| tbAddress == null|| caseTime == null|| tbDesc == null|| tbStar == null || filedTime == null) {
			return ResultUtil.getResultBase("caseTime,tbCaseTypeId,tbFilingAreaId,tbSize,tbStar,tbAddress,tbDesc,filedTime参数不能为空");
		}
		String path = "";
		String tbNumber = CreateSequence.getTimeMillisSequence();
//        String originalFilename = file.getOriginalFilename();
        if (FileUtil.checkPictureFormat(file)) {
        	if(null!=file){
    			StsServiceSample.init();//初始化
    			path = "case_imgs/"+tbNumber+"/" + StsServiceSample.uploadImg2Oss(file, "case_imgs/"+tbNumber+"/");
    		}else{
    			ResultUtil.getResultData(true, StatusCode.PARAM_NULL, "参数为空！", new Object());
    		}
    		StsServiceSample.destory();//销毁
        }
        try {
    		String time = MyDateUtil.getNowDateTime();
    		TbCase tbCase=new TbCase();
    		tbCase.setTbNumber(tbNumber);
    		tbCase.setCreateTime(time);
    		tbCase.setTbCaseTypeId(tbCaseTypeId);
    		tbCase.setTbFilingAreaId(tbFilingAreaId);
    		tbCase.setTbUserId(1);//把userId写死得了，不需要前端传用户id
    		tbCase.setTbReportAddress(tbReportAddress);
    		tbCase.setTbSize(tbSize);
    		tbCase.setTbStar(tbStar);
    		tbCase.setTbAddress(tbAddress);
    		tbCase.setTbDesc(tbDesc);
    		tbCase.setFiledTime(filedTime);
    		if(tbRemarks!=null) {
    			tbCase.setTbRemarks(tbRemarks);
    		}
    		tbCase.setTbImages(path);
    		tbCase.setCaseTime(caseTime);
    		if(tbLongitude!=null&&tbLatitude!=null) {
        		tbCase.setTbLongitude(new BigDecimal(tbLongitude));
        		tbCase.setTbLatitude(new BigDecimal(tbLatitude));
    		}
    		int a=tbCaseMapper.insertCase(tbCase);
    		if(a<1) {
    			return ResultUtil.getResultBase("案件内容上传失败");
    		}
    		return ResultUtil.getResultBase("案件内容上传成功");
        } catch (Exception e) {
            throw e;
        }
	}
	
	//这边是更新案件
	@RequestMapping("/updateCaseById")
	public ResultBase updateCaseById(MultipartFile file, Integer tbCaseTypeId, Integer tbFilingAreaId,
			String tbReportAddress, String tbSize, Integer tbStar, String tbAddress, String tbDesc, String tbRemarks,
			Double tbLongitude, Double tbLatitude, String tbId, String caseTime,String filedTime,HttpServletRequest request) throws Exception {
		return tbCaseService.updateCaseById(file,tbCaseTypeId,tbFilingAreaId,tbReportAddress,tbSize,tbStar,tbAddress,tbDesc,tbRemarks,tbLongitude,tbLatitude,tbId,caseTime,filedTime,request);
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
	
	
	/**
	 * 新增案件
	 * @param tbNumber 编号(档案号)
	 * @param files 上传的图片流
	 * @param tbCaseTypeId  案件类型ID	
	 * @param tbCaseSaveCategory  案件保存类别（0：联网保存，1：草稿箱保存）
	 * @param tbFilingAreaId  报案地址ID
	 * @param tbSize  事件大小 1=小  2=中  3=大
	 * @param tbStar  关注星级	
	 * @param tbReportAddress  案件地址
	 * @param tbAddress  案件地址(详细)
	 * @param tbLongitude  经度
	 * @param tbLatitude  纬度	
	 * @param tbDesc  案件经过
	 * @param tbRemarks  案件备注
	 * @param filedTime  归档时间	
	 * @param caseTime  案件发生时间	
	 * @return ResultBase
	 * @throws Exception
	 */
	@RequestMapping("/addCase")
	public ResultBase addCase(/*@RequestBody(required = false) AddAndUpdateCaseReqBean bean*/@RequestPart(value = "files", required = false) MultipartFile[] files,
			@RequestPart("addCaseReqBean") AddAndUpdateCaseReqBean bean) throws Exception {
		return tbCaseService.addCase(files,bean);
	}
	
	/**
	 * 查询所有案件
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
	@RequestMapping(value = "/queryAllCaseList", method = RequestMethod.POST,produces="application/json")
	public ResultData<PageUtilBean> queryAllCaseList(@RequestBody(required = false) QueryAllCaseListReqBean bean) throws Exception {
		return tbCaseService.queryAllCaseList(bean);
	}
	/**
	 * 修改案件
	 * @param tbId 案件ID
	 * @param files 上传的图片流
	 * @param delImgs 需要删除的图片数组
	 * @param tbCaseTypeId  案件类型ID	
	 * @param tbCaseSaveCategory  案件保存类别（0：联网保存，1：草稿箱保存）
	 * @param tbFilingAreaId  报案地址ID
	 * @param tbSize  事件大小 1=小  2=中  3=大
	 * @param tbStar  关注星级	
	 * @param tbReportAddress  案件地址
	 * @param tbAddress  案件地址(详细)
	 * @param tbLongitude  经度
	 * @param tbLatitude  纬度	
	 * @param tbDesc  案件经过
	 * @param tbRemarks  案件备注
	 * @param filedTime  归档时间	
	 * @param caseTime  案件发生时间	
	 * @return ResultBase
	 * @throws Exception
	 */
	@RequestMapping("/updateCase")
	public ResultBase updateCase(@RequestPart("files") MultipartFile[] files,@RequestPart("addCaseReqBean")  AddAndUpdateCaseReqBean bean) throws Exception {
		return tbCaseService.updateCase(files,bean);
	}
	/**
	 * 获取案件编号
	 * @param 无
	 * @return ResultData
	 * @throws Exception
	 */
	@RequestMapping("/getCaseSerialNum")
	public ResultData<Map<String,Object>> getCaseSerialNum() throws Exception {
		return tbCaseService.getCaseSerialNum();
	}
	/**
	 * 获取案件类型列表
	 * @param 无
	 * @return ResultQuery
	 * @throws Exception
	 */
	@RequestMapping("/getCaseTypeList")
	public ResultData<PageUtilBean> getCaseTypeList() throws Exception {
		return tbCaseService.getCaseTypeList();
	}
	/**
	 * 获取报案地址列表
	 * @param 无
	 * @return ResultData
	 * @throws Exception
	 */
	@RequestMapping("/getReportAddressList")
	public ResultData<PageUtilBean> getReportAddressList() throws Exception {
		return tbCaseService.getReportAddressList();
	}
	/**
	 * 删除案件
	 * @param listid 案件ID数组
	 * @return ResultData
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleCase", method = RequestMethod.POST,produces="application/json")
	public ResultBase deleCase(@RequestBody(required = false) Map<String,Object> map) throws Exception,CustomException{
		return tbCaseService.deleCase(map);
	}
	/** 
	 * 导出Excel/word/PDF案件文件 
	 * @param tbNumber 编号(档案号)
	 * @param type 导出类型1：Excel，2：word，3：PDF
	 * @return ResultData
	 * @throws Exception
	 * */
	@RequestMapping("/exportCaseFile")
	public ResultData<Map<String,Object>> exportCaseFile(String tbNumber,int type)throws IOException {
		return tbCaseService.exportCaseFile(tbNumber,type);
	}
	/** 
	 * 删除导出的Excel/word/PDF案件文件 
	 * @param tbNumber  编号(档案号)
	 * @param type  导出类型1：Excel，2：word，3：PDF
	 * @return ResultData
	 * @throws Exception
	 * */
	@RequestMapping("/deleteExportCaseFile")
	public ResultData<Object> deleteExportCaseFile(String tbNumber,int type)throws IOException {
		return tbCaseService.deleteExportCaseFile(tbNumber,type);
	}
}