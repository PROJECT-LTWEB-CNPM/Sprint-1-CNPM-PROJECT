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
<title>Boards | Topic Registration Admin</title>
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
							<h3 class="d-flex justify-content-between">
								<span>Board Manage</span>
								<div class="d-flex gap-2">
									<button type="button" data-bs-toggle="modal"
										data-bs-target="#selectSemesterModal"
										class="btn btn-sm btn-outline-primary">
										<i class="fas fa-object-group"></i> Semesters
									</button>
									<button type="button" data-bs-toggle="modal"
										data-bs-target="#modalCreateRP"
										class="btn btn-sm btn-outline-primary">
										<i class="fas fa-clock"></i> Create New
									</button>
								</div>
							</h3>
						</div>
						<div class="box box-primary">
							<div class="box-body">
								<div class="row mb-3">
									<div class="col-2 d-flex gap-1">
										Show <select name="board_length" aria-controls="dataTable"
											class="custom-select custom-select-sm form-control form-control-sm"><option
												value="10">10</option>
											<option value="25">25</option>
											<option value="50">50</option>
											<option value="100">100</option></select> entries
									</div>
								</div>
								<table width="100%" class="table table-hover table-bordered"
									id="dataTables-example">
									<thead>
										<tr>
											<th>ID</th>
											<th>Name</th>
											<th>No member</th>
											<th>Description</th>
											<th></th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="item" items="${boards}">
											<tr>
												<td>${item.getBoardId()}</td>
												<td>${item.getBoardName()}</td>
												<td>${item.getNoMember()}</td>
												<td>${item.getDescription()}</td>
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
								<div class="row">
									<div class="col-sm-12 col-md-5">Showing 1 to 7 of
										${maxEntries} entries</div>
									<div class="col-sm-12 col-md-7">
										<div class="dataTables_paginate paging_simple_numbers"
											id="dataTables-example_paginate">
											<ul class="pagination justify-content-end mx-2">
												<li class="paginate_button page-item previous disabled"
													id="dataTables-example_previous"><a href="#"
													aria-controls="dataTables-example" data-dt-idx="0"
													tabindex="0" class="page-link">Previous</a></li>
												<c:forEach var="item" begin="1" end="${maxEntries}">
													<c:choose>
														<c:when test="${item == 1}">
															<li class="paginate_button page-item active"><a
																href="<%=context %>/admin/boards/?page=${item}"
																aria-controls="dataTables-example" data-dt-idx="1"
																tabindex="0" class="page-link">${item}</a></li>
														</c:when>
														<c:otherwise>
															<li class="paginate_button page-item"><a
																href="<%=context %>/admin/boards/?page=${item}"
																aria-controls="dataTables-example" data-dt-idx="1"
																tabindex="0" class="page-link">${item}</a></li>
														</c:otherwise>
													</c:choose>
												</c:forEach>
												<li class="paginate_button page-item next disabled"
													id="dataTables-example_next"><a href="#"
													aria-controls="dataTables-example" data-dt-idx="2"
													tabindex="0" class="page-link">Next</a></li>
											</ul>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="./modalCreateBoard.jsp"></jsp:include>
	<jsp:include page="./modalSelectSemester.jsp"></jsp:include>
	<jsp:include page="../partials/tail.jsp"></jsp:include>
</body>
</html>