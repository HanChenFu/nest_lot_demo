package com.hc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hc.common.exception.CustomException;
import com.hc.common.result.ResultQuery;
import com.hc.common.tools.Tools;
import com.hc.mapper.callCenter.CallCenterMapper;
import com.hc.pojo.askRecord.TbAskRecord;
import com.hc.pojo.callCenter.CallCenter;
import com.hc.service.CallCenterService;
import com.hc.utils.result.ResultUtil;

@Service("callCenterService")
public class CallCenterImpl implements CallCenterService {

	@Autowired
	private CallCenterMapper callCenterMapper;
	
	@Override 
	public int insertCallCenter(String tbNumber) throws Exception,CustomException{
		CallCenter callCenter = new CallCenter();
		callCenter.setTbUserId(1);
		callCenter.setTbNumber(tbNumber);
		callCenter.setTbType(1);
		callCenter.setTbHandleType(1);
		callCenter.setTbState(1);
		callCenter.setTbDuration("2分10秒");
		callCenter.setSoundRecordFile("./file/hh.mp3");
		callCenter.setDelTime(null);
		callCenter.setCreateTime(Tools.getAPIresponseDateTime());//当前时间
		callCenter.setCallName("末知");
		Integer index = callCenterMapper.insertCallCenter(callCenter);
        return index;
	}
	
	/**
	 * 查询记录
	 */
	@Override 
	public ResultQuery<CallCenter> getCallCenterRecord(CallCenter callCenter){
		
		List<CallCenter> callCenters = callCenterMapper.getCallCenterRecord(callCenter);
		
		if(callCenters==null) {
			return ResultUtil.getResultQuery("没有数据！");
		}
		return  ResultUtil.getResultQuery(callCenters, callCenterMapper.getCallCenterRecordCount(callCenter));
	}
	
	
}
