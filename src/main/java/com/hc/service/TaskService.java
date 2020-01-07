package com.hc.service;

import javax.servlet.http.HttpServletRequest;

import org.quartz.SchedulerException;
import org.springframework.web.multipart.MultipartFile;

import com.hc.common.exception.CustomException;
import com.hc.common.result.ResultBase;
import com.hc.para.page_base.BasePara;
import com.hc.pojo.task.TaskInfo;

public interface TaskService {

	ResultBase list();
	
	ResultBase addJob(TaskInfo info,int type,MultipartFile file) throws Exception;
	
	ResultBase edit(TaskInfo info,int type)  throws Exception;
	
	ResultBase delete(String jobName, String jobGroup,int type) throws Exception;
	
	boolean pause(String jobName, String jobGroup);
	
	boolean resume(String jobName, String jobGroup);
	
	boolean checkExists(String jobName, String jobGroup) throws SchedulerException;
	
	ResultBase getUserTaskList(BasePara base,HttpServletRequest request,int type) throws Exception, CustomException;
	
}
