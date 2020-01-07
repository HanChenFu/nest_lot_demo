package com.hc.common.timing;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hc.common.timing.bean.Crawler;
import com.hc.common.tools.Tools;
import com.hc.controller.callCenter.HttpClientRequest;
import com.hc.utils.conig.SystemConfigUtil;

@Component
public class QuartzService {
	// 每六小时启动
	@Scheduled(cron = "0 * */6 * * ?")
	public void timerToNow() {
		System.out.println("now time:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date()));
		try {
			System.out.println("~~~~开始爬取台风天气数据~~~~~");
			Crawler.getTyphoonWarning();
			Crawler.getWeatherForecastDetailsCrawler();
			System.out.println("~~~~结束爬取台风天气数据~~~~~");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("更新缓存爬虫：天气/台风失败！");
		}
		System.out.println("now time:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date()));
	}
	
	
	//每天上午10：15执行一次： 0 15 10 ? * * 或 0 15 10 * * ? 或 0 15 10 * * ? *
	//每天中午十二点执行一次：0 0 12 * * ?
	// 每天12点启动
	@Scheduled(cron = "0 0 12 * * ?")
	public void startReptile() throws IOException {
		String url = "http://localhost:8080//index/news/insertReptileData";
		HttpClientRequest httpClientRequest = new HttpClientRequest();
		String str = httpClientRequest.requestVoiceServer(url); 
		System.out.println(str+"====首页的爬虫触发成功！");
		
		String path = SystemConfigUtil.getValue("upload_path")+"paichong.txt";
		File file = new File(path);
		if(!file.exists()){
          file.getParentFile().mkdirs();          
		}
		file.createNewFile();

		// write
		FileWriter fw = new FileWriter(file, true);
		BufferedWriter bw = new BufferedWriter(fw);
//		String tt = "哈喽，哈喽，  你好你好\r\n";
		bw.write(str+"====首页的爬虫触发成功！"+Tools.getAPIresponseDateTime());
		bw.flush();
		bw.close();
		fw.close();
	}
}
