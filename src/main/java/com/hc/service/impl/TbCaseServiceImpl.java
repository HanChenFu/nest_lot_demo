package com.hc.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.hc.common.code.StatusCode;
import com.hc.common.exception.CustomException;
import com.hc.common.param_checkd.annotation.ParamCheck;
import com.hc.common.result.ResultBase;
import com.hc.common.result.ResultData;
import com.hc.common.result.ResultQuery;
import com.hc.mapper.tbAreaDynamics.TbCaseMapper;
import com.hc.mapper.tbAreaDynamics.TbCaseTypeMapper;
import com.hc.mapper.tbAreaDynamics.TbFilingAreaMapper;
import com.hc.para.page_base.BasePara;
import com.hc.pojo.base.PageUtilBean;
import com.hc.pojo.entity.TbCase;
import com.hc.pojo.entity.TbCaseType;
import com.hc.pojo.entity.TbFilingArea;
import com.hc.pojo.reqBean.QueryAllCaseListReqBean;
import com.hc.service.TbCaseService;
import com.hc.utils.conig.SystemConfigUtil;
import com.hc.utils.documentSequence.CreateSequence;
import com.hc.utils.export.ExportFileUtil;
import com.hc.utils.file.FileUtil;
import com.hc.utils.result.ResultUtil;
import com.hc.utils.string.IncreaseString;


@Service("tbCaseService")
public class TbCaseServiceImpl implements TbCaseService{
	@Autowired
	private TbCaseMapper tbCaseMapper;
	
	@Autowired
	private TbCaseTypeMapper tbCaseTypeMapper;
	
	@Autowired
	private TbFilingAreaMapper tbFilingAreaMapper;
	
	public int queryNumber(int tbCaseTypeId) {
		System.out.println(1);
		//System.out.println(Object(tbCaseService.queryNumber(tbCaseTypeId)));
		return tbCaseMapper.queryNumber(tbCaseTypeId);
	}

	@Override
	public List<TbCase> queryForPage(Integer tbCaseTypeId, String time, String tbNumber, String tbAddress,
			String tbSize, Integer tbStar) {
//		List<TbCase> d = tbCaseMapper.queryForPage(tbCaseTypeId, time, tbNumber, tbAddress, tbSize, tbStar);
		return tbCaseMapper.queryForPage(tbCaseTypeId, time, tbNumber, tbAddress, tbSize, tbStar);
	}

	@ParamCheck(names = "tbId")
	public ResultBase updateCaseById(TbCase tbcase, HttpServletRequest request) throws Exception, CustomException {
		int size = tbCaseMapper.updateCaseById(tbcase);
		if (size>0) {
			return ResultUtil.getResultBase("修改成功！");
		}
		return ResultUtil.getResultBase("修改失败！");
	}

