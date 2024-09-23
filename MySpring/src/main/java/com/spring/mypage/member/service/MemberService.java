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
	
	// ȸ������
	public int registMember(MemberDTO member) {
		int result = dao.registMember(member);
		return result;
	}
	
	// �α���
	public MemberDTO loginDo(MemberDTO member) {
		MemberDTO result = dao.loginDo(member);
		return result;
		
	}
	
	// ȸ������
	public int updateMember(MemberDTO member) {
		int result = dao.updateMember(member);
		return result;
	}
	
	// ȸ�� id ��������
	public MemberDTO getMember(String memId) {
		MemberDTO result = dao.getMember(memId);
		return result;
	}

	// ȸ�� Ż��
	public int deleteMember(String memId) {
		int result = dao.deleteMember(memId);
		return result;
		
	}
	
	// ȸ�� ������ �̹��� �ݿ� �޼ҵ�
	public int updateProfile(MemberDTO member) {
		int result = dao.updateProfile(member);
		return result;
	}
	
	// ȸ�� ����Ʈ
	public List<MemberDTO> getMemList(){
		List<MemberDTO> result = dao.getMemList();
		return result;
	}
	
	// ������ ���� �߰�
	public int updateAdminDo(String memId) {
		int result = dao.updateAdminDo(memId);
		return result;
	}
	
	// ������ ���� ����
	public int outAdminDo(String memId) {
		int result = dao.outAdminDo(memId);
		return result;
		
	}
	
	
	
}
