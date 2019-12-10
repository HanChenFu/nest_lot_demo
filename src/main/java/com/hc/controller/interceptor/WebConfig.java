package com.hc.controller.interceptor;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import com.hc.common.jackjson.fastJsonConfig;

@Configuration
public class WebConfig extends fastJsonConfig {
	@Resource
	private LoginWebInterceptor webInterceptor;
	// web 不拦截路径
	private static List<String> webNoUrl = new ArrayList<String>();
	
	static {
		// web 端
		webNoUrl.add("/web/user/login");// 登录，不需要拦截
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 自定义拦截器，添加拦截路径和排除拦截路径
		// web 拦截器
//		System.err.println(" ------------------加载拦截器 ");
//		registry.addInterceptor(webInterceptor)// 添加拦截器
//				.addPathPatterns("/web/**")// 添加拦截路径
//				.excludePathPatterns(webNoUrl);// 添加不拦截路径
	}
}
