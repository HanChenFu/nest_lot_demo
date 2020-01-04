package com.hc.controller.callCenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hc.common.exception.CustomException;
import com.hc.common.result.ResultBase;
import com.hc.common.result.ResultData;
import com.hc.common.result.ResultQuery;
import com.hc.pojo.base.PageUtilBean;
import com.hc.pojo.callCenter.CallCenter;
import com.hc.pojo.callCenter.MailList;
import com.hc.pojo.callCenter.StationSetting;
import com.hc.service.CallCenterService;
import com.hc.service.MailListService;
import com.hc.service.StationSettingService;
import com.hc.utils.result.ResultUtil;

@Controller
@RequestMapping("/stationSetting")
@ResponseBody
public class StationSettingController {
	
	
	@Autowired
	private StationSettingService stationSettingService;
	/**
	 *  添加座机
	 */
	@RequestMapping("/insertStationSetting")
	public ResultBase insertStationSetting(@RequestBody(required = false) StationSetting stationSetting) throws Exception {
		return ResultUtil.getResultBase(stationSettingService.insertStationSetting(stationSetting)+"");
	}

	/**
	 * 查询座机
	 */
	@RequestMapping("getStationSetting")
	public ResultData<PageUtilBean> getStationSetting(@RequestBody(required = false) StationSetting stationSetting) throws Exception,CustomException{
		return stationSettingService.getStationSetting(stationSetting);
	}
	
	/**
	 * 删除座机
	 */
	@RequestMapping("deleteStationSetting")
	public ResultBase deleteStationSetting(@RequestBody(required = false) JSONObject jsonObject) throws Exception,CustomException{
		String stationSettingId = jsonObject == null ? null : jsonObject.getString("stationSettingId");
		return stationSettingService.deleteStationSetting(stationSettingId==null?null:Integer.valueOf(stationSettingId));
	}
	
	/**
	 * 修改座机
	 */
	@RequestMapping("updateStationSetting")
	public ResultBase updateStationSetting(@RequestBody(required = false) StationSetting stationSetting) throws Exception,CustomException{
		return stationSettingService.updateStationSetting(stationSetting);
	}

}
