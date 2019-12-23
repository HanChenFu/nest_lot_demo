package com.hc.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hc.common.exception.CustomException;
import com.hc.common.param_checkd.annotation.ParamCheck;
import com.hc.common.result.ResultBase;
import com.hc.common.result.ResultQuery;
import com.hc.mapper.shortMess.TbShortMessMapper;
import com.hc.para.page_base.BasePara;
import com.hc.pojo.email.TbEmail;
import com.hc.pojo.usually.LetterPageData;
import com.hc.service.TbSMService;
import com.hc.utils.result.ResultUtil;

@Service("tbSMService")
public class TbSMServiceImpl implements TbSMService{
	
	@Autowired
	private TbAsyncTaskImpl tbAsyncTaskImpl;
	
	@Autowired
	private TbShortMessMapper tbShortMessMapper;
	
	@Override
	public ResultBase sendSM(TbEmail tbEmail, HttpServletRequest request) throws Exception, CustomException {
		tbAsyncTaskImpl.sendSM(tbEmail);
	   return ResultUtil.getResultBase("短信已经发送!");
	}
	
	@Override
	@ParamCheck(names = {"id"})
	public ResultQuery<LetterPageData> getShortMess(BasePara para,HttpServletRequest request) throws Exception, CustomException {
		List<LetterPageData> l = tbShortMessMapper.getShortMess(para);
		if(l==null) {
			return ResultUtil.getResultQuery("没有数据！");
		}
		return ResultUtil.getResultQuery(l, tbShortMessMapper.getShortMessCount(para));
	}


	@Override
	public ResultBase deleShort(BasePara para, HttpServletRequest request) throws Exception, CustomException {
		List<Integer> li = para.getListid();
		int size = tbShortMessMapper.deleShort(li);
		if (size>0) {
			return ResultUtil.getResultBase("删除成功！");
		}
		return ResultUtil.getResultBase("删除失败！");
	}

}
