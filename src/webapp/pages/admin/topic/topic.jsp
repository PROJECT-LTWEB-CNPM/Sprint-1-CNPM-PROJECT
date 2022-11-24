<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String context = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../partials/head.jsp"></jsp:include>
<title>Topic | Topic Registration Admin</title>
</head>
<body>
	<div class="wrapper">
		<jsp:include page="../partials/sidebar.jsp"></jsp:include>
		<div id="body" class="active">
			<!-- navbar navigation component -->
			<jsp:include page="../partials/navigation.jsp"></jsp:include>
			<!-- end of navbar navigation -->
			<div class="content">
				<div class="content">
					<div class="container">
						<div class="page-title">
							<h3>
								Topics 
							</h3>
						</div>
						<div class="box box-primary">
							<div class="box-body">
								<table width="100%" class="table table-hover"
									id="dataTables-example">
									<thead>
										<tr>
											<th>Topic ID</th>
											<th>Topic Name</th>
											<th>Major</th>
											<th>Max Member</th>
											<th>Teacher</th>
											<th></th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="item" items="${topics}">
											<tr>
												<td>${item.getTopicId()}</td>
												<td>${item.getTopicName()}</td>
												<td>${item.getMajor().getMajorName()}</td>
												<td>Admin</td>
												<td>${item.getTeacher().getPerson().getFullName()}</td>
												<td class="text-end"><a href=""
													class="btn btn-outline-info btn-rounded"><i
														class="fas fa-pen"></i></a> <a href=""
													class="btn btn-outline-danger btn-rounded"><i
														class="fas fa-trash"></i></a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../partials/tail.jsp"></jsp:include>
</body>
</html>