package com.hc.utils.voiceToText;

import java.io.File;

import org.json.JSONObject;

import com.baidu.aip.speech.AipSpeech;

public class VoiceRecognition {
	 // 设置APPID/AK/SK
    // 百度AI开发平台的控制台中创建一个语音应用即可获得
    public static final String APP_ID = "18002166";
    public static final String API_KEY = "SAxoHSjPQv23YQcQ61efzVMz";
    public static final String SECRET_KEY = "U2stEGGGsLcM5DrVGg2U1ixckabn0cQw";
 
    private static final AipSpeech aipSpeech = getAipSpeech();
 
    private static String resultText;
 
    public static String getResultText() {
        return resultText;
    }
 
    public static void main(String[] args){
        VoiceRecognition voiceRecognition = new VoiceRecognition();
        if(voiceRecognition.recognizeVoice()){
            System.out.println("结果为：" + voiceRecognition.getResultText());
        }else{
            System.out.println("识别错误");
        }
    }
 
    public static AipSpeech getAipSpeech(){
        // 初始化一个AipSpeech
        AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);
 
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
 
    public boolean recognizeVoice()
    {
        String wavFileName = "record";
        // 对本地语音文件进行识别
//        String path = new File("").getAbsolutePath() + "/" + wavFileName + ".wav";
        String path = "D:/百度语音识别/ffmpeg-20191211-4110029-win64-static/bin/16k.pcm";
        JSONObject asrRes = aipSpeech.asr(path, "wav", 16000, null);
        System.out.println(asrRes);
 
        if(asrRes.getString("err_msg").equals("success.")){
            resultText = asrRes.getJSONArray("result").getString(0);
            return true;
        }else{
            return false;
        }
        // 对语音二进制数据进行识别
        /*byte[] data = new byte[0];     //readFileByBytes仅为获取二进制数据示例
        try {
            data = Util.readFileByBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject asrRes2 = client.asr(data, "pcm", 16000, null);
        System.out.println(asrRes2);*/
    }
    
    
}
