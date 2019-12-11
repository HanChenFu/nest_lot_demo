package com.hc.service;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;

import com.hc.pojo.entity.TbNotice;

public interface TbNoticeService {
	
	// 查询通知公告
	List<T> queryNotice();
}
