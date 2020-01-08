package com.hc.mapper.askRecord;

import java.util.List;
import com.hc.pojo.askRecord.TbAskRecord;
import com.hc.pojo.askRecord.TbAskRecordPara;

public interface TbAskRecordMapper {
	
	int insertSelective(TbAskRecord ask);
	
	List<TbAskRecordPara> getAskRecord(TbAskRecord ask);
	
	int getAskRecordCount(TbAskRecord ask);
	
	int deleAskRecord(List<Integer> list);
	
	int updateAskRecord(TbAskRecord ask);
	
	String checkTbNumberIsExist(TbAskRecord ask);
	
}
