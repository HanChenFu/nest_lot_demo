package com.hc.mapper.callCenter;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.hc.pojo.callCenter.StationSetting;
 

public interface StationSettingMapper {

	int insertStationSetting(StationSetting stationSetting);
	
	List<StationSetting> getStationSetting(@Param("limitsTart")Integer limitsTart,@Param("limitsEnd")Integer limitsEnd,@Param("seatNumber")String seatNumber); 
	
	int updateStationSetting(StationSetting stationSetting);
	
	int deleteStationSetting(Integer stationSettingId);
	
	int getStationSettingCount(StationSetting stationSetting);
	
	int getStationSettingTrueOrFalse(Integer stationSettingId);
}
