package com.spring.mypage.common.exception;

public class BizNotFoundException extends BizException {

	public BizNotFoundException(String message, String errCode) {
		super(message, errCode);
	}
	
}
