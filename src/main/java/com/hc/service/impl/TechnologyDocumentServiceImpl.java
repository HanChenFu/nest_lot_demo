package com.hc.service.impl;

import java.io.File;
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
import com.hc.service.TechnologyDocumentService;
import com.hc.utils.aliyunOss.StsServiceSample;
import com.hc.utils.conig.SystemConfigUtil;
import com.hc.utils.date.MyDateUtil;
import com.hc.utils.documentSequence.CreateSequence;
import com.hc.utils.export.ExportFileUtil;
import com.hc.utils.file.FileUtil;
import com.hc.utils.result.ResultUtil;

@Service("tbCaseService")
public class TechnologyDocumentServiceImpl implements TechnologyDocumentService{
	@Autowired
	private TbCaseMapper tbCaseMapper;

	@Override
	public ResultData<String> getUrl() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
