package com.hc.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.http.entity.ContentType;
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
import org.springframework.web.multipart.MultipartFile;

import com.hc.common.code.StatusCode;
import com.hc.common.exception.CustomException;
import com.hc.common.param_checkd.annotation.ParamCheck;
import com.hc.common.result.ResultBase;
import com.hc.mapper.emailTimingTask.TbEmailTimingTaskMapper;
import com.hc.mapper.letterTimingTask.TbLetterTimingTaskMapper;
import com.hc.para.page_base.BasePara;
import com.hc.pojo.task.TaskData;
import com.hc.pojo.task.TaskInfo;
import com.hc.service.TaskService;
import com.hc.utils.documentSequence.CreateSequence;
import com.hc.utils.file.FileUtil;
import com.hc.utils.result.ResultUtil;
import com.hc.utils.string.FormatCheck;

@Service("taskService")
public class TaskServiceImpl implements TaskService{
private Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);
	
	@Autowired(required=false)
	private Scheduler scheduler;
	
	@Autowired
	private TbAsyncTaskImpl tbAsyncTaskImpl;
	
	@Autowired
	private TbEmailTimingTaskMapper tbEmailTimingTaskMapper;
	
	@Autowired
	private TbLetterTimingTaskMapper tbLetterTimingTaskMapper;
		
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
	public ResultBase addJob(TaskInfo info,int type,MultipartFile file) throws Exception {
		String[] str = info.getTo().split(",");
		String j_name = "";
		MultipartFile file2 = null;
		if(type==0) {
			j_name = "com.hc.common.timingTask.MailJob";
			for (int i = 0; i < str.length; i++) {
				if (!FormatCheck.isEmail(str[i])) {
					return ResultUtil.getResultBase(false, StatusCode.PARAM_ERROR, "邮箱格式不对!");
				}
			}
		}else {
			j_name = "com.hc.common.timingTask.LetterJob";
			for (int i = 0; i < str.length; i++) {
				if (!FormatCheck.isMobilePhone(str[i])) {
					return ResultUtil.getResultBase(false, StatusCode.PARAM_ERROR, "手机格式不对!");
				}
			}
		}
		if(file!=null) {
			if(!FileUtil.checkEnclosureFormat(file)) {
				return ResultUtil.getResultBase("附件需为压缩格式!");
			}
			byte[] pdfFile = IOUtils.toByteArray(file.getInputStream());
			file2 = new org.springframework.mock.web.MockMultipartFile("file",file.getOriginalFilename(),ContentType.APPLICATION_OCTET_STREAM.toString(), pdfFile);
		}
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
				return ResultUtil.getResultBase(false, StatusCode.NULL, "该任务已经不存在!");
		    }
			TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
			JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
			CronScheduleBuilder schedBuilder = CronScheduleBuilder.cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing();
			CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withDescription(createTime).withSchedule(schedBuilder).build();
			Class<? extends Job> clazz = (Class<? extends Job>)Class.forName(jobName);
			JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(jobKey).withDescription(jobDescription).build();
			scheduler.scheduleJob(jobDetail, trigger);
			if (type==0) {
				tbAsyncTaskImpl.insertEmailTask(info,file2);//这边是异步插入数据库
			}else {
				tbAsyncTaskImpl.insertLetterTask(info);//这边是异步插入数据库
			}
			file = null;
			return ResultUtil.getResultBase(true);
		} catch (SchedulerException | ClassNotFoundException e) {
			logger.error("类名不存在或执行表达式错误,exception:{}",e.getMessage());
		}
		return ResultUtil.getResultBase(false);
	}
	
	/**
	 * 修改定时任务
	 * @param info
	 * @throws Exception 
	 */
	@ParamCheck(names = {"jobName","jobGroup","cronExpression"})
	public ResultBase edit(TaskInfo info,int type) throws Exception {
		String jobName = info.getJobName(), 
			   jobGroup = info.getJobGroup(), 
			   cronExpression = info.getCronExpression(),
			   jobDescription = info.getJobDescription(),
			   createTime = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
		try {
			if (!checkExists(jobName, jobGroup)) {
//				logger.info("edit job fail, job is not exist, jobGroup:{}, jobName:{}", jobGroup, jobName);
				return ResultUtil.getResultBase(false, StatusCode.NULL, "该任务已经不存在!");
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
	    	if (type ==0) {
		    	tbAsyncTaskImpl.updateEmailTask(info);
	    	}else {
	    		tbAsyncTaskImpl.updateLetterTask(info);
	    	}
			return ResultUtil.getResultBase(true);
		} catch (SchedulerException e) {
//			logger.error("类名不存在或执行表达式错误,exception:{}",e.getMessage());
		}
		return ResultUtil.getResultBase(false);
	}
	
	/**
	 * 删除定时任务
	 * @param jobName
	 * @param jobGroup
	 * 
	 * 	/**任务名称*/
	public ResultBase delete(String jobName, String jobGroup) throws Exception{
		if (jobName == null || jobGroup == null || (!"".contentEquals(jobName))||(!"".equals(jobGroup))) {
			return ResultUtil.getResultBase(false);
		}
		TriggerKey triggerKey = TriggerKey.triggerKey(jobName,jobGroup);
        try {
			if (checkExists(jobName, jobGroup)) {
				scheduler.pauseTrigger(triggerKey);
			    scheduler.unscheduleJob(triggerKey);
//			    logger.info("delete job, triggerKey:{},jobGroup:{}, jobName:{}", triggerKey ,jobGroup, jobName);
				return ResultUtil.getResultBase(true);
			}
		} catch (SchedulerException e) {
			logger.error(e.getMessage());
		}
		return ResultUtil.getResultBase(false);
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
	public ResultBase getUserTaskList(BasePara base, HttpServletRequest request,int type) throws Exception,CustomException {
		if(type ==0) {
			List<TaskData> list = tbEmailTimingTaskMapper.getTaskByAdminId(base);
			if(list==null) {
				return ResultUtil.getResultQuery("没有数据！");
			}
			return ResultUtil.getResultQuery(list, tbEmailTimingTaskMapper.getTaskByAdminIdCount(base));
		}else {
			List<TaskData> list = tbLetterTimingTaskMapper.getTaskByAdminId(base);
			if(list==null) {
				return ResultUtil.getResultQuery("没有数据！");
			}
			return ResultUtil.getResultQuery(list, tbLetterTimingTaskMapper.getTaskByAdminIdCount(base));
		}
	}
	
}
