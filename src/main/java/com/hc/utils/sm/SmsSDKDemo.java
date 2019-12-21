package com.hc.utils.sm;

public final class SmsSDKDemo {
	
	public static void sendSM(String phone,String title,String content) {

    	try {
    		//请根据实际 accesskey 和 secretkey 进行开发，以下只作为演示 sdk 使用
    		String accesskey = "5dfc99e8efb9a31ec69b945c";
    		String secretkey ="d2cd7f5986df4fb589f0f0b0ad4b8e6a";
    		//手机号码
    		 //初始化单发
	    	SmsSingleSender singleSender = new SmsSingleSender(accesskey, secretkey);
	    	SmsSingleSenderResult singleSenderResult;
	    	 //普通单发,注意前面必须为【】符号包含，置于头或者尾部。
	    	singleSenderResult = singleSender.send(0, "86", phone, "【汉臣府】主题:"+title+","+content +"", "", "");
	    	System.out.println(singleSenderResult);
	    	//语音验证码发送
    		//SmsVoiceVerifyCodeSender smsVoiceVerifyCodeSender = new SmsVoiceVerifyCodeSender(accesskey,secretkey);
    		//SmsVoiceVerifyCodeSenderResult smsVoiceVerifyCodeSenderResult = smsVoiceVerifyCodeSender.send("86",phoneNumber, "444144",2,"");
    		//System.out.println(smsVoiceVerifyCodeSenderResult);
    	} catch (Exception e) {
			e.printStackTrace();
		}
    
	}
	
	
	public static void main(String[] args) {
		SmsSDKDemo.sendSM("18122711575", "asdsadasd", "asjdhsad");
		/*
		 * try { //请根据实际 accesskey 和 secretkey 进行开发，以下只作为演示 sdk 使用 String accesskey =
		 * "5dfc99e8efb9a31ec69b945c"; String secretkey
		 * ="d2cd7f5986df4fb589f0f0b0ad4b8e6a"; //手机号码 String phoneNumber =
		 * "18122711575"; //初始化单发 SmsSingleSender singleSender = new
		 * SmsSingleSender(accesskey, secretkey); SmsSingleSenderResult
		 * singleSenderResult; //普通单发,注意前面必须为【】符号包含，置于头或者尾部。 singleSenderResult =
		 * singleSender.send(0, "86", phoneNumber,
		 * "【Kewail科技】尊敬的用户：您的验证码：123456，工作人员不会索取，请勿泄漏。", "", "");
		 * System.out.println(singleSenderResult); //语音验证码发送 //SmsVoiceVerifyCodeSender
		 * smsVoiceVerifyCodeSender = new SmsVoiceVerifyCodeSender(accesskey,secretkey);
		 * //SmsVoiceVerifyCodeSenderResult smsVoiceVerifyCodeSenderResult =
		 * smsVoiceVerifyCodeSender.send("86",phoneNumber, "444144",2,"");
		 * //System.out.println(smsVoiceVerifyCodeSenderResult); } catch (Exception e) {
		 * e.printStackTrace(); }
		 */}
}
