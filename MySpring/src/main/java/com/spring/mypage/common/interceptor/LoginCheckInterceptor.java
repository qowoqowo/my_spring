package com.spring.mypage.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//���ͼ��� ������ ���� HandlerInterceptorAdapter �� ��� �ޱ�
public class LoginCheckInterceptor extends HandlerInterceptorAdapter {

	// ��Ʈ�ѷ��� �޼ҵ� ���� ���� ����� �޼ҵ�
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// �α��� ������ üũ (���� ��ü)
		// HttpSession �� �ƴ϶�, HttpServletRequest ��ü�κ��� ���ǰ�ü�� ���ٰ���
		HttpSession session = request.getSession();
		
		// �α����� �ȵǾ� �ִٸ� �α��� ȭ������ ��������
		if(session.getAttribute("login") == null) {
			// �������� �ϴ� URI �ּ� �Է�(/loginView)
			response.sendRedirect(request.getContextPath() + "/loginView");
			return false;  // false�� ��Ʈ�ѷ��� /memEditView �޼ҵ�� ������ ����
		}
		
		// �α��� ���� ����
		return true;  // ��Ʈ�ѷ��� /memEditView �޼ҵ�� ��
	}
	
	
	// ��Ʈ�ѷ��� �޼ҵ� ���� �Ŀ� ������ �޼ҵ� (���� ���� ����)
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}	
	
	
	
}
