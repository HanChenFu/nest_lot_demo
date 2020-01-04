package com.hc.common.timingTask;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hc.mapper.letterTimingTask.TbLetterTimingTaskMapper;
import com.hc.pojo.shortMess.TbShortPara;
import com.hc.pojo.task.TaskData;
import com.hc.service.impl.TbAsyncTaskImpl;

@Component
public class LetterJob implements Job{
	private Logger logger = LoggerFactory.getLogger(LetterJob.class);
	
	@Autowired
	private TbAsyncTaskImpl tbAsyncTaskImpl;
	
	@Autowired
	private TbLetterTimingTaskMapper tbLetterTimingTaskMapper;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			System.out.println("~~~~~task MailJob run~~~~~");
			String name = context.getJobDetail().getKey().getName();
			String group = context.getJobDetail().getKey().getGroup();
			TaskData task = tbLetterTimingTaskMapper.getByJobGroup(group);
			String[] to = task.getTarget().split(",");
			for (int i = 0; i < to.length; i++) {
				try {
					tbAsyncTaskImpl.sendSM(new TbShortPara(String.valueOf(task.getTbAdminId()),to[i],task.getTbContent()));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			logger.error("LetterJob:"+e);
		}
		logger.info("JobName: {}", "LetterJob" + context.getJobDetail().getKey().getName());
	}
	
}
