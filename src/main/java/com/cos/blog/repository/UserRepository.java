package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

//JpaRepository를 상속받는데, 해당 인터페이스는 user테이블을 관리하며, 키값은 Integer라는 의미
//DAO역할
//자동으로 bean 등록이 됨 따라서, @Repository 어노테이션을 생략 가능함
public interface UserRepository extends JpaRepository<User, Integer> {

	
}