	@Override
	public ResultBase updateCaseById(MultipartFile file, Integer tbCaseTypeId, Integer tbFilingAreaId,
			String tbReportAddress, String tbSize, Integer tbStar, String tbAddress, String tbDesc, String tbRemarks,
			Double tbLongitude, Double tbLatitude, String tbId, String caseTime,String filedTime,HttpServletRequest request)
			throws Exception, CustomException {
		String path = "";
		if("".equals(tbId)||tbId==null) {
			return ResultUtil.getResultBase("tbId不能为空");
		}
		if (FileUtil.checkPictureFormat(file)) {
			path = FileUtil.save(file, SystemConfigUtil.getValue("case_pic"));
//            throw new CustomException(StatusCode.PARAM_ERROR, "只支持jpg,jpeg,png,gif格式的图片！");
        }
        try {
        	//这边先是上传，然后再是进行格式的转换
    		TbCase tbCase=new TbCase();
    		tbCase.setTbId(Integer.parseInt(tbId));
    		if(tbCaseTypeId!=null) {
    			tbCase.setTbCaseTypeId(tbCaseTypeId);
    		}
    		if(tbFilingAreaId!=null) {
    			tbCase.setTbFilingAreaId(tbFilingAreaId);
    		}
    		tbCase.setTbUserId(1);//把userId写死得了，不需要前端传用户id
    		if(tbReportAddress!=null) {
    			tbCase.setTbReportAddress(tbReportAddress);
    		}
    		if(tbSize!=null) {
    			tbCase.setTbSize(tbSize);
    		}
    		if(tbStar!=null) {
    			tbCase.setTbStar(tbStar);
    		}
    		if(tbAddress!=null) {
    			tbCase.setTbAddress(tbAddress);
    		}
    		if(tbDesc!=null) {
    			tbCase.setTbDesc(tbDesc);
    		}
    		if (tbRemarks!=null) {
    			tbCase.setTbRemarks(tbRemarks);
    		}
    		if(caseTime!=null) {
    			tbCase.setCaseTime(caseTime);
    		}
    		if(filedTime!=null) {
    			tbCase.setFiledTime(filedTime);
    		}
    		tbCase.setTbImages(path);
			  if(tbLatitude!=null&&tbLongitude!=null) {
				  tbCase.setTbLongitude(new BigDecimal(tbLongitude)); 
				  tbCase.setTbLatitude(new BigDecimal(tbLatitude)); 
			  }
    		int a=tbCaseMapper.updateCaseById(tbCase);
    		if(a>0) {
    			return ResultUtil.getResultBase("更新成功");
    		}
			return ResultUtil.getResultBase("更新失败");
        } catch (Exception e) {
            throw e;
        }
	}

	@Override
	public ResultBase deleCase(BasePara para, HttpServletRequest request) throws Exception, CustomException {
		List<Integer> li = para.getListid();
		int size = tbCaseMapper.deleCase(li);
		if (size>0) {
			return ResultUtil.getResultBase("删除成功！");
		}
		return ResultUtil.getResultBase("删除失败！");
	}
	
	
	//查询所有案件
	@Override
	public ResultData<PageUtilBean> queryAllCaseList(QueryAllCaseListReqBean bean) {
		System.out.println("~~~~~~" + JSONArray.toJSONString(bean));
		int totalCount = tbCaseMapper.selectCaseByConditionsCount(bean.getTbCaseTypeId(), bean.getTime(), bean.getTbNumber(), bean.getTbAddress(), 
				bean.getTbSize(), bean.getTbStar(), bean.getTbCaseSaveCategory());
		PageUtilBean pages = new PageUtilBean(bean.getPageSize(), totalCount, bean.getPage());
		List<TbCase> list = tbCaseMapper.selectCaseByConditions(bean.getTbCaseTypeId(), bean.getTime(), bean.getTbNumber(), bean.getTbAddress(), 
				bean.getTbSize(), bean.getTbStar(), bean.getTbCaseSaveCategory(), pages.limitsTart(),pages.limitsEnd());
		pages.setResults(list);
		return ResultUtil.getResultData(true, StatusCode.SUCCESS, "操作成功！", pages);
	}
	
