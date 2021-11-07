package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {
	
	@Autowired //의존성 주입
	private UserRepository userRepository;
	
	/*[save] 함수 관련
	 * 1. id 전달 안하는 경우 : insert
	 * 2. id 전달 + 해당 id에 대한 데이터 있을 경우 : update
	 * 3. id 전달 + 해당 데이터 없을 경우 : insert
	 */
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
	
		try {
			userRepository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다.";
		}
		
		return "삭제되었습니다. id= "+id;
	}
	
	//email, password
	@Transactional //메소드 종료시 자동으로 commit 됨
	@PutMapping("/dummy/user/{id}")
	//JSON 데이터로 요청이 들어오지만, MessageConver(Jackson라이브러리)가 자바 객체로 변환해서 받아줌
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
		System.out.println("id = "+ id);
		System.out.println("password = "+ requestUser.getPassword());
		System.out.println("email = "+ requestUser.getEmail());
		
		requestUser.setId(id);
		
		//DB -> 영속성 컨텍스트로 영속화됨
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		//영속화 후 트랜잭션은 데이터 변경을 감지함
		//하기와 같이 password와 Email이 영속화 컨텍스트의 데이터와 다른것을 감지했기 떄문에,
		//커밋시 해당 데이터를 DB에 플러시 하게됨
		//이러한 프로세스를 더티 체킹이라고 함
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		//상기 코드와 같이 User를 따로 찾아주지 않으면 id값이 null로 들어가 문제가 발생함
		//상기 코드의 경우 입력한 id의 user객체를 만들어 내부에 변경할 데이터만 업데이트해서 다시 save처리하기 때문에
		//정상적으로 데이터가 저장되는 원리임
//		userRepository.save(user);
		/*
		 * 더티 체킹
		 */
		
		
		return user;
	}
	
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	//한 페이지당 2건의 데이터를 리턴받기
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
	Page<User> pagingUser= userRepository.findAll(pageable);
		
	List<User> users = pagingUser.getContent();
	return users;
	}
	//{id}주소로 파라미터를 전달 받을 수 있음
	// http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		
		/*
		 * 만약 DB에서 해당 User가 없는 경우 null을 반환하게 될텐데,
		 * Optional로 user를 감싸서 가져오게되면 null 여부를 판단한 후,
		 * 리턴을 할 수 있음
		 */
		
		//람다식 표현
//		User user = userRepository.findById(id).orElseThrow(()->{
//			return new IllegalArgumentException("해당 사용자는 없습니다."); 
//		});
			
		
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
		//하기와 같이 에러를 던져줌으로써 무슨 오류인지 파악이 가능해짐
		@Override
		public IllegalArgumentException get() {
			return new IllegalArgumentException("해당 유저는 없습니다. id : "+id);
		}
		});
		
		//요청 : 웹브라우저 
		//user 객체 = 자바 오브젝트
		//RestController는 html이 아닌 데이터를 전송함
		//따라서, 브라우저가 이해할 수 있는 데이터로 변환을 해줘야함 (JSON)
		//스프링부트 = MessageConverter라는 애가 응답시 자동으로 작동함
		//만약 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson라이브러리를 통해
		//Json으로 변환해서 던져줌
		return user;
		
	}
	
	@PostMapping("/dummy/join")
	public String join(User user) {
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getEmail());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다";
	}
	
	
}
