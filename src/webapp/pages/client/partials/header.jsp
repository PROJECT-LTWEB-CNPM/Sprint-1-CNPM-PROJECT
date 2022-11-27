<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<% 
	String context = request.getContextPath(); 
%>

<header>
	<div id='header'>
		<div class='nav'></div>
	</div>
	<div class='header_login'>
		<div class='grid'>
			<c:choose>
				<c:when test="${user != null}">
					<div class="login-name__container">
						<div class='header_login-container'>
							<p>${user.fullName}</p>
						</div>
						<div class='header_login-container'>
							<a href="<%=context%>/logout">
								<button>Đăng xuất</button>
							</a>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<div class='header_login-container'>
						<a href="<%=context%>/login"><button>Đăng nhập</button></a>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</header>