package com.hc.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hc.common.redis.RedisUtil;
import com.hc.mapper.askRecord.TbAskRecordMapper;
import com.hc.mapper.emailTask.TbEmailTaskMapper;
import com.hc.mapper.emailTimingTask.TbEmailTimingTaskMapper;
import com.hc.mapper.letter.TbLetterMapper;
import com.hc.mapper.letterTask.TbLetterTaskMapper;
import com.hc.mapper.letterTimingTask.TbLetterTimingTaskMapper;
import com.hc.mapper.sendMess.TbSendMessMapper;
import com.hc.mapper.shortMess.TbShortMessMapper;
import com.hc.mapper.user.TbUserMapper;
import com.hc.pojo.TbLetterTimingTask.TbLetterTimingTask;
import com.hc.pojo.email.TbEmail;
import com.hc.pojo.emailTask.EmailTask;
import com.hc.pojo.emailTimingTask.TbEmailTimingTask;
import com.hc.pojo.letter.TbLetter;
import com.hc.pojo.letterTask.LetterTask;
import com.hc.pojo.sendMess.TbSendMess;
import com.hc.pojo.shortMess.TbShortMess;
import com.hc.pojo.shortMess.TbShortPara;
import com.hc.pojo.task.TaskData;
import com.hc.pojo.task.TaskInfo;
import com.hc.pojo.user.TbUser;
import com.hc.utils.conig.SystemConfigUtil;
import com.hc.utils.documentSequence.CreateSequence;
import com.hc.utils.email.SendMail;
import com.hc.utils.file.FileUtil;
import com.hc.utils.redis.LoginUserUtil;
import com.hc.utils.sendCode.AliyunSMSUtil;
import com.hc.utils.string.FormatCheck;

@Service("tbAsyncTaskImpl")
public class TbAsyncTaskImpl {
	@Autowired
	RedisUtil redis;
	
	@Autowired
	TbAskRecordMapper tbAskRecordMapper;
	
	@Autowired
	TaskServiceImpl taskServiceImpl;
	
	@Autowired
	LoginUserUtil loginUserUtil;
	
	@Autowired
	TbUserMapper tbUserMapper;
	
	@Autowired
	TbLetterMapper tbLetterMapper;
	
	@Autowired
	TbSendMessMapper tbSendMessMapper;
	
	@Autowired
	TbShortMessMapper tbShortMessMapper;
	
	@Autowired
	TbEmailTaskMapper tbEmailTaskMapper;
	
	@Autowired
	TbEmailTimingTaskMapper TbEmailTimingTaskMapper;
	
	@Autowired
	TbLetterTimingTaskMapper tbLetterTimingTaskMapper;
	
	@Autowired
	TbLetterTaskMapper tbLetterTaskMapper;
	
	
	final static String upload_path = SystemConfigUtil.getValue("upload_path");
	
	@Async
    public void sendEmail(TbEmail tbEmail, MultipartFile file) throws Exception {
	  	TbSendMess t = new TbSendMess(tbEmail.getTitle(),tbEmail.getContent());
	  	String path = "";
	  	tbSendMessMapper.insertSelective(t);
	  	if(file!=null) {
	  		path = FileUtil.save(file, SystemConfigUtil.getValue("qq_enclosure_path"));
	  	}
	  	StringBuffer check_err = new StringBuffer();
	  	String[] to = tbEmail.getTo().split(",");
	  	List<String> list = new ArrayList<String>(Arrays.asList(to));//将数组转换为list集合
	  	Iterator<String> it = list.iterator();
	  	while(it.hasNext()) {
	  		String s = it.next();
	  			String str = tbUserMapper.getUserIdByEmail(s);
	  			if(str==null) {
	  				TbUser tb = new TbUser(s);
	  				tbUserMapper.insertSelective(tb);
	  				str = tb.getTbId();
	  			}
	  			int auto_id = t.getTbId() == 0?null:t.getTbId();
	  			TbLetter letter = new TbLetter(tbEmail.getTbAdminId(),CreateSequence.getTimeMillisSequence(),Integer.parseInt(str),s,auto_id,"2");
	  			if (!"".equals(path)) {
	  				letter.setAppendixTitle(tbEmail.getAppendixTitle());
	  				letter.setAppendixPath(path);
				}
	  			tbLetterMapper.insertSelective(letter);
	  	}
	  	int size = list.size();
        if(size!=0) {
        	for (int i = 0; i < size; i++) {
	        	String qq = list.get(i);
	        	if(!"".equals(path)) {
	        		SendMail.send(qq, tbEmail.getTitle(), upload_path + path, tbEmail.getAppendixTitle(), tbEmail.getContent());
	        	}else {
	        		SendMail.send(qq, tbEmail.getTitle(), null, null, tbEmail.getContent());
	        	}
			}
        }
    }
	
