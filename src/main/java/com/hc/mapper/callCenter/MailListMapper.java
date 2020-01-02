package com.hc.mapper.callCenter;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hc.pojo.callCenter.MailList;
 

public interface MailListMapper {

	int insertMailList(MailList mailList);
	
	List<MailList> getMailList(MailList mailList,@Param("limitsTart")Integer limitsTart,@Param("limitsEnd")Integer limitsEnd); 
	
	int updateMailList(MailList mailList);
	
	int deleteMailList(Integer mailListId);
	
	int getMailListCount(MailList mailList);
	
	int getMailListTrueOrFalse(Integer mailListId);
}
