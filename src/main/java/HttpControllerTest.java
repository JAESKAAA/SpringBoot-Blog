import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.test.Member;

//@Component : ����� ��û -> ����(HTML)

@RestController // ����� ��û -> ����(DATA)
public class HttpControllerTest {

	//������ ��û�� GET�� ����
	@GetMapping("/http/get")
	public String getTest(Member m) {
		return "get ��û = " + m.getId()+ " username = "+m.getUsername();
	}
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m) { // JSON ��û�� ���ƿ��� MessageConverter(Jackson)�� Json�� parsing�ؼ� Ŀ��Ʈ ��ü�� �־���
		return "post ��û = "+ m.getId()+", username = "+m.getUsername();
	}
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put ��û"+ m.getId()+ " username = "+m.getUsername();	
	}
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete ��û";	
	}
}