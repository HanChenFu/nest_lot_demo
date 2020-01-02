package com.hc.common.timingTask;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hc.pojo.email.TbEmail;
import com.hc.service.impl.TbAsyncTaskImpl;

@Component
public class MailJob implements Job{
	private Logger logger = LoggerFactory.getLogger(MailJob.class);
	
	@Autowired
	private TbAsyncTaskImpl tbAsyncTaskImpl;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			tbAsyncTaskImpl.sendEmail(new TbEmail("1", "280888608@qq.com", "hello", "你好啊，我是定时任务", null), null);
			System.out.println("~~~~~task MailJob run~~~~~");
		} catch (Exception e) {
			logger.error("MailJob:"+e);
		}
		logger.info("JobName: {}", "MailJob" + context.getJobDetail().getKey().getName());
	}
	
}
