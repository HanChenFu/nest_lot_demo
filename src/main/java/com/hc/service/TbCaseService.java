package com.hc.service;

import java.sql.Timestamp;
import java.util.List;

import com.hc.pojo.entity.TbCase;

public interface TbCaseService {
	int queryNumber(int tbCaseTypeId);

	List<TbCase> queryForPage(Integer tbCaseTypeId, Timestamp time, String tbNumber, String tbAddress, String tbSize,
			Integer tbStar);
}
