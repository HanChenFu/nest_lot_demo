package com.hc.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hc.common.exception.CustomException;
import com.hc.common.param_checkd.annotation.ParamCheck;
import com.hc.common.result.ResultBase;
import com.hc.mapper.tbAreaDynamics.TbCaseMapper;
import com.hc.para.page_base.BasePara;
import com.hc.pojo.entity.TbCase;
import com.hc.service.TbCaseService;
import com.hc.utils.conig.SystemConfigUtil;
import com.hc.utils.file.FileUtil;
import com.hc.utils.result.ResultUtil;


@Service("tbCaseService")
public class TbCaseServiceImpl implements TbCaseService{
	@Autowired
	private TbCaseMapper tbCaseMapper;
	
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
			Double tbLongitude, Double tbLatitude, String tbId, String caseTime,HttpServletRequest request)
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
	
}
