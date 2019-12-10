package com.hc.common.result;

import com.hc.common.code.StatusCode;

public class ResultBase {
	private Boolean success = true; // 成功与否
	private StatusCode code; // 状态码
	private String message; // 消息

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	/**
	 * 获取 Integer 形式的 Code
	 * 
	 * @author DDM 2018年1月18日
	 */
	public Integer getCode() {
		return code.toInteger();
	}

	public void setCode(Object code) {
		if (code instanceof StatusCode) {
			this.code = (StatusCode) code;
		} else {
			int i = (Integer) code;
			for (StatusCode codeV : StatusCode.values()) {
				if (codeV.toInteger().intValue() == i) {
					this.code = codeV;
					return;
				}
			}
		}

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
