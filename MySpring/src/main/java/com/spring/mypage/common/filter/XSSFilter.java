package com.spring.mypage.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class XSSFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// Request 객체를 우리가 커스텀한 RequestWrapper를 거치도록 함
		chain.doFilter(new RequestWrapper((HttpServletRequest) request), response);
	}

	@Override
	public void destroy() {
		
	}
	
}
