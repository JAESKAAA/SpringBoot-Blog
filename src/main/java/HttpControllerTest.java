import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.test.Member;

//@Component : 사용자 요청 -> 응답(HTML)

@RestController // 사용자 요청 -> 응답(DATA)
public class HttpControllerTest {

	//브라우저 요청은 GET만 가능
	@GetMapping("/http/get")
	public String getTest(Member m) {
		return "get 요청 = " + m.getId()+ " username = "+m.getUsername();
	}
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m) { // JSON 요청이 날아오면 MessageConverter(Jackson)가 Json을 parsing해서 커멘트 객체에 넣어줌
		return "post 요청 = "+ m.getId()+", username = "+m.getUsername();
	}
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청"+ m.getId()+ " username = "+m.getUsername();	
	}
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";	
	}
}