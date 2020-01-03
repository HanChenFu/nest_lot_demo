package com.hc.service;

import com.hc.common.exception.CustomException;
import com.hc.common.result.ResultBase;
import com.hc.common.result.ResultData;
import com.hc.pojo.base.PageUtilBean;
import com.hc.pojo.callCenter.StationSetting;

public interface StationSettingService {

	int insertStationSetting(StationSetting stationSetting) throws Exception, CustomException;

	ResultData<PageUtilBean> getStationSetting(StationSetting stationSetting) throws Exception, CustomException;

	ResultBase deleteStationSetting(Integer stationSettingId) throws Exception, CustomException;

	ResultBase updateStationSetting(StationSetting stationSetting) throws Exception, CustomException;





}
