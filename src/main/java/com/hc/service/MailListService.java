package com.hc.service;

import com.hc.common.exception.CustomException;
import com.hc.pojo.callCenter.MailList;

public interface MailListService {

	int insertMailList(MailList mailList) throws Exception, CustomException;


}
