package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

//JpaRepository를 상속받는데, 해당 인터페이스는 user테이블을 관리하며, 키값은 Integer라는 의미
//DAO역할
//자동으로 bean 등록이 됨 따라서, @Repository 어노테이션을 생략 가능함
public interface UserRepository extends JpaRepository<User, Integer> {

	//JPA Naming 쿼리
	//Select * from user Where username = ?1 AND password =?2;
	//첫번째 와일드카드에 첫번째 파라미터, 두번째 와일드카드에 두번째 파라미터가 대입됨
	User findByUsernameAndPassword(String username, String password);
	
//	@Query(value="SELECT * FROM user WHERE username =?1 AND password = ?2", nativeQuery = true)
//	User login (String username, String password);
}
