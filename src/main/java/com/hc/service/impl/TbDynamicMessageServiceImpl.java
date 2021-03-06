package com.hc.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.hc.common.code.StatusCode;
import com.hc.common.result.ResultBase;
import com.hc.common.result.ResultData;
import com.hc.common.timing.bean.Crawler;
import com.hc.mapper.tbAreaDynamics.TbDynamicMessageInfoMapper;
import com.hc.pojo.base.PageUtilBean;
import com.hc.pojo.entity.TbDynamicMessageInfo;
import com.hc.pojo.reqBean.DynamicMessageGetInfoReqBean;
import com.hc.pojo.resBean.ResGetOneTyphoonWarningBean;
import com.hc.pojo.resBean.ResGetTyphoonWarningsBean;
import com.hc.pojo.resBean.ResOneDayDetailsWeatherBean;
import com.hc.pojo.resBean.ResOneDayWeatherBean;
import com.hc.pojo.resBean.ResSevenDaysWeatherBean;
import com.hc.service.TbDynamicMessageService;
import com.hc.test.GetJson;
import com.hc.utils.result.ResultUtil;


@Service("TbDynamicMessageService")
public class TbDynamicMessageServiceImpl implements TbDynamicMessageService{
	@Autowired
	private TbDynamicMessageInfoMapper tbDynamicMessageInfoMapper;
	/*@Autowired
	Crawler crawler;*/
	//增加动态消息
	@Override
	public ResultBase addInfo(TbDynamicMessageInfo bean) throws Exception {
		bean.setTbId(null);
		int i = tbDynamicMessageInfoMapper.insertSelective(bean);
		if (i>0) {
			return ResultUtil.getResultBase(true, StatusCode.SUCCESS,"增加成功！");
		}
		return ResultUtil.getResultBase(false, StatusCode.FAIL,"增加失败！");
	}
	//删除动态消息
	@Override
	public ResultBase delInfo(Map<String, Object> map) throws Exception {
		@SuppressWarnings("unchecked")
		List<Integer> li = (List<Integer>)map.get("listid");
		int size = tbDynamicMessageInfoMapper.deleDynamicMessage(li);
		if (size>0) {
			return ResultUtil.getResultBase(true, StatusCode.SUCCESS,"删除成功！");
		}
		return ResultUtil.getResultBase(false, StatusCode.FAIL,"删除失败！");
	}
	//修改动态消息
	@Override
	public ResultBase updateInfo(TbDynamicMessageInfo bean)  throws Exception  {
		int i = tbDynamicMessageInfoMapper.updateByPrimaryKeySelective(bean);
		if (i>0) {
			return ResultUtil.getResultBase(true, StatusCode.SUCCESS,"修改成功！");
		}
		return ResultUtil.getResultBase(false, StatusCode.FAIL,"修改失败！");
	}
	//查询动态消息
	@Override
	public ResultData<PageUtilBean> getAllInfo(DynamicMessageGetInfoReqBean bean)  throws Exception {
		//查询总条数
		int totalCount = tbDynamicMessageInfoMapper.selectDynamicMessageAllCount(bean.getTbType());
		PageUtilBean pages = new PageUtilBean(bean.getPageSize(), totalCount, bean.getPage());
		//查询案件
		List<TbDynamicMessageInfo> list = tbDynamicMessageInfoMapper.selectDynamicMessageAll(bean.getTbType(), pages.limitsTart(),pages.limitsEnd());
		pages.setResults(list);
		return ResultUtil.getResultData(true, StatusCode.SUCCESS, "操作成功！", pages);
	}
	//查询动态消息详情通过ID
	@Override
	public ResultData<TbDynamicMessageInfo> getOneInfoById(Integer tbId)  throws Exception {
		if(null == tbId){
			return ResultUtil.getResultData(false, StatusCode.FAIL, "id不能为空！", new TbDynamicMessageInfo());
		}
		TbDynamicMessageInfo messageInfo = tbDynamicMessageInfoMapper.selectByPrimaryKey(tbId);
		if (messageInfo == null) {
			return ResultUtil.getResultData(false, StatusCode.FAIL, "未找到该数据！", new TbDynamicMessageInfo());
		}
		return ResultUtil.getResultData(true, StatusCode.SUCCESS, "操作成功！", messageInfo );
	}
	//返回天气预报详情
	@Override
	public ResultData<ResSevenDaysWeatherBean> getWeatherForecastDetails() throws Exception {
		return ResultUtil.getResultData(true, StatusCode.SUCCESS, "操作成功！", Crawler.getRedisWeatherForecastDetails());
	}
	//返回台风预警详情
	@Override
	public ResultData<ResGetTyphoonWarningsBean> getTyphoonWarning() throws Exception {
		return ResultUtil.getResultData(true, StatusCode.SUCCESS, "操作成功！", Crawler.getRedisTyphoonWarning());
	}
}
