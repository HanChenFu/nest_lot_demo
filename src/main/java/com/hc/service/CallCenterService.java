package com.hc.service;

import java.util.List;

import com.hc.common.exception.CustomException;
import com.hc.common.result.ResultQuery;
import com.hc.pojo.callCenter.CallCenter;

public interface CallCenterService {

	int insertCallCenter(String tbNumber) throws Exception, CustomException;

	ResultQuery<CallCenter> getCallCenterRecord(CallCenter callCenter);
	

}
