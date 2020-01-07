package com.hc.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hc.common.code.StatusCode;
import com.hc.common.exception.CustomException;
import com.hc.common.param_checkd.annotation.ParamCheck;
import com.hc.common.result.ResultBase;
import com.hc.common.result.ResultQuery;
import com.hc.mapper.shortMess.TbShortMessMapper;
import com.hc.para.page_base.BasePara;
import com.hc.pojo.shortMess.TbShortMess;
import com.hc.pojo.shortMess.TbShortPara;
import com.hc.pojo.usually.LetterPageData;
import com.hc.service.TbSMService;
import com.hc.utils.result.ResultUtil;
import com.hc.utils.string.FormatCheck;

@Service("tbSMService")
public class TbSMServiceImpl implements TbSMService{
	
	@Autowired
	private TbAsyncTaskImpl tbAsyncTaskImpl;
	
	@Autowired
	private TbShortMessMapper tbShortMessMapper;
	
	@Override
	@ParamCheck(names = {"to","title","content","tbAdminId"})
	public ResultBase sendSM(TbShortPara shortPara, HttpServletRequest request) throws Exception, CustomException {
		String[] str = shortPara.getTo().split(",");
		for (int i = 0; i < str.length; i++) {
			if (!FormatCheck.isMobilePhone(str[i])) {
				return ResultUtil.getResultBase(false, StatusCode.PARAM_ERROR, "手机格式不对!");
			}
		}
		tbAsyncTaskImpl.sendSM(shortPara);
	   return ResultUtil.getResultBase("短信已经发送!");
	}
	
	@Override
	@ParamCheck(names = {"id"})
	public ResultQuery<LetterPageData> getShortMess(BasePara para,HttpServletRequest request) throws Exception, CustomException {
		List<LetterPageData> l = tbShortMessMapper.getShortMess(para);
		if(l==null) {
			return ResultUtil.getResultQuery("没有数据！");
		}
		return ResultUtil.getResultQuery(l, tbShortMessMapper.getShortMessCount(para),para.getPage(),para.getPageSize());
	}


	@Override
	@ParamCheck(names = {"listid"})
	public ResultBase deleShort(BasePara para, HttpServletRequest request) throws Exception, CustomException {
		List<Integer> li = para.getListid();
		int size = tbShortMessMapper.deleShort(li);
		if (size>0) {
			return ResultUtil.getResultBase("删除成功！");
		}
		return ResultUtil.getResultBase("删除失败！");
	}

	@Override
	@ParamCheck(names = {"tbId","nickname"})
	public ResultBase updateNameById(TbShortMess letter, HttpServletRequest request) throws Exception, CustomException {
		int size = tbShortMessMapper.updateNameById(letter);
		if (size>0) {
			return ResultUtil.getResultBase("修改成功！");
		}
		return ResultUtil.getResultBase("修改失败！");
	}

}
