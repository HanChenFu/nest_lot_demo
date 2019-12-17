package com.hc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hc.mapper.onlineService.OnlineServiceMapper;
import com.hc.service.TbOnlineServiceService;

@Service("onlineService")
public class TbOnlineServiceImpl implements TbOnlineServiceService{

	@Autowired
	private OnlineServiceMapper  onlineServiceMapper; 
	
	
	/**
	 * 查询记录
	 */
	@Override 
	public String getOnlineServiceChatContent(String chatContent){
		String chatContents = onlineServiceMapper.getOnlineServiceChatContent(chatContent);
		return  chatContents;
	}
	
	
}
