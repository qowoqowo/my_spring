package com.spring.mypage.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mypage.member.dao.IMemberDAO;
import com.spring.mypage.member.dto.MemberDTO;

@Service
public class MemberService {

	@Autowired
	IMemberDAO dao;
	
	// 회원가입
	public int registMember(MemberDTO member) {
		int result = dao.registMember(member);
		return result;
	}
	
	// 로그인
	public MemberDTO loginDo(MemberDTO member) {
		MemberDTO result = dao.loginDo(member);
		return result;
		
	}
	
	// 회원수정
	public int updateMember(MemberDTO member) {
		int result = dao.updateMember(member);
		return result;
	}
	
	// 회원 id 가져오기
	public MemberDTO getMember(String memId) {
		MemberDTO result = dao.getMember(memId);
		return result;
	}

	// 회원 탈퇴
	public int deleteMember(String memId) {
		int result = dao.deleteMember(memId);
		return result;
		
	}
	
	// 회원 프로필 이미지 반영 메소드
	public int updateProfile(MemberDTO member) {
		int result = dao.updateProfile(member);
		return result;
	}
	
	// 회원 리스트
	public List<MemberDTO> getMemList(){
		List<MemberDTO> result = dao.getMemList();
		return result;
	}
	
	// 관리자 권한 추가
	public int updateAdminDo(String memId) {
		int result = dao.updateAdminDo(memId);
		return result;
	}
	
	// 관리자 권한 제거
	public int outAdminDo(String memId) {
		int result = dao.outAdminDo(memId);
		return result;
		
	}
	
	
	
}
