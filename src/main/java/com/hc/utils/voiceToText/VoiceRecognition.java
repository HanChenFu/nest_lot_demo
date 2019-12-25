package com.hc.utils.voiceToText;

import org.json.JSONException;
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
        return client;
    }
 
    /**
     * 
     * @param path
     * @return
     * @throws JSONException 
     */
    public final static String recognizeVoice(String path) throws JSONException
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
