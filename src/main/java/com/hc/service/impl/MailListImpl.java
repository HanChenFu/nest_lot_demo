package com.hc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hc.common.exception.CustomException;
import com.hc.common.result.ResultQuery;
import com.hc.common.tools.Tools;
import com.hc.mapper.callCenter.CallCenterMapper;
import com.hc.mapper.callCenter.MailListMapper;
import com.hc.pojo.askRecord.TbAskRecord;
import com.hc.pojo.callCenter.CallCenter;
import com.hc.pojo.callCenter.MailList;
import com.hc.service.CallCenterService;
import com.hc.service.MailListService;
import com.hc.utils.result.ResultUtil;

@Service("mailListService")
public class MailListImpl implements MailListService {

	@Autowired
	private MailListMapper mailListMapper;
	
	/**
	 * 添加通讯录
	 */
	@Override 
	public int insertMailList(MailList mailList) throws Exception,CustomException{
		if(mailList!=null) {
			mailList.setCreateTime(Tools.getAPIresponseDateTime());
			int index = mailListMapper.insertMailList(mailList);
	        return index;
		}else {
			return 0;
		}
	}
	
	 
	
	
}
