package com.spring.mypage.member.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.mypage.board.service.BoardService;
import com.spring.mypage.common.exception.BizNotFoundException;
import com.spring.mypage.member.dto.MemberDTO;
import com.spring.mypage.member.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	// ȸ������ ȭ��
	@RequestMapping("/registView")
	public String registView() {
		
		return "member/registView";
	}
	
	// ȸ�� ���� ��û
	@PostMapping("/registDo")
	public String registDo(@Valid MemberDTO member, BindingResult result ,HttpServletRequest request, RedirectAttributes attr) {
		
		System.out.println(result.hasErrors());
		
		if(result.hasErrors()) {
			for(ObjectError error : result.getAllErrors()) {
				System.out.println(error.getDefaultMessage());
			}
			
			return "redirect:/registView";
		}
		
		
		member.setMemId(request.getParameter("id"));
		
		// ���� �� Pw ��ȣȭ
		String encodePw = passwordEncoder.encode(request.getParameter("pw"));
		
		member.setMemPassword(encodePw);
		member.setMemName(request.getParameter("name"));
		member.setMemPhone(request.getParameter("phone"));
		member.setMemEmail(request.getParameter("email"));
		
		try {
			memberService.registMember(member);
		} catch (Exception e) {
			e.printStackTrace();
			
			attr.addFlashAttribute("failMsg", "�ߺ��� ���̵� �Դϴ�.");
			
			return "redirect:/registView";
		}		
		
		
		
		return "redirect:/loginView/";
		
		
	}
	
	// �α��� ȭ��
	@RequestMapping("/loginView")
	public String loginView(HttpServletRequest request, Model model) {
		
		String from = request.getHeader("Referer");
		
		 try {
			from = URLDecoder.decode(from, "UTF-8");
			from = from.replace("&#61;", "=");
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
         
        
		
		System.out.println(from + "���� /loginView ��û");
		
		model.addAttribute("keyFrom",from);
		
		return "member/loginView";
		
	}
	
	// �α��� ��û
	@PostMapping("/loginDo")
	public String loginDo(MemberDTO member, HttpSession session, boolean rememberId, HttpServletResponse respons, RedirectAttributes attr, String from) {
		
		System.out.println(member);

		MemberDTO login = memberService.loginDo(member);
		
		// �α��� ���� ���� �Ǻ�
				if(login != null) {
					
					// passwordEncoder �� �̿��Ͽ�
					// ����ڰ� �Է��� ��й�ȣ�� DB�� ��ȣȭ�� ��й�ȣ�� ��
					
					// ��ġ�ϸ� true, Ʋ���� false ����
					boolean isMatch = passwordEncoder.matches(member.getMemPassword(), login.getMemPassword());
					
					if(isMatch == false) {
						attr.addFlashAttribute("failMsg", "���̵� Ȥ�� ��й�ȣ�� �ùٸ��� �ʽ��ϴ�.");
						return "redirect:/loginView";
					}					
					
					System.out.println(login);
					
					// �α��� ������ ���� ��ü�� �α��� ���� ����
					// Ű��: login, ���� value��: MemberDTO ��ü
					session.setAttribute("login", login);
					
					
					// ���̵� ����ϱⰡ üũ�Ǿ� ������
					// ���̵� ������ Ŭ���̾�Ʈ(������)�� ��Ű�� ���� ����
					if(rememberId) {
						// ���̵� ����ϱ� üũ
						
						// ��Ű ����
						// Ű��: rememberId
						// value��: �α����� ����� ���̵�
						Cookie cookie = new Cookie("rememberId", login.getMemId());
						
						// ������ ��Ű�� ����(response) ��ü�� ����ش�.
						respons.addCookie(cookie);
					}else {
						// ���̵� ����ϱ� üũ����
						// rememberId Ű������ ���� ���� ��Ű�� �������ֱ�
						
						// ��Ű ���� (������ Ű���� ���� ��Ű ����)
						Cookie cookie = new Cookie("rememberId", "");
						
						// ��Ű ��ȿ���� 0���� ������ֱ� (Ŭ���̾�Ʈ ���޽� �ٷ� ������)
						cookie.setMaxAge(0);
						
						// ���� ��ü�� ��Ű�� ����
						respons.addCookie(cookie);
						
						
					}
					
				}else {
					// �α��� ����
					// �α��� ���� ������ �Բ� �α��� �������� ����
					// model�� failMsg Ű ������ "���̵� Ȥ�� ��й�ȣ�� �ùٸ��� �ʽ��ϴ�." ���� ����
					// loginView.jsp���� failMsg Ű���� ���� ���� ���� �� �� �ִ�
//					model.addAttribute("failMsg", "���̵� Ȥ�� ��й�ȣ�� �ùٸ��� �ʽ��ϴ�.");
					
					attr.addFlashAttribute("failMsg", "���̵� Ȥ�� ��й�ȣ�� �ùٸ��� �ʽ��ϴ�.");
					
					// redirect:/loginView �� �ϸ� model�� ������ �����
					// forward:/loginView �� �ϸ� ���� �޼ҵ��� model, result ���� ���޵�
					// redirect �� �� ������ ������ ��� RedirectAttributes ��ü �̿�
					return "redirect:/loginView";
					
				}
				
				
		try {
			from = URLDecoder.decode(from, "UTF-8");
			from = from.replace("&#61;", "=");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		System.out.println(from + "���� ��û");
		
		return "redirect:" + from;
		
	}
	
	// �α׾ƿ� ��û
	@RequestMapping("/logoutDo")
	public String logoutDo(HttpSession session) {
		session.invalidate();
		
		return "redirect:/";
		
	}
	
	// ȸ�� ���� ���� ȭ��
	@RequestMapping("/memEditView")
	public String memEditView() {
		
		return "member/memEditView";
		
	}
	
	// ȸ�� ���� ���� ��û
	@PostMapping("/memEditDo")
	public String memEditDo(MemberDTO member, HttpSession session) {
		
		System.out.println(member);
		
		String encodePw = passwordEncoder.encode(member.getMemPassword());
		
		member.setMemPassword(encodePw);
		
		memberService.updateMember(member);
		
		MemberDTO login = memberService.getMember(member.getMemId());
		session.setAttribute("login", login);
		
		return "redirect:/";
		
	}
	
	// ȸ�� Ż�� ��û
	@PostMapping("/delMemberDo")
	public String delMemberDo(HttpSession session) {
		
		MemberDTO member = (MemberDTO)session.getAttribute("login");
		
		boardService.noMemIdBoard(member.getMemId());
		
		boardService.noMemIdPhoto(member.getMemId());
		
		memberService.deleteMember(member.getMemId());
	
		session.invalidate();
		
		return "redirect:/";
		
	}
	

	
	
	
	
	
	
	
	
}
