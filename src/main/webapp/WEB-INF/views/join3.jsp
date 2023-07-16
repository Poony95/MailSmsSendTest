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
	
	$("#btnCheckPhone").click(function(){
		phone = $("#phone").val();
		var data  = {
			phone:phone
		};
		
		$.ajax({
			url:"validPhone",
			data:data,
			success:function(n){
				sendNUM = eval(n);
				//alert("인증번호를 이메일로 발송하였습니다.");
				$("#box_check").css("display","block");
				$("#box_phone").css("display","none");
				
			}
		})
	});
	
	$("#btnCheckNUM").click(function(){
		var userNUM = eval( $("#checkNUM").val() );
		if(userNUM == sendNUM){
			$("#f").css("display","block");
			$("#box_check").css("display","none");
			$("#db_phone").val(phone);
		}
	});
})
</script>
</head>
<body>
	<h2>회원가입</h2>
	<hr>
	
	<div id="box_phone">
		휴대폰번호 : <input type="tel" id="phone">
		<button id="btnCheckPhone">인증</button>
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












