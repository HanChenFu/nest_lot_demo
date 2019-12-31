package com.hc.controller.index;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hc.common.code.StatusCode;
import com.hc.common.result.ResultData;
import com.hc.common.result.ResultQuery;
import com.hc.mapper.tbAreaDynamics.TbCaseMapper;
import com.hc.pojo.entity.TbCase;
import com.hc.service.TbCaseService;
import com.hc.utils.aliyunOss.AliyunBean;
import com.hc.utils.aliyunOss.StsServiceSample;
import com.hc.utils.conig.SystemConfigUtil;
import com.hc.utils.documentSequence.CreateSequence;
import com.hc.utils.export.ExportFileUtil;
import com.hc.utils.result.ResultUtil;


@Controller
@RequestMapping("/index/ceshi")
@ResponseBody
public class MultimediaAction {
	@Autowired
	private TbCaseMapper tbCaseService; 
	/** 阿里云获取参数 */
	@RequestMapping("/getAliyun")
	public AliyunBean getAliyun() {
		return StsServiceSample.getAliyun();
	}
	/** 阿里云上传图片 */
	@RequestMapping("/uploadImg")
	public ResultData<Map<String,Object>> upload(@RequestParam(value = "file", required = false) MultipartFile file)throws IOException {
		Map<String,Object> map = new HashMap<String, Object>();
		if(null!=file){
			StsServiceSample.init();
			String uploadImg2Oss = StsServiceSample.uploadImg2Oss(file, "nieliyun/");;
			map.put("uploadImg", "http://hanchenfu-szemo.oss-cn-shenzhen.aliyuncs.com/nieliyun/"+uploadImg2Oss);
		}else{
			ResultUtil.getResultData(true, StatusCode.PARAM_NULL, "参数为空！", map);
		}
		StsServiceSample.destory();
		return ResultUtil.getResultData(true, StatusCode.SUCCESS, "操作成功！", map);
	}
	/** 阿里云上传视频 */
	@RequestMapping("/uploadVideo")
	public String uploadVideo(@RequestParam(value = "file", required = false) MultipartFile file)throws IOException {
		return StsServiceSample.uploadVideo2Oss(file, "nieliyun/");
	}
	/** 
	 * 导出Excel/word/PDF文件 
	 * @param tbNumber
	 * @param type
	 * */
	@RequestMapping("/exportFile")
	public ResultData exportFile(String tbNumber,int type)throws IOException {
		String url = "";
		TbCase tbCase = tbCaseService.queryByTbNumber(tbNumber);
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
			return ResultUtil.getResultData(false, StatusCode.ERROR, "参数错误！", "");
		}
		return ResultUtil.getResultData(true, StatusCode.SUCCESS, "操作成功！", url);
	}
	/** 
	 * 删除导出的Excel/word/PDF文件 
	 * @param tbNumber
	 * @param type
	 * */
	@RequestMapping("/deleteExportFile")
	public ResultData deleteExportFile(String tbNumber,int type)throws IOException {
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
			return ResultUtil.getResultData(false, StatusCode.ERROR, "参数错误！", "");
		}
		return ResultUtil.getResultData(true, StatusCode.SUCCESS, "操作成功！", "");
	}
	public static void main(String[] args) {
	}
}
