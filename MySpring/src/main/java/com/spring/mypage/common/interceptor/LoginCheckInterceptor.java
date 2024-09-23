package com.spring.mypage.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//인터셉터 구현을 위해 HandlerInterceptorAdapter 를 상속 받기
public class LoginCheckInterceptor extends HandlerInterceptorAdapter {

	// 컨트롤러의 메소드 실행 전에 실행될 메소드
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// 로그인 중인지 체크 (세션 객체)
		// HttpSession 뿐 아니라, HttpServletRequest 객체로부터 세션객체에 접근가능
		HttpSession session = request.getSession();
		
		// 로그인이 안되어 있다면 로그인 화면으로 보내버림
		if(session.getAttribute("login") == null) {
			// 보내고자 하는 URI 주소 입력(/loginView)
			response.sendRedirect(request.getContextPath() + "/loginView");
			return false;  // false면 컨트롤러의 /memEditView 메소드로 진입을 막음
		}
		
		// 로그인 중인 상태
		return true;  // 컨트롤러의 /memEditView 메소드로 감
	}
	
	
	// 컨트롤러의 메소드 실행 후에 실행할 메소드 (현재 내용 없음)
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}	
	
	
	
}
