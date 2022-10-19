<%@page import="java.sql.Timestamp"%>
<%@page import="member.MemberDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="member.MemberDao"%>
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
	<% 
	MemberDao dao = MemberDao.getInstance();
	ArrayList<MemberDto> list = dao.getMemberAll();
	System.out.println("size : " + list.size());
	%>

	<jsp:include page="header.jsp"/>
	<section>
		<h3>회원목록 조회/수정</h3>
		<table border = "1" style = "border-collaspe : collapse;">
			<thead>
				<tr>
					<th>회원번호</th>
					<th>회원성명</th>
					<th>전화번호</th>
					<th>주소</th>
					<th>가입일자</th>
					<th>고객등급</th>
					<th>거주지역</th>
				</tr>
			</thead>
			<%
			for(MemberDto member : list){
				Timestamp joinDate = member.getJoindate();
				String date = String.format("%d-%d-%d", joinDate.getYear(), joinDate.getMonth(), joinDate.getDate());
			%>
			<tbody>
				<tr>
					<td><%=member.getCustno() %></td>
					<td><%=member.getCustname() %></td>
					<td><%=member.getPhone() %></td>
					<td><%=member.getAddress() %></td>
					<td><%=date %></td>
					<td><%=member.getGradeString() %></td>
					<td><%=member.getCity() %></td>
				</tr>
			</tbody>
			<%} %>
		</table>
	</section>
	<jsp:include page="footer.jsp"/>
</body>
</html>