package com.hc.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hc.common.exception.CustomException;
import com.hc.common.param_checkd.annotation.ParamCheck;
import com.hc.common.result.ResultBase;
import com.hc.mapper.emailTimingTask.TbEmailTimingTaskMapper;
import com.hc.para.page_base.BasePara;
import com.hc.pojo.task.TaskData;
import com.hc.pojo.task.TaskInfo;
import com.hc.service.TaskService;
import com.hc.utils.documentSequence.CreateSequence;
import com.hc.utils.result.ResultUtil;

@Service("taskService")
public class TaskServiceImpl implements TaskService{
private Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);
	
	@Autowired(required=false)
	private Scheduler scheduler;
	
	@Autowired
	private TbAsyncTaskImpl tbAsyncTaskImpl;
	
	@Autowired
	private TbEmailTimingTaskMapper tbEmailTimingTaskMapper;
		
	/**
	 * 所有任务列表
	 */
	public ResultBase list(){
		List<TaskInfo> list = new ArrayList<>();
		try {
			for(String groupJob: scheduler.getJobGroupNames()){
				for(JobKey jobKey: scheduler.getJobKeys(GroupMatcher.<JobKey>groupEquals(groupJob))){
					List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
					for (Trigger trigger: triggers) {
						Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
						JobDetail jobDetail = scheduler.getJobDetail(jobKey);
						String cronExpression = "", createTime = "";
						if (trigger instanceof CronTrigger) {
				            CronTrigger cronTrigger = (CronTrigger) trigger;
				            cronExpression = cronTrigger.getCronExpression();
				            createTime = cronTrigger.getDescription();
				        }
						TaskInfo info = new TaskInfo();
						info.setJobName(jobKey.getName());
						info.setJobGroup(jobKey.getGroup());
						info.setJobDescription(jobDetail.getDescription());
						info.setJobStatus(triggerState.name());
						info.setCronExpression(cronExpression);
						info.setCreateTime(createTime);
						list.add(info);
					}					
				}
			}			
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return ResultUtil.getResultData(list);
	}
	
	/**
	 * 保存定时任务
	 * @param info
	 * @throws Exception 
	 */
	@ParamCheck(names = {"cronExpression","to","title","content","tbAdminId"})
	public boolean addJob(TaskInfo info) throws Exception {
		String j_name = "com.hc.common.timingTask.MailJob";
		String g_name = CreateSequence.getTimeMillisSequence();
		String jobName = j_name, 
			   jobGroup = g_name,
			   cronExpression = info.getCronExpression(),
			   jobDescription = info.getJobDescription(),
			   createTime = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
		info.setJobName(j_name);
		info.setJobGroup(g_name);
		try {
			if (checkExists(jobName, jobGroup)) {
//				logger.info("add job fail, job already exist, jobGroup:{}, jobName:{}", jobGroup, jobName);
				return false;
		    }
			TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
			JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
			CronScheduleBuilder schedBuilder = CronScheduleBuilder.cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing();
			CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withDescription(createTime).withSchedule(schedBuilder).build();
			Class<? extends Job> clazz = (Class<? extends Job>)Class.forName(jobName);
			JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(jobKey).withDescription(jobDescription).build();
			scheduler.scheduleJob(jobDetail, trigger);
			tbAsyncTaskImpl.insertEmailTask(info);//这边是异步插入数据库
			return true;
		} catch (SchedulerException | ClassNotFoundException e) {
			logger.error("类名不存在或执行表达式错误,exception:{}",e.getMessage());
		}
		return false;
	}
	
	/**
	 * 修改定时任务
	 * @param info
	 */
	public boolean edit(TaskInfo info) {
		String jobName = info.getJobName(), 
			   jobGroup = info.getJobGroup(), 
			   cronExpression = info.getCronExpression(),
			   jobDescription = info.getJobDescription(),
			   createTime = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
		try {
			if (!checkExists(jobName, jobGroup)) {
//				logger.info("edit job fail, job is not exist, jobGroup:{}, jobName:{}", jobGroup, jobName);
				return false;
			}
			TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
	        JobKey jobKey = new JobKey(jobName, jobGroup);
	        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing();
	        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withDescription(createTime).withSchedule(cronScheduleBuilder).build();
	        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
	        jobDetail.getJobBuilder().withDescription(jobDescription);
	        HashSet<Trigger> triggerSet = new HashSet<>();
	    	triggerSet.add(cronTrigger);
	    	scheduler.scheduleJob(jobDetail, triggerSet, true);
	    	return true;
		} catch (SchedulerException e) {
//			logger.error("类名不存在或执行表达式错误,exception:{}",e.getMessage());
		}
		return false;
	}
	
	/**
	 * 删除定时任务
	 * @param jobName
	 * @param jobGroup
	 */
	public boolean delete(String jobName, String jobGroup){
		TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        try {
			if (checkExists(jobName, jobGroup)) {
				scheduler.pauseTrigger(triggerKey);
			    scheduler.unscheduleJob(triggerKey);
//			    logger.info("delete job, triggerKey:{},jobGroup:{}, jobName:{}", triggerKey ,jobGroup, jobName);
			    return true;
			}
		} catch (SchedulerException e) {
			logger.error(e.getMessage());
		}
        return false;
	}
	
	/**
	 * 暂停定时任务
	 * @param jobName
	 * @param jobGroup
	 */
	public boolean pause(String jobName, String jobGroup){
		TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
		try {
			if (checkExists(jobName, jobGroup)) {
				scheduler.pauseTrigger(triggerKey);
//			    logger.info("pause job success, triggerKey:{},jobGroup:{}, jobName:{}", triggerKey ,jobGroup, jobName);
			    return true;
			}
		} catch (SchedulerException e) {
			logger.error(e.getMessage());
		}
		return false;
	}
	
	/**
	 * 重新开始任务
	 * @param jobName
	 * @param jobGroup
	 */
	public boolean resume(String jobName, String jobGroup){
		TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        try {
			if (checkExists(jobName, jobGroup)) {
				scheduler.resumeTrigger(triggerKey);
//			    logger.info("resume job success,triggerKey:{},jobGroup:{}, jobName:{}", triggerKey ,jobGroup, jobName);
			    return true;
			}
		} catch (SchedulerException e) {
			logger.error(e.getMessage());
		}
        return false;
	}
	
	/**
	 * 验证是否存在
	 * @param jobName
	 * @param jobGroup
	 * @throws SchedulerException
	 */
	public boolean checkExists(String jobName, String jobGroup) throws SchedulerException{
		TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
		return scheduler.checkExists(triggerKey);
	}

	@Override
	@ParamCheck(names = {"id"})
	public ResultBase getUserTaskList(BasePara base, HttpServletRequest request) throws Exception, CustomException {
		List<TaskData> list = tbEmailTimingTaskMapper.getTaskByAdminId(base);
		if(list==null) {
			return ResultUtil.getResultQuery("没有数据！");
		}
		return ResultUtil.getResultQuery(list, tbEmailTimingTaskMapper.getTaskByAdminIdCount(base));
	}
	
}
