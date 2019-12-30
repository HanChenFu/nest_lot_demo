package com.hc.service.impl;

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
import com.hc.common.result.ResultQuery;
import com.hc.mapper.tbAreaDynamics.TbCaseMapper;
import com.hc.mapper.tbAreaDynamics.TbCaseTypeMapper;
import com.hc.para.page_base.BasePara;
import com.hc.pojo.base.PageUtilBean;
import com.hc.pojo.entity.TbCase;
import com.hc.pojo.entity.TbCaseType;
import com.hc.pojo.entity.TbFilingArea;
import com.hc.pojo.reqBean.QueryAllCaseListReqBean;
import com.hc.service.TbCaseService;
import com.hc.utils.conig.SystemConfigUtil;
import com.hc.utils.documentSequence.CreateSequence;
import com.hc.utils.file.FileUtil;
import com.hc.utils.result.ResultUtil;
import com.hc.utils.string.IncreaseString;


@Service("tbCaseService")
public class TbCaseServiceImpl implements TbCaseService{
	@Autowired
	private TbCaseMapper tbCaseMapper;
	
	@Autowired
	private TbCaseTypeMapper tbCaseTypeMapper;
	
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
	
	@Override
	public ResultData<PageUtilBean> getCaseTypeList() {
		int totalCount = tbCaseTypeMapper.queryAllCount();
		PageUtilBean pages = new PageUtilBean(99999, totalCount, 1);
		List<TbCaseType> list = tbCaseTypeMapper.queryAll( pages.limitsTart(),pages.limitsEnd());
		pages.setResults(list);
		return ResultUtil.getResultData(true, StatusCode.SUCCESS, "操作成功！", pages);
	}

	@Override
	public ResultData<PageUtilBean> getReportAddressList() {
		int totalCount = tbCaseTypeMapper.queryAllCount();
		PageUtilBean pages = new PageUtilBean(99999, totalCount, 1);
		List<TbCaseType> list = tbCaseTypeMapper.queryAll( pages.limitsTart(),pages.limitsEnd());
		pages.setResults(list);
		return ResultUtil.getResultData(true, StatusCode.SUCCESS, "操作成功！", pages);
	}

	@Override
	public ResultData<Map<String, Object>> exportCaseFile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultData<Map<String, Object>> deleteExportCaseFile() {
		// TODO Auto-generated method stub
		return null;
	}
}
