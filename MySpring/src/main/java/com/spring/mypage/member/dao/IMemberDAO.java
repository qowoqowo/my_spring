package com.spring.mypage.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.spring.mypage.member.dto.MemberDTO;

@Mapper
public interface IMemberDAO {
	
	// ȸ������ �߻� �޼ҵ�
	int registMember(MemberDTO member);
	
	// �α��� �޼ҵ�
	MemberDTO loginDo(MemberDTO member);
	
	// ȸ�� ���� �޼ҵ�
	int updateMember(MemberDTO member);
	
	// ȸ�� ���� ��������
	MemberDTO getMember(String memId);
	
	// ȸ�� ���� ����
	int deleteMember(String memId);
	
	// ȸ�� ������ �̹��� �ݿ�
	int updateProfile(MemberDTO member);
	
	// ȸ�� ����Ʈ
	List<MemberDTO> getMemList();
	
	// ������ ���� �߰�
	int updateAdminDo(String memId);
	
	// ������ ���� ����
	int outAdminDo(String memId);
	
}
