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
import com.hc.service.TaskService;
import com.hc.service.impl.TbAsyncTaskImpl;

@Component
public class LetterJob implements Job{
	private Logger logger = LoggerFactory.getLogger(LetterJob.class);
	
	@Autowired(required = false)
	private TaskService taskService;
	
	@Autowired
	private TbAsyncTaskImpl tbAsyncTaskImpl;
	
	@Autowired
	private TbLetterTimingTaskMapper tbLetterTimingTaskMapper;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		String name = context.getJobDetail().getKey().getName();
		String group = context.getJobDetail().getKey().getGroup();
		try {
			System.out.println("~~~~~task LetterJob run~~~~~");
			TaskData task = tbLetterTimingTaskMapper.getByJobGroup(group);
			if (task.getTbNumber() > 0) {
				String[] to = task.getTarget().split(",");
				for (int i = 0; i < to.length; i++) {
					try {
						tbAsyncTaskImpl.sendSM(new TbShortPara(String.valueOf(task.getTbAdminId()),to[i],task.getTbContent()));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				tbLetterTimingTaskMapper.updateNumber(task.getTbId());
			}else {
				taskService.delete(name,group);
			}
		} catch (Exception e) {
			logger.error("LetterJob:"+e);
		}
//		logger.info("JobName: {}", "LetterJob" + context.getJobDetail().getKey().getName());
	}
	
}
