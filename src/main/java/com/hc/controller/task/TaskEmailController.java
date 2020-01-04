package com.hc.controller.task;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hc.common.exception.CustomException;
import com.hc.common.result.ResultBase;
import com.hc.para.page_base.BasePara;
import com.hc.pojo.task.TaskInfo;
import com.hc.service.TaskService;

@Controller
@RequestMapping("/email/task/")
@ResponseBody
public class TaskEmailController {
	
	@Autowired(required=false)
	private TaskService taskService;

	/**
	 * 任务列表
	 * @return
	 */
	@RequestMapping(value="list")
	public ResultBase list(){
//		Map<String, Object> map = new HashMap<>();
		return taskService.list();
	}
	
	/**
	 * 保存定时任务
	 * @param info
	 */
	@RequestMapping(value="save")
	public ResultBase save(@RequestBody(required = false) TaskInfo info) throws Exception,CustomException{
		return taskService.addJob(info,0);
	}
	
	/**
	 * 保存定时任务
	 * @param info
	 */
	@RequestMapping(value="edit")
	public ResultBase edit(@RequestBody(required = false) TaskInfo info) throws Exception,CustomException{
		return taskService.edit(info,0);
	}
	
	/**
	 * 删除定时任务
	 * @param jobName
	 * @param jobGroup
	 */
	@RequestMapping(value="delete")
	public ResultBase delete(@RequestBody(required = false) TaskInfo info) throws Exception,CustomException{
		return taskService.delete(info,0);
	}
	
	/**
	 * 暂停定时任务
	 * @param jobName
	 * @param jobGroup
	 */
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
	
	@RequestMapping("/getUserTaskList")
	public ResultBase getUserTaskList(@RequestBody BasePara base, HttpServletRequest request) throws Exception,CustomException {
		return taskService.getUserTaskList(base, request,0);
	}

}
