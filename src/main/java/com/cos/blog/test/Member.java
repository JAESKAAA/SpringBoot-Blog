package com.cos.blog.test;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@RequiredArgsConstructor :final 붙은 생성자들을 매개변수로 넣은 생성자 만들어줌
@NoArgsConstructor // 기본 생성자 만들어줌
public class Member {
	private int id;
	private String username;
	private String password;
	private String email;
	
	@Builder //생성자 관련, 메소드 만들때 생성자 순서 상관 없이 빌드할 수 있음
	public Member(int id, String username, String password, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	
	
	
	
	
	
	
}
