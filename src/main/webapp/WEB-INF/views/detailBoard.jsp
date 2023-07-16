<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>게시물 상세</h1>
	<hr>
	게시물번호 : ${b.no }<br>
	글제목 : ${b.title }<br>
	작성자 : ${b.writer }<br>
	글내용 : <br>
	<textarea rows="10" cols="60" readonly="readonly">${b.content }</textarea><br>
	조회수 : ${b.hit }<br>
	등록일 : ${b.regdate }<br>
	<img src="upload/${b.fname }" width="200" height="200">
	<hr>
	<a href="listBoard">게시물 목록</a>
	<a href="insertBoard?no=${b.no }">답글작성</a>
	<a href="updateBoard?no=${b.no }">수정</a>
	<a href="deleteBoard?no=${b.no }">삭제</a>
	
</body>
</html>











