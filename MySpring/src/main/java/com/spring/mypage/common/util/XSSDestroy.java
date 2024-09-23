package com.spring.mypage.common.util;

import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;

public class XSSDestroy {

	// 사용자의 입력값에서 허용할 태그와 속성을 설정
	private static final PolicyFactory policy = new HtmlPolicyBuilder()
			.allowElements("p", "b", "i", "u", "string", "em", "a", "img")
			.allowAttributes("href", "target", "class").onElements("a")
			.allowAttributes("src", "alt", "style", "width", "height", "class").onElements("img")
			.allowStandardUrlProtocols()
			.toFactory();
	
	// 파라미터에 문자열이 들어오면 위 설정을 제외한 태그 및 속성을 파괴한 결과를 리턴
	public static String destroy(String inputText) {
		return policy.sanitize(inputText);
	}
	
	
	
	
	
	
}
