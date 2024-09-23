package com.spring.mypage.member.dto;

import javax.validation.constraints.Pattern;

public class MemberDTO {
	
	@Pattern(regexp = "[a-zA-Z0-9]{4,10}",message="아이디는 4글자 이상 10글자 이하 입니다.")
	private String memId;
	
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\\\d~!@#$%^&*()+|=]{4,16}$",message="비밀번호는 4글자 이상 16글자 이하 입니다.")
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
