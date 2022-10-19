<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel = "stylesheet" href = "resources/index.css">
<title>쇼핑몰 회원관리</title>
</head>
<body>
	<jsp:include page="header.jsp"/>
	<section>
		<h3>쇼핑몰 회원등록</h3>
		<form method = "post" action = "">
			<table border = "1" style = "border-collapse : collapse;">
				<tr>
					<td>회원번호(자동발생)</td>
					<td><input type = "text" readonly></td>
				</tr>
				<tr>
					<td>회원성명</td>
					<td><input type = "text"></td>
				</tr>
				<tr>
					<td>회원전화</td>
					<td><input type = "text"></td>
				</tr>
				<tr>
					<td>회원주소</td>
					<td><input type = "text"></td>
				</tr>
				<tr>
					<td>가입일자</td>
					<td><input type = "date"></td>
				</tr>
				<tr>
					<td>고객등급[A : VIP, B : 일반, C : 직원]</td>
					<td><select>
						<option>A</option>
						<option>B</option>
						<option>C</option>
					</select></td>
				</tr>
				<tr>
					<td>도시코드</td>
					<td><input type = "text"></td>
				</tr>
				<tr>
					<td colspan = "2"><input type = "submit" value = "등록">
					<input type = "button" value = "조회"></td>
				</tr>
			</table>
		</form>
	</section>
	<jsp:include page="footer.jsp"/>
</body>
</html>