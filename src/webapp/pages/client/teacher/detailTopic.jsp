<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String context = request.getContextPath();
String check = (String) request.getAttribute("notExistPeriod");
%>

<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../partials/head.jsp" />
<title>Giảng viên | Chi tiết đề tài</title>
</head>
<body>
	<div id="root">
		<!-- Header -->
		<jsp:include page="../partials/header.jsp" />
		<!-- Body -->
		<main>
			<div class="container">

				<div class="grid">
					<div class="grid_row">
						<div class="grid_column_1">
							<jsp:include page="./teacherSidebar.jsp" />
						</div>
						<div class="grid_column_3">
							<h1>${topicName}</h1>
							<div class="topic-group">
								<ion-icon name="receipt-outline"></ion-icon>
								<h3>NHÓM SINH VIÊN ĐĂNG KÝ</h3>
							</div>
							<div class="student-table">
								<c:forEach var="id" items="${groupStudentMap.keySet()}">

									<table id="students">
										<tr>
											<th>Mã nhóm</th>
											<th>Tên thành viên</th>
											<th>Mã số sinh viên</th>
											<th>Vai trò</th>
										</tr>
										<c:forEach var="student" items="${groupStudentMap.get(id)}"
											varStatus="state">
											<c:choose>
												<c:when test="${state.first}">
													<tr>
														<td>${id}</td>
														<td>${student.person.fullName}</td>
														<td>${student.studentId}</td>
														<td></td>
													</tr>
												</c:when>
												<c:otherwise>
													<tr>
														<td></td>
														<td>${student.person.fullName}</td>
														<td>${student.studentId}</td>
														<td></td>
													</tr>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</table>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</div>
		</main>

		<!-- Modal -->
		<jsp:include page="./periodModal.jsp" />
		<jsp:include page="../partials/logoutModal.jsp" />
		<!-- Footer -->
		<jsp:include page="../partials/footer.jsp" />

		<%
		if (check != null) {
		%>
		<script type="text/javascript">
			$(document).ready(function() {
				$("#periodModal").modal('show');
			});
		</script>
		<%
		}
		%>
	</div>
</body>
</html>