package com.hc.mapper.shortMess;

import java.util.List;
import com.hc.para.page_base.BasePara;
import com.hc.pojo.shortMess.TbShortMess;
import com.hc.pojo.usually.LetterPageData;

public interface TbShortMessMapper {
	
	List<LetterPageData> getShortMess(BasePara para);
	
	int getShortMessCount(BasePara para);
	
	int insertSelective(TbShortMess letter);
	
	int deleShort(List<Integer> list);
	
	int updateNameById(TbShortMess letter);
	
}
