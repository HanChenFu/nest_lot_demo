package com.hc.common.exception;

import com.hc.common.code.StatusCode;

@SuppressWarnings("serial")
public class CustomException extends Exception {
	private StatusCode statusCode;
	private String message;

	public CustomException(StatusCode statusCode, String message) {
		this.message = message;
		this.statusCode = statusCode;
	}

	public CustomException(String message) {
		this.message = message;
		this.statusCode = StatusCode.ERROR;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public StatusCode getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(StatusCode statusCode) {
		this.statusCode = statusCode;
	}

}
