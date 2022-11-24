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
<title>Registration Priods For Teacher | Topic Registration
	Admin</title>
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
								Registration Priods For Teacher
								<button type="button" data-bs-toggle="modal"
									data-bs-target="#modalCreateRP"
									class="btn btn-sm btn-outline-primary float-end">
									<i class="fas fa-clock"></i> Create New
								</button>
							</h3>
						</div>
						<div class="box box-primary">
							<div class="box-body">
								<table width="100%" class="table table-hover"
									id="dataTables-example">
									<thead>
										<tr>
											<th>ID</th>
											<th>Name</th>
											<th>Semeter</th>
											<th>School Year</th>
											<th>Open Date</th>
											<th>Close Date</th>
											<th></th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="item" items="${registrationPeriods}">
											<tr>
												<td>${item.getRegistrationPeriodId()}</td>
												<td>${item.getRegistrationPeriodName()}</td>
												<td>${item.getSemeter()}</td>
												<td>${item.getSchoolYear()}</td>
												<td>${item.getOpenDate()}</td>
												<td>${item.getCloseDate()}</td>
												<td class="text-end"><a href=""
													class="btn btn-outline-info btn-rounded"><i
														class="fas fa-pen"></i></a>

													<button type="button" data-bs-toggle="modal"
														data-bs-target="#modalConfirmDeleteRP"
														class="btn btn-outline-danger btn-rounded">
														<i class="fas fa-trash"></i>
													</button>
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

	<jsp:include page="./modalCreateRP.jsp"></jsp:include>
	<jsp:include page="./modalConfirmDeleteRP.jsp"></jsp:include>
	<jsp:include page="../partials/tail.jsp"></jsp:include>
</body>
</html>