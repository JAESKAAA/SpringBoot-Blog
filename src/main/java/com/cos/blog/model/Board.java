package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto.increment
	private int id;
	
	@Column(nullable=false, length=100)
	private String title;
	
	@Lob //대용량 데이터
	private String content; //섬머노트 라이브러리 <html>태그가 섞여 디자인됨
	
	@ColumnDefault("0")
	private int count; //조회수
	
	@ManyToOne(fetch = FetchType.EAGER)
	//Many = Board, User=One -> 하나의 유저는 많은 게시판을 가질 수 있음
	//만약 OneToOne이면 -> 하나의 유저는 하나의 게시판만 가질수있다고 명시해 주는것
	@JoinColumn(name="userId")
	private User user; 
	/*
	 * DB는 오브젝트를 저장할 수 없어서 FK를 사용하지만, 자바는 오브젝트를 저장할 수 있음
	 * 여기서 DB와 자바프로그램에서 충돌이 일어나게 되므로, 보통 자바가 DB에 맞춰,
	 * private int userId 같이 정의해서 userId를 외래키로 쓰게됨
	 * 하지만, ORM을 사용하면 객체 그대로 저장할 수 있음
	 * 이 경우, JoinColumn(name="userId") -> 실제 테이블 필드값은 userId라는 이름으로 저장되고 FK설정됨
	 * 이때는 아직 User와 연관관계가 형성되지않아 @ManyToOne과 같이 연관관계를 형성시켜줘야 함
	 * 
	
	*/
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER)
	//mappedBy : 연관관계의 주인이 아님 (FK가 아니라고 알려주는 것. 따라서 DB에 컬럼이 생성되지 않음)
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
