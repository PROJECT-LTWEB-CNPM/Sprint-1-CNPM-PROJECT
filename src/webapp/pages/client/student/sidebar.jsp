<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String context = request.getContextPath();
%>
<ul class="category-list">
	<li class="category-item"><ion-icon name="home-outline"></ion-icon>
		<a href="<%=context %>/home/student" class="category-item__link">Trang của bạn</a></li>
	<li class="category-item"><ion-icon name="person-outline"></ion-icon>
		<a href="<%=context%>/user-profile" class="category-item__link">Thông tin
			sinh viên</a></li>
	<li class="category-item"><ion-icon name="pencil"></ion-icon> <a
		href="<%=context %>/home/student/register-topic" class="category-item__link">Đăng ký đề
			tài</a></li>
	<li class="category-item"><ion-icon name="people-outline"></ion-icon>
		<a href="<%=context %>/group-manage" class="category-item__link">Quản lý nhóm</a></li>
</ul>