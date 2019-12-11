package com.hc.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hc.common.exception.CustomException;
import com.hc.common.redis.RedisUtil;
import com.hc.common.result.ResultQuery;
import com.hc.mapper.letter.TbLetterMapper;
import com.hc.para.page_base.BasePara;
import com.hc.pojo.base.TbAdmin;
import com.hc.pojo.usually.LetterPageData;
import com.hc.service.TbLetterService;
import com.hc.utils.redis.LoginUserUtil;
import com.hc.utils.result.ResultUtil;

@Service("tbLetterService")
public class TbLetterServiceImpl implements TbLetterService{
	
	@Autowired
	RedisUtil redis;
	
	@Autowired
	TbLetterMapper tbLetterMapper;
	
	@Autowired
	LoginUserUtil loginUserUtil;
	
	@Override
	public ResultQuery<LetterPageData> getLetterMess(BasePara para,HttpServletRequest request) throws Exception, CustomException {
		TbAdmin t = loginUserUtil.getLoginUser(request.getHeader("token"));
		para.setId(t.getTbId());//这边是把登陆用户添加进去
		List<LetterPageData> l = tbLetterMapper.getLetterMess(para);
		if(l==null) {
			return ResultUtil.getResultQuery("没有数据！");
		}
		return ResultUtil.getResultQuery(l, tbLetterMapper.getLetterMessCount());
	}

}
