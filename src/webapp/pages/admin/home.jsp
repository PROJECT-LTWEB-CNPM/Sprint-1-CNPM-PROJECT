<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String context = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="./partials/head.jsp"></jsp:include>
<title>Dashboard | Topic Registration Admin</title>
</head>
<body>
	<div class="wrapper">
		<jsp:include page="./partials/sidebar.jsp"></jsp:include>
		<div id="body" class="active">
			<!-- navbar navigation component -->
			<jsp:include page="./partials/navigation.jsp"></jsp:include>
			<!-- end of navbar navigation -->
			<div class="content">
				<div class="container">
					<div class="row">
						<div class="col-md-12 page-header">
							<div class="page-pretitle">Overview</div>
							<h2 class="page-title">Dashboard</h2>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="./partials/tail.jsp"></jsp:include>
</body>
</html>