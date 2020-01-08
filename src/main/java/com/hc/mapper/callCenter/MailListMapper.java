package com.hc.mapper.callCenter;

import java.util.List;

import com.hc.pojo.callCenter.MailList;
import com.hc.pojo.callCenter.MailListSimple;
 

public interface MailListMapper {

	int insertMailList(MailList mailList);
	
	List<MailListSimple> getMailList(MailList mailList); 
	
	int updateMailList(MailList mailList);
	
	int deleteMailList(Integer mailListId);
	
	int getMailListCount(MailList mailList);
	
	int getMailListTrueOrFalse(Integer mailListId);
	
	int updateMailListFlag(MailList mailList);
}
