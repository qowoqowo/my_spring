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

	// 회원가입 화면
	@RequestMapping("/registView")
	public String registView() {
		
		return "member/registView";
	}
	
	// 회원 가입 요청
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
		
		// 세팅 전 Pw 암호화
		String encodePw = passwordEncoder.encode(request.getParameter("pw"));
		
		member.setMemPassword(encodePw);
		member.setMemName(request.getParameter("name"));
		member.setMemPhone(request.getParameter("phone"));
		member.setMemEmail(request.getParameter("email"));
		
		try {
			memberService.registMember(member);
		} catch (Exception e) {
			e.printStackTrace();
			
			attr.addFlashAttribute("failMsg", "중복된 아이디 입니다.");
			
			return "redirect:/registView";
		}		
		
		
		
		return "redirect:/loginView/";
		
		
	}
	
	// 로그인 화면
	@RequestMapping("/loginView")
	public String loginView(HttpServletRequest request, Model model) {
		
		String from = request.getHeader("Referer");
		
		 try {
			from = URLDecoder.decode(from, "UTF-8");
			from = from.replace("&#61;", "=");
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
         
        
		
		System.out.println(from + "에서 /loginView 요청");
		
		model.addAttribute("keyFrom",from);
		
		return "member/loginView";
		
	}
	
	// 로그인 요청
	@PostMapping("/loginDo")
	public String loginDo(MemberDTO member, HttpSession session, boolean rememberId, HttpServletResponse respons, RedirectAttributes attr, String from) {
		
		System.out.println(member);

		MemberDTO login = memberService.loginDo(member);
		
		// 로그인 성공 여부 판별
				if(login != null) {
					
					// passwordEncoder 를 이용하여
					// 사용자가 입력한 비밀번호와 DB에 암호화된 비밀번호를 비교
					
					// 일치하면 true, 틀리면 false 리턴
					boolean isMatch = passwordEncoder.matches(member.getMemPassword(), login.getMemPassword());
					
					if(isMatch == false) {
						attr.addFlashAttribute("failMsg", "아이디 혹은 비밀번호가 올바르지 않습니다.");
						return "redirect:/loginView";
					}					
					
					System.out.println(login);
					
					// 로그인 성공시 세션 객체에 로그인 정보 저장
					// 키값: login, 들어가는 value값: MemberDTO 객체
					session.setAttribute("login", login);
					
					
					// 아이디 기억하기가 체크되어 있으면
					// 아이디 정보를 클라이언트(브라우저)에 쿠키로 만들어서 전송
					if(rememberId) {
						// 아이디 기억하기 체크
						
						// 쿠키 생성
						// 키값: rememberId
						// value값: 로그인한 사람의 아이디
						Cookie cookie = new Cookie("rememberId", login.getMemId());
						
						// 생성된 쿠키를 응답(response) 객체에 담아준다.
						respons.addCookie(cookie);
					}else {
						// 아이디 기억하기 체크안함
						// rememberId 키값으로 만들어서 보낸 쿠키를 제거해주기
						
						// 쿠키 삭제 (동일한 키값을 갖은 쿠키 생성)
						Cookie cookie = new Cookie("rememberId", "");
						
						// 쿠키 유효기한 0으로 만들어주기 (클라이언트 전달시 바로 삭제됨)
						cookie.setMaxAge(0);
						
						// 응답 객체에 쿠키를 담음
						respons.addCookie(cookie);
						
						
					}
					
				}else {
					// 로그인 실패
					// 로그인 실패 문구와 함께 로그인 페이지가 열림
					// model에 failMsg 키 값으로 "아이디 혹은 비밀번호가 올바르지 않습니다." 값을 저장
					// loginView.jsp에서 failMsg 키값을 통해 값을 꺼내 쓸 수 있다
//					model.addAttribute("failMsg", "아이디 혹은 비밀번호가 올바르지 않습니다.");
					
					attr.addFlashAttribute("failMsg", "아이디 혹은 비밀번호가 올바르지 않습니다.");
					
					// redirect:/loginView 를 하면 model의 내용이 사라짐
					// forward:/loginView 를 하면 현재 메소드의 model, result 값이 전달됨
					// redirect 할 때 데이터 보내는 경우 RedirectAttributes 객체 이용
					return "redirect:/loginView";
					
				}
				
				
		try {
			from = URLDecoder.decode(from, "UTF-8");
			from = from.replace("&#61;", "=");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		System.out.println(from + "에서 요청");
		
		return "redirect:" + from;
		
	}
	
	// 로그아웃 요청
	@RequestMapping("/logoutDo")
	public String logoutDo(HttpSession session) {
		session.invalidate();
		
		return "redirect:/";
		
	}
	
	// 회원 정보 수정 화면
	@RequestMapping("/memEditView")
	public String memEditView() {
		
		return "member/memEditView";
		
	}
	
	// 회원 정보 수정 요청
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
	
	// 회원 탈퇴 요청
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
