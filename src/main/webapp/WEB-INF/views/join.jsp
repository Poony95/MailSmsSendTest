<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#box_check, #f{
	display: none;
}
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<script type="text/javascript">
$(function(){
	var sendNUM;
	var userNUM;
	var email;
	var phone;
	
	var authType;
	var to;
	
	$("#radio_phone").click(function(){
		$("#span_auth").html("휴대폰 번호:");
		authType="phone";		
	});
	
	$("#radio_email").click(function(){
		$("#span_auth").html("이메일 주소:");
		authType="email";		
	});
	
	$("#btnCheck").click(function(){
		to = $("#to").val();
		var data  = {
			to:to,
			authType:authType
		};
		
		$.ajax({
			url:"validCheck",
			data:data,
			success:function(n){
				sendNUM = eval(n);				
				$("#box_check").css("display","block");
				$("#box_send").css("display","none");
				
			}
		})
	});
	
	
	$("#btnCheckNUM").click(function(){
		var userNUM = eval( $("#checkNUM").val() );
		if(userNUM == sendNUM){
			$("#f").css("display","block");
			$("#box_check").css("display","none");
			
			if(authType == "phone"){
				$("#db_phone").val(to);				
			}else{
				$("#db_email").val(to);
			}			
		}
	});
})
</script>
</head>
<body>
	<h2>회원가입</h2>
	<hr>
	
	<div id="box_send">
		<input type="radio" id="radio_phone" name="authType">문자인증
		<input type="radio" id="radio_email" name="authType">이메일인증
		
		<span id="span_auth">휴대폰번호</span> : 
		<input type="text" id="to">
		<button id="btnCheck">인증</button>
	</div>
	<div id="box_check">
		인증번호 입력 : <input type="text" id="checkNUM">
		<button id="btnCheckNUM">확인</button>
	</div>
	
	<form action="join" method="post" id="f">
		아이디 : <input type="text" name="id"><br>
		비밀번호 : <input type="password" name="pwd"><br>
		이름 : <input type="text" name="name"><br>
		이메일 : <input type="email" name="email" id="db_email"><br>
		전화번호 : <input type="tel" name="phone" id="db_phone"><br>
		<input type="submit" value="등록">
		<input type="reset" value="다시입력">
	</form>
</body>
</html>












