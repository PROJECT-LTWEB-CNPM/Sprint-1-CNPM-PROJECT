<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String context = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../partials/head.jsp" />
<title>Trang chủ - Đăng ký thành viên</title>
</head>
<body>
	<div class="form__contain">
		<form action="<%=context %>/add-member-to-group" method="get">
			<h1>Register Group Student</h1>
			<p>Please fill in this form to create an group student for registration topic.</p>
			<hr>
	
			<label for="studentId"><b>Student ID</b></label>
			<input type="text" placeholder="Enter Student ID" name="studentId" id="studentId" required><br>
	
			<hr>
			<p>By creating an account you agree to our <a href="#">Terms & Privacy</a>.</p>
	
			<button type="submit" class="registerbutton">Register</button>
		</form>
	</div>
</body>
</html>
