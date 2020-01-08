package com.hc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hc.common.code.StatusCode;
import com.hc.common.exception.CustomException;
import com.hc.common.result.ResultBase;
import com.hc.common.result.ResultData;
import com.hc.common.tools.Tools;
import com.hc.mapper.callCenter.MailListMapper;
import com.hc.pojo.base.PageUtilBean;
import com.hc.pojo.callCenter.MailList;
import com.hc.pojo.callCenter.MailListSimple;
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
	/**
	 * 查询通讯录
	 */
	@Override 
	public ResultData<PageUtilBean> getMailList(MailList mailList) throws Exception,CustomException{
		mailList = mailList == null ? new MailList() : mailList;
		int totalCount = mailListMapper.getMailListCount(mailList);
		int page = mailList.getPage()==null ? 1 : mailList.getPage();
		int pageSize = mailList.getPageSize()==null ? 10 : mailList.getPageSize();
		PageUtilBean pages = new PageUtilBean(pageSize, totalCount, page);
		mailList.setLimitsTart(pages.limitsTart());
		mailList.setLimitsEnd(pages.limitsEnd());
		List<MailListSimple> mailLists = mailListMapper.getMailList(mailList);
		pages.setResults(mailLists);
		return  ResultUtil.getResultData(true, StatusCode.SUCCESS, "操作成功！", pages);
	}
	/**
	 * 删除通讯录
	 */
	@Override 
	public ResultBase deleteMailList(Integer mailListId,Integer updateUserId) throws Exception,CustomException{
		int count = mailListMapper.getMailListTrueOrFalse(mailListId);
		
		if(count > 0) {
			MailList mailList = new MailList();
			mailList.setMailListId(mailListId);
			mailList.setUpdateUserId(updateUserId);
			mailList.setUpdateTime(Tools.getAPIresponseDateTime());
			mailList.setFlag(2);
//			int deleteCount = mailListMapper.deleteMailList(mailListId);
			int updateCount = mailListMapper.updateMailListFlag(mailList);
			if (updateCount > 0) {
				return ResultUtil.getResultBase("删除成功！");
			}
		}
		return ResultUtil.getResultBase("删除失败！");
	}
	/**
	 * 修改通讯录
	 */
	@Override 
	public ResultBase updateMailList(MailList mailList) throws Exception,CustomException{
		int count = mailListMapper.getMailListTrueOrFalse(mailList.getMailListId());
		if(count > 0) {
			mailList.setUpdateTime(Tools.getAPIresponseDateTime());
			int updateCount = mailListMapper.updateMailList(mailList);
			if (updateCount > 0) {
				return ResultUtil.getResultBase("修改成功！");
			}
		}
		return ResultUtil.getResultBase("修改失败！");
	}
	
	 
	
	
}
