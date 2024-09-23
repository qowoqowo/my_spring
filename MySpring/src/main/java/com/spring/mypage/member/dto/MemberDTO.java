package com.spring.mypage.member.dto;

import javax.validation.constraints.Pattern;

public class MemberDTO {
	
	@Pattern(regexp = "[a-zA-Z0-9]{4,10}",message="���̵�� 4���� �̻� 10���� ���� �Դϴ�.")
	private String memId;
	
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\\\d~!@#$%^&*()+|=]{4,16}$",message="��й�ȣ�� 4���� �̻� 16���� ���� �Դϴ�.")
	private String memPassword;
	
	private String memName;
	private String memPhone;
	private String memEmail;
	private String memProfileImg;
	private String memLv;
	
	
	public MemberDTO() {
	}


	public MemberDTO(String memId, String memPassword, String memName, String memPhone, String memEmail,
			String memProfileImg, String memLv) {
		this.memId = memId;
		this.memPassword = memPassword;
		this.memName = memName;
		this.memPhone = memPhone;
		this.memEmail = memEmail;
		this.memProfileImg = memProfileImg;
		this.memLv = memLv;
	}


	@Override
	public String toString() {
		return "MemberDTO [memId=" + memId + ", memPassword=" + memPassword + ", memName=" + memName + ", memPhone="
				+ memPhone + ", memEmail=" + memEmail + ", memProfileImg=" + memProfileImg + ", memLv=" + memLv + "]";
	}


	public String getMemId() {
		return memId;
	}


	public void setMemId(String memId) {
		this.memId = memId;
	}


	public String getMemPassword() {
		return memPassword;
	}


	public void setMemPassword(String memPassword) {
		this.memPassword = memPassword;
	}


	public String getMemName() {
		return memName;
	}


	public void setMemName(String memName) {
		this.memName = memName;
	}


	public String getMemPhone() {
		return memPhone;
	}


	public void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}


	public String getMemEmail() {
		return memEmail;
	}


	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}


	public String getMemProfileImg() {
		return memProfileImg;
	}


	public void setMemProfileImg(String memProfileImg) {
		this.memProfileImg = memProfileImg;
	}


	public String getMemLv() {
		return memLv;
	}


	public void setMemLv(String memLv) {
		this.memLv = memLv;
	}


	
	
	
	
	
}
