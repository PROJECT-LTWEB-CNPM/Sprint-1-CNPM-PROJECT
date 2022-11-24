<%@page import="com.courses.utils.constants.RoleConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String context = request.getContextPath();
String type = (String) request.getAttribute("type");
String title = type.substring(0, 1).toUpperCase() + type.substring(1);
%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../partials/head.jsp"></jsp:include>
<title><%=title%> | Topic Registration Admin</title>
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
								<%=title%>
								<button type="button" data-bs-toggle="modal"
									data-bs-target="#modalCreateUser"
									class="btn btn-sm btn-outline-primary float-end">
									<i class="fas fa-user-plus"></i> Create New
								</button>
							</h3>
						</div>
						<div class="box box-primary">
							<div class="box-body">
								<c:choose>
									<c:when test="${users.size() != 0}">
										<table width="100%" class="table table-hover"
											id="dataTables-example">
											<thead>
												<tr>
													<th>User ID</th>
													<th>Name</th>
													<th>Email</th>
													<th>Role</th>
													<th>Type</th>
													<th></th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="item" items="${users}">
													<tr>
														<td>${item.getPerson().getPersonId()}</td>
														<td>${item.getPerson().getFullName()}</td>
														<td>${item.getPerson().getEmail()}</td>
														<td>${item.getPerson().getRole()}</td>
														<td>Normal</td>
														<td class="text-end"><c:choose>
																<c:when test="${type == RoleConstants.ADMIN}">
																	<a
																		href="<%=context%>/admin/users/edit/?type=${type}&id=${item.getAdminId()}"
																		class="btn btn-outline-info btn-rounded"><i
																		class="fas fa-pen"></i></a>
																</c:when>
																<c:when test="${type == RoleConstants.TEACHER}">
																	<a
																		href="<%=context%>/admin/users/edit/?type=${type}&id=${item.getTeacherId()}"
																		class="btn btn-outline-info btn-rounded"><i
																		class="fas fa-pen"></i></a>
																</c:when>
																<c:otherwise>
																	<a
																		href="<%=context%>/admin/users/edit/?type=${type}&id=${item.getStudentId()}"
																		class="btn btn-outline-info btn-rounded"><i
																		class="fas fa-pen"></i></a>
																</c:otherwise>
															</c:choose>
															<button type="button" data-bs-toggle="modal"
																data-bs-target="#exampleModal"
																class="btn btn-outline-danger btn-rounded">
																<i class="fas fa-trash"></i>
															</button></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</c:when>
									<c:otherwise>
										<h1>No Data</h1>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
				</div>
			</div>
			<jsp:include page="./modalConfirmDeleteUser.jsp"></jsp:include>
			<jsp:include page="./modalCreateUser.jsp"></jsp:include>
		</div>
	</div>
	<jsp:include page="../partials/tail.jsp"></jsp:include>
</body>
</html>