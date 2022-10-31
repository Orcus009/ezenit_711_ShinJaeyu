<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원가입</h1>
	<div>
    	<form method="post" action="index">
    		<input type="text" name="id" class="text-field" placeholder="아이디" required><br>
    		<input type="text" name="pw" class="text-field" placeholder="비밀번호" required><br>
    		<input type="text" name="name" class="text-field" placeholder="이름" required><br>
    		<input type="text" name="phNum" class="text-field" placeholder="전화번호" required><br>
    		<input type="text" name="address" class="text-field" placeholder="주소" required><br>
    		<input type="text" name="driveCode" class="text-field" placeholder="운전면허 코드" required><br>
    		<input type="submit" value="계정 생성" class="submit-btn">
    	</form>
    </div>
</body>
</html>