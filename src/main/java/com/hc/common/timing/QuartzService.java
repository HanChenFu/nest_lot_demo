package com.hc.common.timing;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hc.common.timing.bean.Crawler;

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
}
