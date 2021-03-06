package com.hc.service;

import java.util.List;

import com.hc.common.exception.CustomException;
import com.hc.common.result.ResultBase;
import com.hc.common.result.ResultData;
import com.hc.common.result.ResultQuery;
import com.hc.pojo.base.PageUtilBean;
import com.hc.pojo.callCenter.MailList;

public interface MailListService {

	int insertMailList(MailList mailList) throws Exception, CustomException;

	ResultData<PageUtilBean> getMailList(MailList mailList) throws Exception, CustomException;

	ResultBase deleteMailList(Integer mailListId,Integer updateUserId) throws Exception, CustomException;

	ResultBase updateMailList(MailList mailList) throws Exception, CustomException;

}
