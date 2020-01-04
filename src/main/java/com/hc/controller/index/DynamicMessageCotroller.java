package com.hc.controller.index;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hc.common.result.ResultBase;
import com.hc.common.result.ResultData;
import com.hc.pojo.base.PageUtilBean;
import com.hc.pojo.entity.TbDynamicMessageInfo;
import com.hc.pojo.reqBean.DynamicMessageGetInfoReqBean;
import com.hc.pojo.resBean.ResSevenDaysWeatherBean;
import com.hc.service.TbDynamicMessageService;

@Controller
@RequestMapping("/index/dynamicMessage")
@ResponseBody
public class DynamicMessageCotroller {

	
	@Autowired
	private TbDynamicMessageService tbDynamicMessageService; 
	/**
	 * 测试东西
	 * @param tbCaseTypeId 案件类型ID
	 * @param time 时间
	 * @param tbNumber  编号(档案号)
	 * @param tbAddress  案件地址
	 * @param tbSize  事件大小
	 * @param tbStar  关注星级
	 * @param tbCaseSaveCategory  案件保存类别（0：联网保存，1：草稿箱保存）
	 * @param pageSize  每页多少条
	 * @param page  当前页
	 * @return ResultData
	 * @throws Exception
	 */
/*	@RequestMapping(value = "/addInfo", method = RequestMethod.POST,produces="application/json")
	public String addInfo(@Valid @RequestBody TestReqBean bean,@NotNull(message = "name 不能为空") String name,
			@Max(value = 99, message = "不能大于99岁") Integer age) throws Exception {
		return "\"msg\":\"返回就好！\"";
	}*/
	/**
	 * 增加动态消息
	 * @param tbCaseTypeId 案件类型ID
	 * @param time 时间
	 * @param tbNumber  编号(档案号)
	 * @param tbAddress  案件地址
	 * @param tbSize  事件大小
	 * @param tbStar  关注星级
	 * @param tbCaseSaveCategory  案件保存类别（0：联网保存，1：草稿箱保存）
	 * @param pageSize  每页多少条
	 * @param page  当前页
	 * @return ResultData
	 * @throws Exception
	 */
	@RequestMapping(value = "/addInfo", method = RequestMethod.POST,produces="application/json")
	public ResultBase addInfo(@RequestBody TbDynamicMessageInfo bean) throws Exception {
		return tbDynamicMessageService.addInfo(bean);
	}
	/**
	 * 删除动态消息
	 * @param tbCaseTypeId 案件类型ID
	 * @param time 时间
	 * @param tbNumber  编号(档案号)
	 * @param tbAddress  案件地址
	 * @param tbSize  事件大小
	 * @param tbStar  关注星级
	 * @param tbCaseSaveCategory  案件保存类别（0：联网保存，1：草稿箱保存）
	 * @param pageSize  每页多少条
	 * @param page  当前页
	 * @return ResultData
	 * @throws Exception
	 */
	@RequestMapping(value = "/delInfo", method = RequestMethod.POST,produces="application/json")
	public ResultBase delInfo(@RequestBody(required = false) Map<String,Object> map) throws Exception {
		return tbDynamicMessageService.delInfo(map);
	}
	/**
	 * 修改动态消息
	 * @param tbCaseTypeId 案件类型ID
	 * @param time 时间
	 * @param tbNumber  编号(档案号)
	 * @param tbAddress  案件地址
	 * @param tbSize  事件大小
	 * @param tbStar  关注星级
	 * @param tbCaseSaveCategory  案件保存类别（0：联网保存，1：草稿箱保存）
	 * @param pageSize  每页多少条
	 * @param page  当前页
	 * @return ResultData
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateInfo", method = RequestMethod.POST,produces="application/json")
	public ResultBase updateInfo(@RequestBody TbDynamicMessageInfo bean) throws Exception {
		return tbDynamicMessageService.updateInfo(bean);
	}
	/**
	 * 查询动态消息
	 * @param tbCaseTypeId 案件类型ID
	 * @param time 时间
	 * @param tbNumber  编号(档案号)
	 * @param tbAddress  案件地址
	 * @param tbSize  事件大小
	 * @param tbStar  关注星级
	 * @param tbCaseSaveCategory  案件保存类别（0：联网保存，1：草稿箱保存）
	 * @param pageSize  每页多少条
	 * @param page  当前页
	 * @return ResultData
	 * @throws Exception
	 */
	@RequestMapping(value = "/getAllInfo", method = RequestMethod.POST,produces="application/json")
	public ResultData<PageUtilBean> getAllInfo(@RequestBody DynamicMessageGetInfoReqBean bean) throws Exception {
		return tbDynamicMessageService.getAllInfo(bean);
	}
	/**
	 * 查询动态消息详情通过ID
	 * @param tbCaseTypeId 案件类型ID
	 * @param time 时间
	 * @param tbNumber  编号(档案号)
	 * @param tbAddress  案件地址
	 * @param tbSize  事件大小
	 * @param tbStar  关注星级
	 * @param tbCaseSaveCategory  案件保存类别（0：联网保存，1：草稿箱保存）
	 * @param pageSize  每页多少条
	 * @param page  当前页
	 * @return ResultData
	 * @throws Exception
	 */
	@RequestMapping(value = "/getOneInfoById", method = RequestMethod.POST,produces="application/json")
	public ResultData<TbDynamicMessageInfo> getOneInfoById(Integer tbId) throws Exception {
		return tbDynamicMessageService.getOneInfoById(tbId);
	}
	/**
	 * 返回天气预报详情
	 * @param tbCaseTypeId 案件类型ID
	 * @param time 时间
	 * @param tbNumber  编号(档案号)
	 * @param tbAddress  案件地址
	 * @param tbSize  事件大小
	 * @param tbStar  关注星级
	 * @param tbCaseSaveCategory  案件保存类别（0：联网保存，1：草稿箱保存）
	 * @param pageSize  每页多少条
	 * @param page  当前页
	 * @return ResultData
	 * @throws Exception
	 */
	@RequestMapping(value = "/getWeatherForecastDetails", method = RequestMethod.POST,produces="application/json")
	public ResultData<ResSevenDaysWeatherBean> getWeatherForecastDetails() throws Exception {
		return tbDynamicMessageService.getWeatherForecastDetails();
	}
}
