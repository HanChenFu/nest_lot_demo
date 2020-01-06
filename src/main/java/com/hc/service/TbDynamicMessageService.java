package com.hc.service;

import java.util.Map;

import com.hc.common.result.ResultBase;
import com.hc.common.result.ResultData;
import com.hc.pojo.base.PageUtilBean;
import com.hc.pojo.entity.TbDynamicMessageInfo;
import com.hc.pojo.reqBean.DynamicMessageGetInfoReqBean;
import com.hc.pojo.resBean.ResGetTyphoonWarningsBean;
import com.hc.pojo.resBean.ResSevenDaysWeatherBean;

public interface TbDynamicMessageService {
	//增加动态消息
	ResultBase addInfo(TbDynamicMessageInfo bean)throws Exception;
	//删除动态消息
	ResultBase delInfo(Map<String,Object> map) throws Exception;
	//修改动态消息
	ResultBase updateInfo(TbDynamicMessageInfo bean) throws Exception ;
	//查询所有动态消息
	ResultData<PageUtilBean> getAllInfo(DynamicMessageGetInfoReqBean bean) throws Exception ;
	//查询动态消息详情通过ID
	ResultData<TbDynamicMessageInfo> getOneInfoById(Integer tbId) throws Exception ;
	//返回天气预报详情
	ResultData<ResSevenDaysWeatherBean> getWeatherForecastDetails() throws Exception ;
	//返回台风预警详情
	ResultData<ResGetTyphoonWarningsBean> getTyphoonWarning() throws Exception ;
}
