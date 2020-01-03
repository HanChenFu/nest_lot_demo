package com.hc.common.timingTask;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hc.service.impl.TbAsyncTaskImpl;

@Component
public class LetterJob implements Job{
	private Logger logger = LoggerFactory.getLogger(LetterJob.class);
	
	@Autowired
	private TbAsyncTaskImpl tbAsyncTaskImpl;
	

	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
//			tbAsyncTaskImpl.sendSM(new TbShortPara("1","18122711575","1234567"));
//			System.out.println("~~~~~task LetterJob run~~~~~");
//			tbEmailTimingTaskMapper.getSendNumber(tbJobGroup);
		} catch (Exception e) {
			logger.error("LetterJob:"+e);
		}
		logger.info("JobName: {}", "LetterJob" + context.getJobDetail().getKey().getName());
	}
	
}
