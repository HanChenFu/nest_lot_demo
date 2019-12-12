package com.hc.pojo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
@PropertySource(value = "systemConfig-dev.properties")
public class ConfigPara {
	
	private String ffmpeg_bin_path;//这边的这个是ffmpeg语音转换的安装路径

	public String getFfmpeg_bin_path() {
		return ffmpeg_bin_path;
	}

	public void setFfmpeg_bin_path(String ffmpeg_bin_path) {
		this.ffmpeg_bin_path = ffmpeg_bin_path;
	}
	
}
