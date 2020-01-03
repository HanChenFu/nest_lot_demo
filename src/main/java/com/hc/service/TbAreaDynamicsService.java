package com.hc.service;

import java.util.List;

import com.hc.common.result.ResultData;
import com.hc.para.page_base.BasePara;
import com.hc.pojo.base.PageUtilBean;
import com.hc.pojo.entity.TbAreaDynamics;

public interface TbAreaDynamicsService {
	// 查询各区动态
	ResultData<PageUtilBean> queryEveryAreaDynamics(BasePara para);
}
