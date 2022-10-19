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
		<h3>회원매출조회</h3>
		<table border = "1" style = "border-collapse : collapse;">
			<thead>
				<tr>
					<th>회원번호</th>
					<th>회원성명</th>
					<th>고객등급</th>
					<th>매출</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>100001</td>
					<td>김행복</td>
					<td>VIP</td>
					<td>8000</td>
				</tr>
			</tbody>
		</table>
	</section>
	<jsp:include page="footer.jsp"/>
</body>
</html>