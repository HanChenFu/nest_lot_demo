package com.hc.controller.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hc.common.result.ResultBase;
import com.hc.pojo.task.TaskInfo;
import com.hc.service.TaskService;

@Controller
@RequestMapping("/qy/task/")
public class TaskManageController {
	
	@Autowired(required=false)
	private TaskService taskService;

	/**
	 * 任务列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="list")
	public ResultBase list(){
//		Map<String, Object> map = new HashMap<>();
		return taskService.list();
	}
	
	/**
	 * 保存定时任务
	 * @param info
	 */
	@ResponseBody
	@RequestMapping(value="save")
	public String save(@RequestBody TaskInfo info){
		try {
			if(info.getId() == 0) {
				taskService.addJob(info);
			}else{
				taskService.edit(info);
			}
		} catch (Exception e) {
			return e.getMessage();
		}
		return "成功";
	}
	
	/**
	 * 删除定时任务
	 * @param jobName
	 * @param jobGroup
	 */
	@ResponseBody
	@RequestMapping(value="delete")
	public String delete(@RequestBody TaskInfo info){
//	public String delete(@PathVariable String jobName, @PathVariable String jobGroup){
		try {
			taskService.delete(info.getJobName(), info.getJobGroup());
		} catch (Exception e) {
			return e.getMessage();
		}
		return "成功";
	}
	
	/**
	 * 暂停定时任务
	 * @param jobName
	 * @param jobGroup
	 */
	@ResponseBody
	@RequestMapping(value="pause")
//	public String pause(@PathVariable String jobName, @PathVariable String jobGroup){
	public String pause(@RequestBody TaskInfo info){
		try {
			taskService.pause(info.getJobName(), info.getJobGroup());
		} catch (Exception e) {
			return e.getMessage();
		}
		return "成功";
	}
	
	/**
	 * 重新开始定时任务
	 * @param jobName
	 * @param jobGroup
	 */
	@ResponseBody
	@RequestMapping(value="resume")
	public String resume(@RequestBody TaskInfo info){
		try {
			taskService.resume(info.getJobName(), info.getJobGroup());
		} catch (Exception e) {
			return e.getMessage();
		}
		return "成功";
	}

}
