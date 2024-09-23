package com.spring.mypage.common.exception;

public class BizException extends Exception {

	private String errCode;

	public BizException(String message) {
		super(message);
	}

	public BizException(String message ,String errCode) {
		super(message);
		this.errCode = errCode;
	}

	public String getErrCode() {
		return errCode;
	}
	
	
	
}
