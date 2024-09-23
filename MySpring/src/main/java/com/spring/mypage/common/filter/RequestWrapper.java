package com.spring.mypage.common.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.spring.mypage.common.util.XSSDestroy;

public class RequestWrapper extends HttpServletRequestWrapper{

	// 생성자로 들어온 Request 객체의 내부 파라미터 값들에 대해 XSS 파괴 
	public RequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String[] getParameterValues(String name) {
		
		// 필터링을 거치지 않은 요청 파라미터 값들
		String[] beforeParameters = super.getParameterValues(name);
		
		// 요청 파라미터가 없으면 (= null) 그대로 null 리턴
		if(beforeParameters == null) {
			return null;
		}
		
		// 요청 파라미터가 있다면 전부 필터링을 적용한 후 리턴
		for(int i = 0; i < beforeParameters.length; i++) {
			beforeParameters[i] = XSSDestroy.destroy(beforeParameters[i]);
			System.out.println(beforeParameters[i]);
		}
		
		return beforeParameters;
	}
	
	@Override
	public String getParameter(String name) {
		
		String beforeParam = super.getParameter(name);
		
		if(beforeParam == null) {
			return null;
		}
		System.out.println(beforeParam);
		return XSSDestroy.destroy(beforeParam);
	}
	
	@Override
	public String getHeader(String name) {
		String beforeHeader = super.getHeader(name);
		
		if(beforeHeader == null) {
			return null;
		}
		System.out.println(beforeHeader);
		return XSSDestroy.destroy(beforeHeader);
	}
	
}
