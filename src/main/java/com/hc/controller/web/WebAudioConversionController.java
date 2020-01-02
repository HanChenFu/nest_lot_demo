package com.hc.controller.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hc.common.code.StatusCode;
import com.hc.common.exception.CustomException;
import com.hc.common.result.ResultData;
import com.hc.utils.conig.SystemConfigUtil;
import com.hc.utils.ffmpeg.FfmpeGUtil;
import com.hc.utils.file.FileUtil;
import com.hc.utils.redis.LoginUserUtil;
import com.hc.utils.result.ResultUtil;
import com.hc.utils.voiceToText.VoiceRecognition;

@Controller
@RequestMapping("/web/audio")
@ResponseBody
public class WebAudioConversionController {
	
    @Autowired
    LoginUserUtil loginUserUtil;
    
    /**
     * 	上传音频并转换为文字
     * @author DDM 2018年4月16日
     */
    @RequestMapping("audioToText")
    public ResultData<String> audioToText(MultipartFile files, Integer type, HttpServletRequest request) throws CustomException, Exception {
//    	TbAdmin tbUser = loginUserUtil.getLoginUser(request.getHeader("token"));// 获取请求头，得到token
        if (null == files) {
            throw new CustomException(StatusCode.PARAM_NULL, "未选择音频！");
        }
        // 文件原始名称
        String originalFilename = files.getOriginalFilename();
        if (-1 == originalFilename.lastIndexOf(".")
                || originalFilename.length() == originalFilename.lastIndexOf(".") + 1) {
            throw new CustomException(StatusCode.PARAM_ERROR, "只支持wav,amr,m4a格式的音频！");
        }
        if (FileUtil.checkAudioFormat(files)) {
            throw new CustomException(StatusCode.PARAM_ERROR, "只支持wav,amr,m4a格式的音频！");
        }
        String path = "";
        try {
        	//这边先是上传，然后再是进行格式的转换
        	path = FileUtil.save(files, SystemConfigUtil.getValue("audio"));
        	//对音频进行转换
        	String p = SystemConfigUtil.getValue("upload_path")+"/"+path;
        	String pcm_path = p.split("\\.")[0]+".pcm";
        	FfmpeGUtil.changeAudioToPcm(p, pcm_path);
        	return ResultUtil.getResultData(VoiceRecognition.recognizeVoice(pcm_path));
        } catch (Exception e) {
            throw e;
        }
    }

}
