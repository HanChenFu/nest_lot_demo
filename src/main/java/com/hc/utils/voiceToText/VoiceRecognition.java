package com.hc.utils.voiceToText;

import org.json.JSONObject;

import com.baidu.aip.speech.AipSpeech;
import com.hc.utils.conig.SystemConfigUtil;

public final class VoiceRecognition {
	 // 设置APPID/AK/SK
    // 百度AI开发平台的控制台中创建一个语音应用即可获得
    private static final AipSpeech aipSpeech = getAipSpeech();
 
    private static String resultText;
 
    public static String getResultText() {
        return resultText;
    }
    
    public static void main(String[] args){
//        VoiceRecognition voiceRecognition = new VoiceRecognition();
//        if(voiceRecognition.recognizeVoice("")){
//            System.out.println("结果为：" + voiceRecognition.getResultText());
//        }else{
//            System.out.println("识别错误");
//        }
    	System.out.println();
    }
    
//    		 = 18002166
//    		 = 
//    		 = U2stEGGGsLcM5DrVGg2U1ixckabn0cQw
 
    public static AipSpeech getAipSpeech(){
        // 初始化一个AipSpeech
        AipSpeech client = new AipSpeech(SystemConfigUtil.getValue("APP_ID"),SystemConfigUtil.getValue("API_KEY"),SystemConfigUtil.getValue("SECRET_KEY"));
        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
        //client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
        //client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理
        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        // System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");
        return client;
    }
 
    /**
     * 
     * @param path
     * @return
     */
    public final static String recognizeVoice(String path)
    {
        JSONObject asrRes = aipSpeech.asr(path, "pcm", 16000, null);
        if(asrRes.getString("err_msg").equals("success.")){
            resultText = asrRes.getJSONArray("result").getString(0);
            return resultText;
        }else{
            return "识别错误";
        }
    }
    
    
}
