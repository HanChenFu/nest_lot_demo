package com.hc.utils.sm;

import org.springframework.beans.factory.annotation.Autowired;

import com.hc.common.code.StatusCode;
import com.hc.common.exception.CustomException;
import com.hc.common.redis.RedisUtil;

public class SMSCodeUtil {

	/**
	 * 保存短信的Key
	 */
	private final String KEY_SMS_CODE = "my_sms_code_";
	/**
	 * 保存短信的时间
	 */
	private final int KEY_SMS_CODE_TIME = 600;

	@Autowired
	private RedisUtil redis;

	/**
	 * 发送短信验证码
	 * 
	 * @param phone
	 * @return
	 * @throws CustomException
	 */
	public boolean sendSmsCode(String phone) throws Exception, CustomException {
		// 验证手机号
		AliyunSMSUtil.checkPhone(phone);
		SMSCode code = new SMSCode(phone);
		boolean res = AliyunSMSUtil.sendMessToUser(phone, code.getCode());
		if (res) {
			setSendSms(code);
			return true;
		}
		return false;
	}

    /**
     * 发送短信通知
     *
     * @param phone
     * @return
     * @throws CustomException
     */
    public boolean sendSmsNotice(String phone, String code)  throws Exception, CustomException {
        // 验证手机号
        AliyunSMSUtil.checkPhone(phone);
        SMSCode smsCode = new SMSCode(phone);
        boolean res = AliyunSMSUtil.sendSMS(phone, code);
        if (res) {
            setSendSms(smsCode);
            return true;
        }
        return false;
    }

	/**
	 * 验证手机验证码
	 * 
	 * @param phone
	 * @param code
	 * @return
	 * @throws CustomException
	 */
	public boolean checkSmsCode(String phone, String code) throws CustomException {
		SMSCode smsCode = getSMSCode(phone);
		if (null == smsCode) {
			throw new CustomException(StatusCode.AUTHENTICATION, "验证码已过期");
		}
		// 验证
		return smsCode.checkCode(phone, code);
	}

	/**
	 * 保存短信信息
	 * 
	 * @author DDM 2018年6月1日
	 */
	private void setSendSms(SMSCode code) {
		redis.set(KEY_SMS_CODE + code.getPhone(), code, KEY_SMS_CODE_TIME);
	}

	/**
	 * 获取短信信息
	 * 
	 * @author DDM 2018年6月1日
	 */
	private SMSCode getSMSCode(String phone) {
		Object obj = redis.get(KEY_SMS_CODE + phone);
		if (null != obj && obj instanceof SMSCode) {
			return (SMSCode) obj;
		} else {
			return null;
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		new SMSCodeUtil().sendSmsNotice("18122711575", "1234");
	}


}
