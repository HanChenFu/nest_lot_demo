package com.hc.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hc.common.code.StatusCode;
import com.hc.common.exception.CustomException;
import com.hc.common.param_checkd.annotation.ParamCheck;
import com.hc.common.redis.RedisUtil;
import com.hc.common.result.ResultBase;
import com.hc.common.result.ResultQuery;
import com.hc.mapper.askRecord.TbAskRecordMapper;
import com.hc.para.page_base.BasePara;
import com.hc.pojo.askRecord.TbAskRecord;
import com.hc.pojo.askRecord.TbAskRecordPara;
import com.hc.service.TbAskRecordService;
import com.hc.utils.redis.LoginUserUtil;
import com.hc.utils.result.ResultUtil;

@Service("tbAskRecordService")
public class TbAskRecordServiceImpl implements TbAskRecordService{

	@Autowired
	RedisUtil redis;
	
	@Autowired
	TbAskRecordMapper tbAskRecordMapper;
	
	@Autowired
	LoginUserUtil loginUserUtil;
	
	@Override
	@ParamCheck(names = {"tbAdminId"})
	public ResultQuery<TbAskRecordPara> getAskRecord(TbAskRecord ask, HttpServletRequest request)
			throws Exception, CustomException {
//		TbAdmin t = loginUserUtil.getLoginUser(request.getHeader("token"));
		if (ask==null) {
			ask = new TbAskRecord();
		}
		ask.setTbAdminId(String.valueOf(ask.getTbAdminId()));
		List<TbAskRecordPara> li = tbAskRecordMapper.getAskRecord(ask);
		if(li==null) {
			return ResultUtil.getResultQuery("没有数据！");
		}
		return ResultUtil.getResultQuery(li, tbAskRecordMapper.getAskRecordCount(ask),ask.getPage(),ask.getPageSize());
	}

	@Override
	@ParamCheck(names = {"tbAdminId","tbUserId","tbName","tbDraft","tbNumber"})
	public ResultBase insertSelective(TbAskRecord ask, HttpServletRequest request) throws Exception, CustomException {
//		ask.setTbNumber(CreateSequence.getTimeMillisSequence());
		String id = tbAskRecordMapper.checkTbNumberIsExist(ask);
		if(id!=null) {
			return ResultUtil.getResultBase(false, StatusCode.FAIL, "该文件名已经存在！");
		}
		int size = tbAskRecordMapper.insertSelective(ask);
		if (size>0) {
			return ResultUtil.getResultBase("操作成功！");
		}
		return ResultUtil.getResultBase("操作失败！");
	}

	@Override
	@ParamCheck(names = {"listid"})
	public ResultBase deleAskRecord(BasePara base, HttpServletRequest request) throws Exception, CustomException {
		List<Integer> li = base.getListid();
		int size = tbAskRecordMapper.deleAskRecord(li);
		if (size>0) {
			return ResultUtil.getResultBase("删除成功！");
		}
		return ResultUtil.getResultBase("删除失败！");
	}

	@Override
	@ParamCheck(names = {"tbId"})
	public ResultBase updateAskRecord(TbAskRecord ask, HttpServletRequest request) throws Exception, CustomException {
		int size = tbAskRecordMapper.updateAskRecord(ask);
		if (size>0) {
			return ResultUtil.getResultBase("更新成功！");
		}
		return ResultUtil.getResultBase("更新失败！");
	}

}
