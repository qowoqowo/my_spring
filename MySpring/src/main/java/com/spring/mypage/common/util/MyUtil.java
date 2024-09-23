package com.spring.mypage.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyUtil {

	// 유니크 아이디 생성 메소드
	public static String makeUniqueId() {
		// yyyyMMddHHmmssSSS + 랜덤숫자 6개
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String result = sdf.format(date);
		
		for(int i = 0; i < 6; i++) {
			int random = (int)(Math.random() * 10);
			result += random;
		}
		
		return result;
		
	}
	
}
