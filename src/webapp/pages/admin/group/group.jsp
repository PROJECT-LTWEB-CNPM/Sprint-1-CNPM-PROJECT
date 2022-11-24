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
<title>Groups | Topic Registration Admin</title>
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
							<h3>Groups</h3>
						</div>
						<div class="box box-primary">
							<div class="box-body">
								<table width="100%" class="table table-hover"
									id="dataTables-example">
									<thead>
										<tr>
											<th>Group ID</th>
											<th>Status</th>
											<th>Active</th>
											<th>Topic Name</th>
											<th>No Member</th>
											<th></th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="item" items="${groups}">
											<tr>
												<td>${item.getGroupId()}</td>
												<c:choose>
													<c:when test="${item.getIsFull() == 0}">
														<td>Approved</td>
													</c:when>
													<c:otherwise>
														<td>Pending</td>
													</c:otherwise>
												</c:choose>

												<c:choose>
													<c:when test="${item.getIsFull() == 1}">
														<td>Activated</td>
													</c:when>
													<c:otherwise>
														<td>Pending</td>
													</c:otherwise>
												</c:choose>
												<td>${item.getTopic().getTopicName()}</td>
												<td>0/${item.getTopic().getMaxMoMember()}</td>
												<td class="text-end"><a
													href="<%=context%>/admin/group/edit"
													class="btn btn-outline-info btn-rounded"><i
														class="fas fa-pen"></i></a>
													<button type="button" data-bs-toggle="modal"
														data-bs-target="#modalConfirmDeleteGroup"
														class="btn btn-outline-danger btn-rounded">
														<i class="fas fa-trash"></i>
													</button></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
			<jsp:include page="./modalConfirmDeleteGroup.jsp"></jsp:include>
		</div>
	</div>
	<jsp:include page="../partials/tail.jsp"></jsp:include>
</body>
</html>