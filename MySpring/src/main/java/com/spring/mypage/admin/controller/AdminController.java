package com.spring.mypage.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.mypage.board.service.BoardService;
import com.spring.mypage.member.dto.MemberDTO;
import com.spring.mypage.member.service.MemberService;

@Controller
public class AdminController {

	@Autowired
	MemberService memService;
	
	@Autowired
	BoardService boardService;
	
	// ȸ������ ������ �̵�
	@RequestMapping("/adminView")
	public String adminView(Model model) {
		
		List<MemberDTO> memList = memService.getMemList();
		model.addAttribute("keyMemList" , memList);
		
		
		return "admin/adminView";
	}
	
	
	// ȸ�� ����
	@PostMapping("/delAdminDo")
	public String delAdminDo(@RequestBody String memId) {
		
		memId = memId.replace("=", "");
		
		System.out.println(memId);
		
		boardService.noMemIdBoard(memId);
		
		memService.deleteMember(memId);
		
	
		return "redirect:/adminView";
	}
	
	
	// ������ ���� �߰�
	@PostMapping("/updateAdminDo")
	public String updateAdminDo(@RequestBody String memId) {
		
		memId = memId.replace("=", "");
		
		System.out.println(memId);
		
		memService.updateAdminDo(memId);
		
		return "redirect:/adminView";
		
		
	}
	
	// ������ ���� ����
	@PostMapping("/outAdminDo")
	public String outAdminDo(@RequestBody String memId) {
		
		memId = memId.replace("=", "");
		
		System.out.println(memId);
		
		memService.outAdminDo(memId);
		
		return "redirect:/adminView";
		
	}
	
	
	
}
