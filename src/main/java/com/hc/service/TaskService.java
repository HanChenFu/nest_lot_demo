package com.hc.service;

import javax.servlet.http.HttpServletRequest;
import org.quartz.SchedulerException;
import com.hc.common.exception.CustomException;
import com.hc.common.result.ResultBase;
import com.hc.para.page_base.BasePara;
import com.hc.pojo.task.TaskInfo;

public interface TaskService {

	ResultBase list();
	
	boolean addJob(TaskInfo info) throws Exception;
	
	boolean edit(TaskInfo info);
	
	boolean delete(String jobName, String jobGroup);
	
	boolean pause(String jobName, String jobGroup);
	
	boolean resume(String jobName, String jobGroup);
	
	boolean checkExists(String jobName, String jobGroup) throws SchedulerException;
	
	ResultBase getUserTaskList(BasePara base,HttpServletRequest request) throws Exception, CustomException;
	
}