	@Async
    public void sendEmailWithPath(TbEmail tbEmail, String path) throws Exception {
		TbSendMess t = new TbSendMess(tbEmail.getTitle(),tbEmail.getContent());
	  	tbSendMessMapper.insertSelective(t);
	  	StringBuffer check_err = new StringBuffer();
	  	String[] to = tbEmail.getTo().split(",");
	  	List<String> list = new ArrayList<String>(Arrays.asList(to));//将数组转换为list集合
	  	Iterator<String> it = list.iterator();
	  	while(it.hasNext()) {
	  		String s = it.next();
	  			String str = tbUserMapper.getUserIdByEmail(s);
	  			if(str==null) {
	  				TbUser tb = new TbUser(s);
	  				tbUserMapper.insertSelective(tb);
	  				str = tb.getTbId();
	  			}
	  			int auto_id = t.getTbId() == 0?null:t.getTbId();
	  			TbLetter letter = new TbLetter(tbEmail.getTbAdminId(),CreateSequence.getTimeMillisSequence(),Integer.parseInt(str),s,auto_id,"2");
	  			if (!"".equals(path)) {
	  				letter.setAppendixTitle(tbEmail.getAppendixTitle());
	  				letter.setAppendixPath(path);
				}
	  			tbLetterMapper.insertSelective(letter);
	  	}
        if(list.size()!=0) {
        	for (int i = 0; i < list.size(); i++) {
	        	String qq = list.get(i);
	        	if(!"".equals(path)) {
	        		String title =  "".equals(tbEmail.getAppendixTitle()) ? "enclosure" : tbEmail.getAppendixTitle();
	        		SendMail.send(qq, tbEmail.getTitle(), upload_path + path, title, tbEmail.getContent());
	        	}else {
	        		SendMail.send(qq, tbEmail.getTitle(), null, null, tbEmail.getContent());
	        	}
			}
        }
	}
	
	//发送短信
	@Async
    public void sendSM(TbShortPara shortPara) throws Exception {
	  	TbSendMess t = new TbSendMess(shortPara.getTitle(),shortPara.getContent());
	  	tbSendMessMapper.insertSelective(t);
	  	String[] to = shortPara.getTo().split(",");
	  	List<String> list = new ArrayList<String>(Arrays.asList(to));//将数组转换为list集合
	  	Iterator<String> it = list.iterator();
	  	while(it.hasNext()) {
	  		String s = it.next();
  			String str = tbUserMapper.getUserIdByPhone(s);
  			if(str==null) {
  				TbUser tb = new TbUser(s,1);
  				tbUserMapper.insertSelective(tb);
  				str = tb.getTbId();
  			}
  			int auto_id = t.getTbId() == 0?null:t.getTbId();
  			tbShortMessMapper.insertSelective(new TbShortMess(shortPara.getTbAdminId(),CreateSequence.getTimeMillisSequence(),Integer.parseInt(str),s,auto_id,"2"));
	  	}
	  	String content = shortPara.getContent();
	  	for (int i = 0; i < list.size(); i++) {
	  		AliyunSMSUtil.sendSMS(list.get(i), content);
		}
    }
	
	//定时任务插入邮箱数据
	@Async
    public void insertEmailTask(TaskInfo info,MultipartFile file) throws Exception {
		String path = "";
		if(file!=null) {
	  		path = FileUtil.save(file, SystemConfigUtil.getValue("qq_enclosure_path"));
	  	}
	  	TbSendMess t = new TbSendMess(info.getTitle(),info.getContent());
	  	tbSendMessMapper.insertSelective(t);
	  	EmailTask task = new EmailTask(info.getTo(),String.valueOf(t.getTbId()),info.getAppendixTitle(),path);
	  	tbEmailTaskMapper.insertSelective(task);
	  	TbEmailTimingTaskMapper.insertSelective(new TbEmailTimingTask(info.getTbAdminId(),String.valueOf(task.getTbId()) , info.getJobName(), info.getJobGroup(), info.getJobDescription(), info.getCronExpression()));
	}
	
	//定时任务插入短信数据
	@Async
    public void insertLetterTask(TaskInfo info) throws Exception {
	  	TbSendMess t = new TbSendMess(info.getTitle(),info.getContent());
	  	tbSendMessMapper.insertSelective(t);
//	  	String target, String tbSendMessId, String appendixTitle, String appendixPath
	  	LetterTask task = new LetterTask(info.getTo(),String.valueOf(t.getTbId()));
	  	tbLetterTaskMapper.insertSelective(task);
	  	tbLetterTimingTaskMapper.insertSelective(new TbLetterTimingTask(info.getTbAdminId(),String.valueOf(task.getTbId()) , info.getJobName(), info.getJobGroup(), info.getJobDescription(), info.getCronExpression()));
	}
	
	//定时任务修改时间
	@Async
    public void updateEmailTask(TaskInfo info) throws Exception {
		TbEmailTimingTaskMapper.updateTaskByjobGroup(info);
	}
	
	//定时任务修改时间
	@Async
    public void updateLetterTask(TaskInfo info) throws Exception {
		tbLetterTimingTaskMapper.updateTaskByjobGroup(info);
	}
	
	
	//把数据库的任务启动之后放到队列里面去
	@Async
    public void putTimingTaskToTask() throws Exception {
		List<TaskData> list = TbEmailTimingTaskMapper.getAllTask();
		if(list!=null) {
			int size = list.size();
			for (int i = 0; i < size; i++) {
				taskServiceImpl.simpleAddJob(list.get(i));
			}
		}
	}
	
	public static void main(String[] args) {
		String[] to = new String[] {"280888608@qq.com","ajdhadsad","787822"};
	  	for (int i = 0; i < to.length; i++) {
			String string = to[i];
			if(!FormatCheck.isMobilePhone(string)) {
				to[i] = null;
			}
		}
	  	System.out.println(to.length);
	}
	
}
