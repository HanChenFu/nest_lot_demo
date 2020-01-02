package com.hc.service;

import org.quartz.SchedulerException;

import com.hc.common.result.ResultBase;
import com.hc.pojo.task.TaskInfo;

public interface TaskService {

	ResultBase list();
	
	boolean addJob(TaskInfo info);
	
	boolean edit(TaskInfo info);
	
	boolean delete(String jobName, String jobGroup);
	
	boolean pause(String jobName, String jobGroup);
	
	boolean resume(String jobName, String jobGroup);
	
	boolean checkExists(String jobName, String jobGroup) throws SchedulerException;
	
}
