package com.hc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hc.common.code.StatusCode;
import com.hc.common.exception.CustomException;
import com.hc.common.result.ResultBase;
import com.hc.common.result.ResultData;
import com.hc.common.tools.Tools;
import com.hc.mapper.callCenter.StationSettingMapper;
import com.hc.pojo.base.PageUtilBean;
import com.hc.pojo.callCenter.MailList;
import com.hc.pojo.callCenter.StationSetting;
import com.hc.service.StationSettingService;
import com.hc.utils.result.ResultUtil;

@Service("stationSettingService")
public class StationSettingImpl implements StationSettingService {

	@Autowired
	private StationSettingMapper stationSettingMapper;
	
	/**
	 * 添加座机
	 */
	@Override 
	public int insertStationSetting(StationSetting stationSetting) throws Exception,CustomException{
		if(stationSetting!=null) {
			stationSetting.setCreateTime(Tools.getAPIresponseDateTime());
			int index = stationSettingMapper.insertStationSetting(stationSetting);
	        return index;
		}else {
			return 0;
		}
	}
	/**
	 * 查询座机
	 */
	@Override 
	public ResultData<PageUtilBean> getStationSetting(StationSetting stationSetting) throws Exception,CustomException{
		stationSetting = stationSetting == null ? new StationSetting() : stationSetting;
		int totalCount = stationSettingMapper.getStationSettingCount(stationSetting);
		int page = stationSetting.getPage()==null ? 1 : stationSetting.getPage();
		int pageSize = stationSetting.getPageSize()==null ? 10 : stationSetting.getPageSize();
		PageUtilBean pages = new PageUtilBean(pageSize, totalCount, page);
		List<StationSetting> stationSettings = stationSettingMapper.getStationSetting(pages.limitsTart(),pages.limitsEnd(),stationSetting.getSeatNumber());
		pages.setResults(stationSettings);
		return  ResultUtil.getResultData(true, StatusCode.SUCCESS, "操作成功！", pages);
	}
	/**
	 * 删除座机
	 */
	@Override 
	public ResultBase deleteStationSetting(Integer stationSettingId) throws Exception,CustomException{
		int count = stationSettingMapper.getStationSettingTrueOrFalse(stationSettingId);
		if(count > 0) {
			int deleteCount = stationSettingMapper.deleteStationSetting(stationSettingId);
			if (deleteCount > 0) {
				return ResultUtil.getResultBase("删除成功！");
			}
		}
		return ResultUtil.getResultBase("删除失败！");
	}
	/**
	 * 修改座机
	 */
	@Override 
	public ResultBase updateStationSetting(StationSetting stationSetting) throws Exception,CustomException{
		int count = stationSettingMapper.getStationSettingTrueOrFalse(stationSetting.getStationSettingId());
		if(count > 0) {
			stationSetting.setUpdateTime(Tools.getAPIresponseDateTime());
			int updateCount = stationSettingMapper.updateStationSetting(stationSetting);
			if (updateCount > 0) {
				return ResultUtil.getResultBase("修改成功！");
			}
		}
		return ResultUtil.getResultBase("修改失败！");
	}
	
}
