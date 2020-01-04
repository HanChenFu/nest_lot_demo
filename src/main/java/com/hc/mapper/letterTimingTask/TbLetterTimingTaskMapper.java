package com.hc.mapper.letterTimingTask;

import java.util.List;

import com.hc.para.page_base.BasePara;
import com.hc.pojo.TbLetterTimingTask.TbLetterTimingTask;
import com.hc.pojo.task.TaskData;
import com.hc.pojo.task.TaskInfo;

public interface TbLetterTimingTaskMapper {
	
	int insertSelective(TbLetterTimingTask task);
	
	TaskData getByJobGroup(String tbJobGroup);
	
	boolean updateNumber(int tbId);
	
	List<TaskData> getTaskByAdminId(BasePara base);
	
	int getTaskByAdminIdCount(BasePara base);
	
	int updateNumberToZero();
	
	int deleAllTask(String jobGroup);
	
	int updateTaskByjobGroup(TaskInfo info);
}
