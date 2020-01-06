package com.hc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.hc.service.impl.TbAsyncTaskImpl;

@EnableAsync
@MapperScan("com.hc.mapper")
@EnableScheduling
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class MainApplication extends SpringBootServletInitializer{
    public static ConfigurableApplicationContext applicationContext;
	
	public static void main(String[] args) throws Exception {
		applicationContext = SpringApplication.run(MainApplication.class, args);
		TbAsyncTaskImpl tbAsyncTaskImpl = (TbAsyncTaskImpl)applicationContext.getBean("tbAsyncTaskImpl");
		tbAsyncTaskImpl.putTimingTaskToTask();
	}

	@Override //为了打包springboot项目
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(this.getClass());
	}

}
