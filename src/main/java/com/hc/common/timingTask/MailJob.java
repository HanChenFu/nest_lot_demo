package com.hc.common.timingTask;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hc.mapper.emailTimingTask.TbEmailTimingTaskMapper;
import com.hc.pojo.email.TbEmail;
import com.hc.pojo.task.TaskData;
import com.hc.service.TaskService;
import com.hc.service.impl.TbAsyncTaskImpl;

@Component
public class MailJob implements Job{
	private Logger logger = LoggerFactory.getLogger(MailJob.class);
	
	@Autowired(required=false)
	private TaskService taskService;
	
	@Autowired
	private TbAsyncTaskImpl tbAsyncTaskImpl;
	
	@Autowired
	private TbEmailTimingTaskMapper tbEmailTimingTaskMapper;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		String name = context.getJobDetail().getKey().getName();
		String group = context.getJobDetail().getKey().getGroup();
		try {
			System.out.println("~~~~~task MailJob run~~~~~");
			TaskData task = tbEmailTimingTaskMapper.getByJobGroup(group);
			int num = task.getTbNumber();
			if(num > 0) {
				String[]  to = task.getTarget().split(",");
				String title = task.getTbTitle();
				String content = task.getTbContent();
				for (int i = 0; i < to.length; i++) {
					tbAsyncTaskImpl.sendEmail(new TbEmail(String.valueOf(task.getTbAdminId()), to[i], title, content, null),null);
				}
				tbEmailTimingTaskMapper.updateNumber(task.getTbId());//这边是把执行次数减去一
			}else {
				taskService.delete(name,group);
			}
		} catch (Exception e) {
			logger.error("MailJob:"+e);
		}
		logger.info("JobName: {}", "MailJob" + name);
	}
	
}



