package com.hc.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hc.common.exception.CustomException;
import com.hc.common.param_checkd.annotation.ParamCheck;
import com.hc.common.redis.RedisUtil;
import com.hc.common.result.ResultBase;
import com.hc.common.result.ResultQuery;
import com.hc.mapper.askRecord.TbAskRecordMapper;
import com.hc.pojo.askRecord.TbAskRecord;
import com.hc.service.TbAskRecordService;
import com.hc.utils.documentSequence.CreateSequence;
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
	public ResultQuery<TbAskRecord> getAskRecord(TbAskRecord ask, HttpServletRequest request)
			throws Exception, CustomException {
		List<TbAskRecord> li = tbAskRecordMapper.getAskRecord(ask);
		if(li==null) {
			return ResultUtil.getResultQuery("没有数据！");
		}
		return ResultUtil.getResultQuery(li, tbAskRecordMapper.getAskRecordCount());
	}

	@Override
	@ParamCheck(names = {"tbUserId","tbName"})
	public ResultBase insertSelective(TbAskRecord ask, HttpServletRequest request) throws Exception, CustomException {
		ask.setTbNumber(CreateSequence.getTimeMillisSequence());
		int size = tbAskRecordMapper.insertSelective(ask);
		if (size>0) {
			return ResultUtil.getResultBase("操作成功！");
		}
		return ResultUtil.getResultBase("操作失败！");
	}

}
