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
import com.hc.mapper.letter.TbLetterMapper;
import com.hc.para.page_base.BasePara;
import com.hc.pojo.letter.TbLetter;
import com.hc.pojo.usually.LetterPageData;
import com.hc.service.TbLetterService;
import com.hc.utils.documentSequence.CreateSequence;
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
	@ParamCheck(names = {"id"})
	public ResultQuery<LetterPageData> getLetterMess(BasePara para,HttpServletRequest request) throws Exception, CustomException {
//		TbAdmin t = loginUserUtil.getLoginUser(request.getHeader("token"));
//		para.setId(t.getTbId());//这边是把登陆用户添加进去
		List<LetterPageData> l = tbLetterMapper.getLetterMess(para);
		if(l==null) {
			return ResultUtil.getResultQuery("没有数据！");
		}
		return ResultUtil.getResultQuery(l, tbLetterMapper.getLetterMessCount());
	}

	@Override
	@ParamCheck(names = {"tbUserId","sendMessId","sendingState"})
	public ResultBase insertSelective(TbLetter letter, HttpServletRequest request) throws Exception, CustomException {
		letter.setTbNumber(CreateSequence.getTimeMillisSequence());
		int size = tbLetterMapper.insertSelective(letter);
		if (size>0) {
			return ResultUtil.getResultBase("添加成功！");
		}
		return ResultUtil.getResultBase("添加失败！");
	}

	@Override
	@ParamCheck(names = {"listid"})
	public ResultBase deleLetter(BasePara para,HttpServletRequest request) throws Exception, CustomException {
		List<Integer> li = para.getListid();
		int size = tbLetterMapper.deleLetter(li);
		if (size>0) {
			return ResultUtil.getResultBase("删除成功！");
		}
		return ResultUtil.getResultBase("删除失败！");
	}

}
