package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//@Component : 사용자 요청 -> 응답(HTML)

@RestController // 사용자 요청 -> 응답(DATA)
public class HttpControllerTest {

	private static final String TAG = "HttpControllerTest";
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		//하기와 같이 빌더로 생성자에 변수별로 매핑해주기 때문에 순서 안지키고 객체를 생성해도 문제없음
		Member m = Member.builder().username("스카").password("asdf").email("asd@test.com").build();
		System.out.println(TAG+"getter: "+m.getId());
		System.out.println(TAG+"_username = "+m.getUsername());
		m.setId(50000);
		System.out.println(TAG+"setter: "+m.getId());
		return "lombok test 완료";
	}
	
	//브라우저 요청은 GET만 가능
	@GetMapping("/http/get")
	public String getTest(Member m) {
	
		return "get 요청 = " + m.getId()+ " username = "+m.getUsername();
	}
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m) {
		return "post 요청 = "+ m.getId()+ " username = "+m.getUsername();
	}
	@PutMapping("/http/put")
	public String putTest() {
		return "put 요청";	
	}
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";	
	}
	
}