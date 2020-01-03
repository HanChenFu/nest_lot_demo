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

import com.hc.common.code.StatusCode;
import com.hc.common.exception.CustomException;
import com.hc.common.param_checkd.annotation.ParamCheck;
import com.hc.common.result.ResultBase;
import com.hc.common.result.ResultData;
import com.hc.mapper.tbAreaDynamics.TbCaseMapper;
import com.hc.mapper.tbAreaDynamics.TbCaseTypeMapper;
import com.hc.mapper.tbAreaDynamics.TbFilingAreaMapper;
import com.hc.pojo.base.PageUtilBean;
import com.hc.pojo.entity.TbCase;
import com.hc.pojo.entity.TbCaseType;
import com.hc.pojo.entity.TbFilingArea;
import com.hc.pojo.reqBean.AddAndUpdateCaseReqBean;
import com.hc.pojo.reqBean.QueryAllCaseListReqBean;
import com.hc.service.TbCaseService;
import com.hc.utils.aliyunOss.StsServiceSample;
import com.hc.utils.conig.SystemConfigUtil;
import com.hc.utils.date.MyDateUtil;
import com.hc.utils.documentSequence.CreateSequence;
import com.hc.utils.export.ExportFileUtil;
import com.hc.utils.file.FileUtil;
import com.hc.utils.result.ResultUtil;

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
	public ResultBase deleCase(Map<String,Object> map) throws Exception, CustomException {
		@SuppressWarnings("unchecked")
		List<Integer> li = (List<Integer>)map.get("listid");
		/*List<Integer> li = para.getListid();*/
		int size = tbCaseMapper.deleCase(li);
		if (size>0) {
			return ResultUtil.getResultBase("删除成功！");
		}
		return ResultUtil.getResultBase("删除失败！");
	}
	//对新增和修改请求实体类做检测
	public ResultBase detectionCaseReqBean(AddAndUpdateCaseReqBean bean) {
		//案件保存类别检测
		/*if(null==bean.getTbLongitude()){
			return ResultUtil.getResultBase(false, StatusCode.PARAM_NULL, "地址坐标获取失败，请重新定位地址！");
		}
		//案件保存类别检测
		if(null==bean.getTbLongitude()){
			return ResultUtil.getResultBase(false, StatusCode.PARAM_NULL, "地址坐标获取失败，请重新定位地址！");
		}*/
		//案件保存类别检测
		if(null==bean.getTbCaseSaveCategory()){
			return ResultUtil.getResultBase(false, StatusCode.PARAM_NULL, "案件保存类别不能为空！");
		}else if(0!=bean.getTbCaseSaveCategory()&&1!=bean.getTbCaseSaveCategory()){//案件保存类别（0：联网保存，1：草稿箱保存）
			return ResultUtil.getResultBase(false, StatusCode.PARAM_ERROR, "参数案件保存类别错误！");
		}
		//案件类型检测
		if(null==bean.getTbCaseTypeId()){
			return ResultUtil.getResultBase(false, StatusCode.PARAM_NULL, "案件类型不能为空！");
		}else{
			TbCaseType caseType = tbCaseTypeMapper.queryByTbId(bean.getTbCaseTypeId());
			if(null==caseType){
				return ResultUtil.getResultBase(false, StatusCode.PARAM_ERROR, "案件类型已删除！刷新后重新新增！");
			}
		} 
		//报案地址检测
		if(null==bean.getTbFilingAreaId()){
			return ResultUtil.getResultBase(false, StatusCode.PARAM_NULL, "报案地址不能为空！");
		}else{
			TbFilingArea filingArea = tbFilingAreaMapper.queryByTbId(bean.getTbFilingAreaId());
			if(null==filingArea){
				return ResultUtil.getResultBase(false, StatusCode.PARAM_ERROR, "报案地址已删除！刷新后重新新增！");
			}
		} 
		//事件大小检测
		if(null==bean.getTbSize()){
			return ResultUtil.getResultBase(false, StatusCode.PARAM_NULL, "事件大小不能为空！");
		}else if("1".equals(bean.getTbSize())&&"2".equals(bean.getTbSize())&&"3".equals(bean.getTbSize())){//事件大小 1=小  2=中  3=大
			return ResultUtil.getResultBase(false, StatusCode.PARAM_ERROR, "参数案件保存类别错误！");
		}
		//关注星级检测
		if(null==bean.getTbStar()){
			return ResultUtil.getResultBase(false, StatusCode.PARAM_NULL, "关注星级不能为空！");
		}else if(0>bean.getTbStar()||5<bean.getTbStar()){
			return ResultUtil.getResultBase(false, StatusCode.PARAM_ERROR, "参数关注星级错误！");
		}
		//案件地址(详细)检测
		if(null==bean.getTbAddress()||"".equals(bean.getTbAddress())){
			return ResultUtil.getResultBase(false, StatusCode.PARAM_NULL, "案件地址(详细)不能为空！");
		}else if(100<bean.getTbAddress().length()){
			return ResultUtil.getResultBase(false, StatusCode.PARAM_ERROR, "参数案件详情长度不能大于100字符！");
		}
		//案件经过检测
		if(null==bean.getTbDesc()||"".equals(bean.getTbDesc())){
			return ResultUtil.getResultBase(false, StatusCode.PARAM_NULL, "案件经过不能为空！");
		}else if(1000<bean.getTbDesc().length()){
			return ResultUtil.getResultBase(false, StatusCode.PARAM_ERROR, "参数案件经过长度不能大于1000字符！");
		}
		//案件备注检测
		if(null==bean.getTbRemarks()||"".equals(bean.getTbRemarks())){
			return ResultUtil.getResultBase(false, StatusCode.PARAM_NULL, "案件备注不能为空！");
		}else if(1000<bean.getTbRemarks().length()){
			return ResultUtil.getResultBase(false, StatusCode.PARAM_ERROR, "参数案件备注长度不能大于1000字符！");
		}
		//归档时间检测
		if(null==bean.getFiledTime()||"".equals(bean.getFiledTime())){
			return ResultUtil.getResultBase(false, StatusCode.PARAM_NULL, "案件归档时间不能为空！");
		}
		return ResultUtil.getResultBase(true, StatusCode.SUCCESS, "操作成功！");
	}
	
	//新增案件
	@Override
	public ResultBase addCase(MultipartFile[] files,AddAndUpdateCaseReqBean bean) throws Exception{
		ResultBase baseBean = ResultUtil.getResultBase(true, StatusCode.SUCCESS, "操作成功！");
		//案件保存类别检测
		if(null==bean.getTbCaseSaveCategory()){
			return ResultUtil.getResultBase(false, StatusCode.PARAM_NULL, "案件保存类别不能为空！");
		}else if(0==bean.getTbCaseSaveCategory()){//案件保存类别（0：联网保存，1：草稿箱保存）
			//案件公共字段检测
			baseBean = detectionCaseReqBean(bean);
			if(!baseBean.getSuccess()){
				return baseBean;
			}
			//案件图片检测
			if(null==files||files.length<1){
				return ResultUtil.getResultBase(false, StatusCode.PARAM_NULL, "案件图片不能为空！");
			}
		}else if(1==bean.getTbCaseSaveCategory()){
			
		}else{
			return ResultUtil.getResultBase(false, StatusCode.PARAM_ERROR, "参数案件保存类别错误！");
		}
		//编号(档案号)
		if(null==bean.getTbNumber()){
			return ResultUtil.getResultBase(false, StatusCode.PARAM_NULL, "案件编号(档案号)不能为空！请刷新重新生成！");
		}else{
			TbCase tbCase = tbCaseMapper.queryByTbNumber(bean.getTbNumber());
			if(null!=tbCase){
				return ResultUtil.getResultBase(false, StatusCode.PARAM_ERROR, "案件编号(档案号)已存档！请刷新重新生成！");
			}
		} 
		//图片处理
		if(null!=files&&files.length>0){
			for(MultipartFile file : files ){
				if (!FileUtil.checkPictureFormat(file)) {
					return ResultUtil.getResultBase(false, StatusCode.PARAM_ERROR, "案件图片格式不正确！");
				}
			}
			StringBuffer path = new StringBuffer();
			StsServiceSample.init();//初始化
			for(MultipartFile file : files ){
				String url = StsServiceSample.uploadImg2Oss(file, "case_imgs/"+bean.getTbNumber()+"/");
				path.append("case_imgs/");
				path.append(bean.getTbNumber());
				path.append("/");
				path.append(url);
				path.append(",");
			}
			StsServiceSample.destory();//销毁
			String tbImages = path.substring(0, path.length()-1);
			bean.setTbImages(tbImages);
		}
		//把userId写死得了，不需要前端传用户id
		bean.setTbUserId(1);
		String time = MyDateUtil.getNowDateTime();
		bean.setCreateTime(time);
		int a=tbCaseMapper.insertCase2(bean);
		if(a<1) {
			return ResultUtil.getResultBase("案件内容上传失败");
		}
		return baseBean;
	}
	
	//查询所有案件
	@Override
	public ResultData<PageUtilBean> queryAllCaseList(QueryAllCaseListReqBean bean) {
		//案件保存类别检测
		if(null==bean.getTbCaseSaveCategory()){
			return ResultUtil.getResultData(false, StatusCode.PARAM_NULL, "案件保存类别不能为空！",new PageUtilBean());
		}else if(0!=bean.getTbCaseSaveCategory()&&1!=bean.getTbCaseSaveCategory()){//案件保存类别（0：联网保存，1：草稿箱保存）
			return ResultUtil.getResultData(false, StatusCode.PARAM_ERROR, "参数案件保存类别错误！",new PageUtilBean());
		}
		//查询总条数
		int totalCount = tbCaseMapper.selectCaseByConditionsCount(bean.getTbCaseTypeId(), bean.getTime(), bean.getTbNumber(), bean.getTbAddress(), 
				bean.getTbSize(), bean.getTbStar(), bean.getTbCaseSaveCategory());
		PageUtilBean pages = new PageUtilBean(bean.getPageSize(), totalCount, bean.getPage());
		//查询案件
		List<TbCase> list = tbCaseMapper.selectCaseByConditions(bean.getTbCaseTypeId(), bean.getTime(), bean.getTbNumber(), bean.getTbAddress(), 
				bean.getTbSize(), bean.getTbStar(), bean.getTbCaseSaveCategory(), pages.limitsTart(),pages.limitsEnd());
		pages.setResults(list);
		return ResultUtil.getResultData(true, StatusCode.SUCCESS, "操作成功！", pages);
	}
	//修改案件
	@Override
	public ResultBase updateCase(MultipartFile[] files,AddAndUpdateCaseReqBean bean)  throws Exception{
		//案件ID检测
		TbCase tbCase = null;
		if(null==bean.getTbId()){
			return ResultUtil.getResultBase(false, StatusCode.PARAM_NULL, "案件ID不能为空！请刷新！");
		}else{
			tbCase = tbCaseMapper.queryByTbId(bean.getTbId());
			if(null==tbCase){
				return ResultUtil.getResultBase(false, StatusCode.PARAM_ERROR, "该案件已删除！请刷新");
			}
		} 
		
		ResultBase baseBean = ResultUtil.getResultBase(true, StatusCode.SUCCESS, "操作成功！");
		
		//案件图片检测
		String[] imgs = tbCase.getTbImages()==null?null:tbCase.getTbImages().split(",");
		//得到本次修改需要增加图片的数量
		int delImgsLength = null == bean.getDelImgs()?0:bean.getDelImgs().length;
		//得到本次修改需要增加图片的数量
		int filsLength = null == files?0:files.length;
		int imgsLength = null == imgs?0:imgs.length;
		//案件保存类别检测
		if(null==bean.getTbCaseSaveCategory()){
			return ResultUtil.getResultBase(false, StatusCode.PARAM_NULL, "案件保存类别不能为空！");
		}else if(0==bean.getTbCaseSaveCategory()){//案件保存类别（0：联网保存，1：草稿箱保存）
			//案件公共字段检测
			baseBean = detectionCaseReqBean(bean);
			if(!baseBean.getSuccess()){
				return baseBean;
			}
			
			if(imgsLength-delImgsLength+filsLength<=0){
				return ResultUtil.getResultBase(false, StatusCode.PARAM_NULL, "案件图片不能为空！");
			}
		}else if(1==bean.getTbCaseSaveCategory()){
		}else{
			return ResultUtil.getResultBase(false, StatusCode.PARAM_ERROR, "参数案件保存类别错误！");
		}
		//检测上传图片格式是否正确
		if(null!=files&&files.length>0){
			for(MultipartFile file : files ){
				if (!FileUtil.checkPictureFormat(file)) {
					return ResultUtil.getResultBase(false, StatusCode.PARAM_ERROR, "案件图片格式不正确！");
				}
			}
		}
		//图片处理
		StringBuffer path = new StringBuffer();
		//先删除需要删除的图片
		if(delImgsLength!=0){
			for(String img : imgs){
				boolean flag = true;
				for(String img2 : bean.getDelImgs()){
					if(img.equals(img2)){
						flag = false;
						//调取阿里云OSS删除【img2】的图片
						StsServiceSample.delAliyunUrl(SystemConfigUtil.getValue("aliyun_oss_url")+img2);
					}
				}
				if(flag){
					path.append(img);
					path.append(",");
				}
			}
		}
		//再增加需要增加的图片
		if(filsLength!=0){
			StsServiceSample.init();//初始化
			for(MultipartFile file : files ){
				String url = StsServiceSample.uploadImg2Oss(file, "case_imgs/"+bean.getTbNumber()+"/");
				path.append("case_imgs/");
				path.append(bean.getTbNumber());
				path.append("/");
				path.append(url);
				path.append(",");
			}
			StsServiceSample.destory();//销毁
		}
		String tbImages = path.length()==0?"":path.substring(0, path.length()-1);
		bean.setTbImages(tbImages);
		bean.setTbNumber(null);
		int a=tbCaseMapper.updateCase(bean);
		if(a<1) {
			return ResultUtil.getResultBase("案件内容上传失败");
		}
		return baseBean;
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
		String tbCaseSType = "";
		TbCaseType tbCaseType = tbCaseTypeMapper.queryByTbId(tbCase.getTbCaseTypeId());
		if(tbCaseType!=null){
			tbCaseSType = tbCaseType.getTbName();
		}
		values[2][0] = "档案类型";
		values[2][1] = ""  +  tbCaseSType;
		String tbFilingAreaName = "";
		TbFilingArea tbFilingArea = tbFilingAreaMapper.queryByTbId(tbCase.getTbFilingAreaId());
		if(tbFilingArea!=null){
			tbFilingAreaName = tbFilingArea.getTbName();
		}
		values[3][0] = "报案地点";
		values[3][1] = ""  +  tbFilingAreaName;
		String tbSize = "";
		if("1".equals(tbCase.getTbSize())){
			tbSize = "小";
		}else if("2".equals(tbCase.getTbSize())){
			tbSize = "中";
		}else if("3".equals(tbCase.getTbSize())){
			tbSize = "大";
		}
		values[4][0] = "事件大小";
		values[4][1] = tbSize;
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
			return ResultUtil.getResultData(false, StatusCode.ERROR, "参数错误！", "");
		}
		return ResultUtil.getResultData(true, StatusCode.SUCCESS, "操作成功！", "");
	}
	
}
