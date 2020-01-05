package com.hc.service;

import java.util.List;

import com.hc.common.exception.CustomException;
import com.hc.common.result.ResultData;
import com.hc.para.page_base.BasePara;
import com.hc.pojo.base.PageUtilBean;
import com.hc.pojo.entity.TbWorkDynamics;

public interface TbWorkDynamicsService {
	// 查询工作动态
	ResultData<PageUtilBean>  queryWorkDynamics(BasePara para);

	int insertReptileData(TbWorkDynamics tbWorkDynamics) throws Exception, CustomException;

	int insertReptileDataAll(TbWorkDynamics tbWorkDynamics) throws Exception, CustomException;

}
