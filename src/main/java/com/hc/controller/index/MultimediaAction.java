package com.hc.controller.index;


import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hc.utils.aliyunOss.AliyunBean;
import com.hc.utils.aliyunOss.StsServiceSample;


@Controller
@RequestMapping("/index/ceshi")
@ResponseBody
public class MultimediaAction {
	/** 阿里云获取参数 */
	@RequestMapping("/getAliyun")
	public AliyunBean getAliyun() {
		return StsServiceSample.getAliyun();
	}
	/** 阿里云上传图片 */
	@RequestMapping("/uploadImg")
	public String upload(@RequestParam(value = "file", required = false) MultipartFile file)throws IOException {
		return StsServiceSample.uploadImg2Oss(file, "nieliyun/");
	}
	/** 阿里云上传视频 */
	@RequestMapping("/uploadVideo")
	public String uploadVideo(@RequestParam(value = "file", required = false) MultipartFile file)throws IOException {
		return StsServiceSample.uploadVideo2Oss(file, "nieliyun/");
	}
	
}
