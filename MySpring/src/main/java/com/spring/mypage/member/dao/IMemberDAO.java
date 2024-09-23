package com.spring.mypage.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.spring.mypage.member.dto.MemberDTO;

@Mapper
public interface IMemberDAO {
	
	// 회원가입 추상 메소드
	int registMember(MemberDTO member);
	
	// 로그인 메소드
	MemberDTO loginDo(MemberDTO member);
	
	// 회원 수정 메소드
	int updateMember(MemberDTO member);
	
	// 회원 정보 가져오기
	MemberDTO getMember(String memId);
	
	// 회원 정보 삭제
	int deleteMember(String memId);
	
	// 회원 프로필 이미지 반영
	int updateProfile(MemberDTO member);
	
	// 회원 리스트
	List<MemberDTO> getMemList();
	
	// 관리자 권한 추가
	int updateAdminDo(String memId);
	
	// 관리자 권한 제거
	int outAdminDo(String memId);
	
}
