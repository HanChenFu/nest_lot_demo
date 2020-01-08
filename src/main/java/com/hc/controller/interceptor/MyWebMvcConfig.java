package com.hc.controller.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import com.hc.utils.conig.SystemConfigUtil;
 
/**
 * 静态资源映射
 */
@Configuration
public class MyWebMvcConfig extends WebMvcConfigurationSupport {
	@Resource
	private LoginWebInterceptor webInterceptor;
	// web 不拦截路径
	private static List<String> webNoUrl = new ArrayList<String>();
	
	static {
		// web 端
		webNoUrl.add("/web/admin/login");// 登录，不需要拦截
		webNoUrl.add("/upload/**");
		webNoUrl.add("/static/**");
	}
	
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler("/upload/**")
    			.addResourceLocations("file:"+SystemConfigUtil.getValue("upload_path"));
    	
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }
    
    /**
     * WebSocke
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
//    	System.out.println("test.................");
        return new ServerEndpointExporter();
    }
    
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(webInterceptor).addPathPatterns("/**").excludePathPatterns(webNoUrl);
	}
    
}

