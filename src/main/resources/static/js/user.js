let index = {
	init:function(){
		$("#btn-save").on("click",function(){
		index.save();
		});
		
		$("#btn-login").on("click",function(){
		index.login();
		});
	},

	save:function(){
		let data = {
			username : $("#username").val(),
			password : $("#password").val(),
			email : $("#email").val()
		};
		console.log(data);

		// ajax호출시 default가 비동기 호출
		// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
		// ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해줌
		$.ajax({
			//회원가입 수행 요청
			type:"POST",
			url:"/blog/api/user",
			data: JSON.stringify(data), //http body 데이터
			contentType: "application/json; charset=utf-8", //body데이터가 어떤 타입인지 (MIME)
			dataType : "json"
			//요청을 서버로 해서 응답이 왔을 때 기본적으로 모든것이 문자열인데, 형태가 json형태면 자바스크립트 객체로 변환해줌
		}).done(function(resp){
			//요청 성공하면 해당 function 실행
			alert("회원가입이 완료되었습니다.");
			console.log(resp);
			location.href = "/blog";
		}).fail(function(error){
			//요청 실패시 해당 function 실행
			alert(JSON.stringify(error));
		});
		
	},
	
	login:function(){
		let data = {
			username : $("#username").val(),
			password : $("#password").val()

		};
		console.log(data);

		// ajax호출시 default가 비동기 호출
		// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
		// ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해줌
		$.ajax({
			//회원가입 수행 요청
			type:"POST",
			url:"/blog/api/user/login",
			data: JSON.stringify(data), //http body 데이터
			contentType: "application/json; charset=utf-8", //body데이터가 어떤 타입인지 (MIME)
			dataType : "json"
			//요청을 서버로 해서 응답이 왔을 때 기본적으로 모든것이 문자열인데, 형태가 json형태면 자바스크립트 객체로 변환해줌
		}).done(function(resp){
			//요청 성공하면 해당 function 실행
			alert("로그인이 완료되었습니다.");
			console.log(resp);
			location.href = "/blog";
		}).fail(function(error){
			//요청 실패시 해당 function 실행
			alert(JSON.stringify(error));
		});
		
	}
}	

index.init();

