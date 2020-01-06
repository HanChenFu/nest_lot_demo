package com.hc.controller.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
import com.hc.utils.conig.SystemConfigUtil;
 
/**
 * 静态资源映射
 */
@Configuration
public class MyWebMvcConfig extends WebMvcConfigurationSupport {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler("/upload/**")
    			.addResourceLocations("file:"+SystemConfigUtil.getValue("upload_path"));
    	
        registry.addResourceHandler("/**")
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
}

