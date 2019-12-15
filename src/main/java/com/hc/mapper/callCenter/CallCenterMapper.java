package com.hc.mapper.callCenter;

import java.util.List;

import com.hc.pojo.callCenter.CallCenter;

public interface CallCenterMapper {

	
	int insertCallCenter(CallCenter callCenter);
	
	List<CallCenter> getCallCenterRecord(CallCenter callCenter);
	
	int getCallCenterRecordCount();
}