	//获取案件编号
	@Override
	public ResultData<Map<String, Object>> getCaseSerialNum() {
		Map<String,Object> map = new HashMap<String, Object>();
		String tbNumber = "SDH02"+CreateSequence.getTimeMillisSequence();
		map.put("tbNumber", tbNumber);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String res = simpleDateFormat.format(date);
		map.put("time", res);
		return ResultUtil.getResultData(true, StatusCode.SUCCESS, "操作成功！", map);
	}
	//获取案件类型列表
	@Override
	public ResultData<PageUtilBean> getCaseTypeList() {
		int totalCount = tbCaseTypeMapper.queryAllCount();
		PageUtilBean pages = new PageUtilBean(99999, totalCount, 1);
		List<TbCaseType> list = tbCaseTypeMapper.queryAll( pages.limitsTart(),pages.limitsEnd());
		pages.setResults(list);
		return ResultUtil.getResultData(true, StatusCode.SUCCESS, "操作成功！", pages);
	}
	//获取报案地址列表
	@Override
	public ResultData<PageUtilBean> getReportAddressList() {
		int totalCount = tbFilingAreaMapper.queryAllCount();
		PageUtilBean pages = new PageUtilBean(99999, totalCount, 1);
		List<TbFilingArea> list = tbFilingAreaMapper.queryAll( pages.limitsTart(),pages.limitsEnd());
		pages.setResults(list);
		return ResultUtil.getResultData(true, StatusCode.SUCCESS, "操作成功！", pages);
	}
	//导出Excel/word/PDF案件文件 
	@Override
	public ResultData<Map<String, Object>> exportCaseFile(String tbNumber,int type) {
		Map<String,Object> map = new HashMap<String,Object>();
		String url = "";
		TbCase tbCase = tbCaseMapper.queryByTbNumber(tbNumber);
		String[][] values = new String[10][2];
		values[0][0] = "档案编号";
		values[0][1] = tbCase.getTbNumber();
		values[1][0] = "归档日期";
		values[1][1] = tbCase.getCaseTime();
		values[2][0] = "档案类型";
		values[2][1] = ""  +  tbCase.getTbCaseTypeId();
		values[3][0] = "报案地点";
		values[3][1] = ""  +  tbCase.getTbFilingAreaId();
		values[4][0] = "事件大小";
		values[4][1] = tbCase.getTbSize();
		values[5][0] = "关注星级";
		values[5][1] = ""  +  tbCase.getTbStar();
		values[6][0] = "案件地址";
		values[6][1] = tbCase.getTbAddress();
		values[7][0] = "案件经过";
		values[7][1] = tbCase.getTbDesc();
		values[8][0] = "案件备注";
		values[8][1] = tbCase.getTbRemarks();
		values[9][0] = "案件图片";
		values[9][1] = "";
		String img = "";
		if(null!=tbCase.getTbImages()){
			img = tbCase.getTbImages();
		}
		if(1==type){
			url = ExportFileUtil.exportExcelFile(tbCase.getTbNumber(), tbCase.getTbNumber(), values, img);
		}else if(2==type){
			url = ExportFileUtil.exportWordFile(tbCase.getTbNumber(), values, img);
		}else if(3==type){
			url = ExportFileUtil.exportPdfFile(tbCase.getTbNumber(), values, img);
		}else{
			return ResultUtil.getResultData(false, StatusCode.ERROR, "参数错误！", map);
		}
		map.put("url", url);
		return ResultUtil.getResultData(true, StatusCode.SUCCESS, "操作成功！", map);
	}
	//删除导出的Excel/word/PDF案件文件 
	@Override
	public ResultData<Object> deleteExportCaseFile(String tbNumber,int type) {
		if(1==type){
			ExportFileUtil.delFile(new File(SystemConfigUtil.getValue("upload_path")+"/temporary/excel/"+tbNumber+".xlsx"));
			ExportFileUtil.delFile(new File(SystemConfigUtil.getValue("upload_path")+"/temporary/excel/"+tbNumber));
		}else if(2==type){
			ExportFileUtil.delFile(new File(SystemConfigUtil.getValue("upload_path")+"/temporary/word/"+tbNumber+".docx"));
			ExportFileUtil.delFile(new File(SystemConfigUtil.getValue("upload_path")+"/temporary/word/"+tbNumber));
		}else if(3==type){
			ExportFileUtil.delFile(new File(SystemConfigUtil.getValue("upload_path")+"/temporary/pdf/"+tbNumber+".pdf"));
			ExportFileUtil.delFile(new File(SystemConfigUtil.getValue("upload_path")+"/temporary/pdf/"+tbNumber));
		}else{
			return ResultUtil.getResultData(false, StatusCode.ERROR, "参数错误！", new Object());
		}
		return ResultUtil.getResultData(true, StatusCode.SUCCESS, "操作成功！", new Object());
	}
}
