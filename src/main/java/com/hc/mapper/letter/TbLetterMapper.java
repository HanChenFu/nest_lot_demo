package com.hc.mapper.letter;

import java.util.List;

import com.hc.para.page_base.BasePara;
import com.hc.pojo.letter.TbLetter;
import com.hc.pojo.usually.LetterPageData;

public interface TbLetterMapper {
	
	List<LetterPageData> getLetterMess(BasePara para);
	
	int getLetterMessCount(BasePara para);
	
	int insertSelective(TbLetter letter);
	
	int deleLetter(List<Integer> list);
	
	int updateNameById(TbLetter letter);
}
