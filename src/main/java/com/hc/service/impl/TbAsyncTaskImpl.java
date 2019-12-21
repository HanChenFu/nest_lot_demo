package com.hc.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.hc.common.redis.RedisUtil;
import com.hc.mapper.askRecord.TbAskRecordMapper;
import com.hc.mapper.letter.TbLetterMapper;
import com.hc.mapper.sendMess.TbSendMessMapper;
import com.hc.mapper.user.TbUserMapper;
import com.hc.pojo.email.TbEmail;
import com.hc.pojo.letter.TbLetter;
import com.hc.pojo.sendMess.TbSendMess;
import com.hc.pojo.user.TbUser;
import com.hc.utils.documentSequence.CreateSequence;
import com.hc.utils.redis.LoginUserUtil;
import com.hc.utils.sendCode.AliyunSMSUtil;
import com.hc.utils.sm.SmsSDKDemo;
import com.hc.utils.string.EmailCheck;

@Service("tbAsyncTaskImpl")
public class TbAsyncTaskImpl {

	@Autowired
    private JavaMailSender mailSender;
	
	@Autowired
	RedisUtil redis;
	
	@Autowired
	TbAskRecordMapper tbAskRecordMapper;
	
	@Autowired
	LoginUserUtil loginUserUtil;
	
	@Autowired
	TbUserMapper tbUserMapper;
	
	@Autowired
	TbLetterMapper tbLetterMapper;
	
	@Autowired
	TbSendMessMapper tbSendMessMapper;
	
	@Value("${spring.mail.username}")
    private String from;
	
	@Async
    public void sendEmail(TbEmail tbEmail) throws Exception {
	  	SimpleMailMessage message = new SimpleMailMessage();
	  	TbSendMess t = new TbSendMess(tbEmail.getTitle(),tbEmail.getContent());
	  	tbSendMessMapper.insertSelective(t);
	  	StringBuffer check_err = new StringBuffer();
	  	String[] to = tbEmail.getTo();
	  	List<String> list = new ArrayList<String>(Arrays.asList(to));//将数组转换为list集合
	  	Iterator<String> it = list.iterator();
	  	while(it.hasNext()) {
	  		String s = it.next();
	  		if (!EmailCheck.isEmail(s)) {
	  			it.remove();
	  			check_err.append(s+",");
	  		}else {
	  			String str = tbUserMapper.getUserIdByEmail(s);
	  			if(str==null) {
	  				TbUser tb = new TbUser(s);
	  				tbUserMapper.insertSelective(tb);
	  				str = tb.getTbId();
	  			}
	  			int auto_id = t.getTbId() == 0?null:t.getTbId();
	  			tbLetterMapper.insertSelective(new TbLetter(tbEmail.getTbAdminId(),CreateSequence.getTimeMillisSequence(),Integer.parseInt(str),auto_id,1,"2"));
	  		}
	  	}
	  	String content = tbEmail.getContent();
        message.setFrom(from); //腾讯的限制，发送人必须与发送邮箱相同，不同会报异常
        if(list.size()!=0) {
        	String[] strings = new String[list.size()];
        	list.toArray(strings);
        	message.setTo(strings);//这边是把不合格的邮箱去掉之后，再换成string
            message.setSubject(tbEmail.getTitle());
            message.setText(content);
            String[] ccList = new String[]{};//这里添加抄送人名称列表
            message.setCc(ccList);
            String[] bccList = new String[]{};//这里添加密送人名称列表
            message.setBcc(bccList);
            mailSender.send(message);
        }
    }
	
	//发送短信
	@Async
    public void sendSM(TbEmail tbEmail) throws Exception {
	  	TbSendMess t = new TbSendMess(tbEmail.getTitle(),tbEmail.getContent());
	  	tbSendMessMapper.insertSelective(t);
	  	String[] to = tbEmail.getTo();
	  	List<String> list = new ArrayList<String>(Arrays.asList(to));//将数组转换为list集合
	  	Iterator<String> it = list.iterator();
	  	while(it.hasNext()) {
	  		String s = it.next();
	  		if (!EmailCheck.isMobilePhone(s)) {
	  			it.remove();
	  		}else {
	  			String str = tbUserMapper.getUserIdByPhone(s);
	  			if(str==null) {
	  				TbUser tb = new TbUser(s);
	  				tbUserMapper.insertSelective(tb);
	  				str = tb.getTbId();
	  			}
	  			int auto_id = t.getTbId() == 0?null:t.getTbId();
	  			tbLetterMapper.insertSelective(new TbLetter(tbEmail.getTbAdminId(),CreateSequence.getTimeMillisSequence(),Integer.parseInt(str),auto_id,2,"2"));
	  		}
	  	}
	  	String content = tbEmail.getContent();
	  	for (int i = 0; i < list.size(); i++) {
	  		AliyunSMSUtil.sendSMS(list.get(i), content);
		}
    }
	
}
