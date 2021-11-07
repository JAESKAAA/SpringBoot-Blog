package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@DynamicInsert //insert실행시 null값은 제외하고 sql 쿼리 날려주는 어노테이션
@Entity //User 클래스가 MySQL에 테이블이 생성됨
public class User {
	
	@Id//pr키
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	// 프로젝트에서 연결된 DB의 넘버린 전략을 따라감
	//오라클 - 시퀀스따라감 / MySQL = auto_increment를 따라감
	private int id; //오라클=시퀀스, auto_increment
	@Column(nullable = false, length=30)
	private String username; //아이디
	@Column(nullable = false, length=100) // 123456 -> 해쉬 (비밀번호 암호화)
	private String password;
	@Column(nullable = false, length=50)
	private String email;
	
	//DB는 RoleType이 없어서 어노테이션 필요
	//@ColumnDefault("'user'")
	@Enumerated(EnumType.STRING)
	private RoleType role; 
	//원래는 타입을 Enum을 써야함. 
	//Enum은 도메인을 정학 수 있음 (도메인은 정확한 값의 범위를 지정하는것 ex. 성별(남,여) 로 지정하면 남과 여만 입력가능)
	
	@CreationTimestamp //시간 자동 입력
	private Timestamp createDate;
	
}
