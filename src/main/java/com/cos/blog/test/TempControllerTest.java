package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //리턴하는 경로에 있는 파일을 던져줌
//다시참고, @RestController 리턴에 해당하는 데이터 자체를 던져줌
public class TempControllerTest {

	//http://localhost:8000/blog/temp/homes
	@GetMapping("/temp/homes")
	public String tempHome() {
		System.out.println("tempHome실행");
		//view 리졸버 때문에 접두사 접미사 처리가 됨 (application.yml에 view관련 참고)
		//"/" 안붙였을때 파일리턴 기본 경로 : src/main/resource/statichome.html
		//리턴명: /home.html
		//풀 경로 : src/main/resource/static/home.html
		return "/home.html"; //앞에 "/"붙여줘야함
	}
	
	@GetMapping("/temp/jsp")
	public String tempHomeJsp() {
		System.out.println("Jsp파일실행");
		//view 리졸버 때문에 접두사 접미사 처리가 됨 (application.yml에 view관련 참고)
		//"/" 안붙였을때 파일리턴 기본 경로 : src/main/resource/statichome.html
		//리턴명: /home.html
		//풀 경로 : src/main/resource/static/home.html
		return "test"; //앞에 "/"붙여줘야함
	}
}
