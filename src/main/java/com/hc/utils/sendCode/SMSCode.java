package com.hc.utils.sendCode;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.hc.common.code.StatusCode;
import com.hc.common.exception.CustomException;

public class SMSCode {
	private Integer id;
	// private String account;
	private String phone;
	private String code;
	private Date createtime;

	public SMSCode() {
		createtime = new Date();
	}

	public SMSCode(String phone) {
		this.phone = phone;
		// 生成随机数
		Integer codeNum = (int) ((Math.random() * 9 + 1) * 100000);
		this.code = codeNum.toString();
		createtime = new Date();
	}

	public SMSCode(Integer id, String phone) {
		this.id = id;
		// this.account = account;
		this.phone = phone;
		// 生成随机数
		Integer codeNum = (int) ((Math.random() * 9 + 1) * 100000);
		this.code = codeNum.toString();
		createtime = new Date();
	}

	// 日期计算类
	private static GregorianCalendar gc = new GregorianCalendar();

	/**
	 * 验证 验证码不正确
	 * 
	 * @param phone
	 * @param code
	 * @return
	 * @throws CustomException
	 */
	public boolean checkCode(String phone, String code) throws CustomException {

		if (!this.phone.equals(phone)) {
			throw new CustomException(StatusCode.PARAM_ERROR, "账号不正确，请重新获取！");
		}

		gc.setTime(createtime);
		gc.add(Calendar.MINUTE, 10);

		if (new Date().getTime() > gc.getTime().getTime()) {
			throw new CustomException(StatusCode.AUTHENTICATION, "验证码已过期！");
		}

		if (!this.code.equals(code)) {
			throw new CustomException(StatusCode.AUTHENTICATION, "验证码不正确！");
		}
		// 通过
		return true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}
