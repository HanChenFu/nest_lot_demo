package com.hc.service;

import java.util.List;

import com.hc.pojo.entity.TbCase;

public interface TbCaseService {
	int queryNumber(int tbCaseTypeId);

	List<TbCase> queryForPage(Integer tbCaseTypeId, String time, String tbNumber, String tbAddress, String tbSize,
			Integer tbStar);
}
