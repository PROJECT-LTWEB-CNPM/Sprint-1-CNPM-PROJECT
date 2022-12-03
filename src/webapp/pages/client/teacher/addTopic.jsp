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
<title>Trang chủ - Đăng ký đề tài</title>
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
							<div class="topic_registration">
								<ion-icon name="pencil"></ion-icon>
								<h3>Thêm đề tài</h3>
							</div>
							<div class="mt-4 d-flex justify-content-center">
								<div class="col col-lg-6">
								<form class="">
										<input type="text" name="topicId" class="form-control"
											value="" hidden />
										<div class="mb-3">
											<label for="site-title" class="form-label">Đợt đăng
												ký </label> <input type="text" name="registrationPeriodName"
												class="form-control form-control-lg" value="RP0000001"
												disabled />
										</div>
										<div class="mb-3">
											<label for="site-title" class="form-label">Chuyên
												ngành </label> <input type="text" name="registrationPeriodName"
												class="form-control form-control-lg" value="CN0000001"
												disabled />
										</div>

										<div class="mb-3">
											<label for="site-title" class="form-label">Tên đề tài
											</label> <input type="text" name="topicName"
												class="form-control form-control-lg" />
										</div>

										<div class="mb-3">
											<label for="site-title" class="form-label">Số lượng
												thành viên </label> <input type="number" min="2" max="3"
												name="numberOfMember" class="form-control form-control-lg" />
										</div>
										<div class="mb-3">
											<label for="site-title" class="form-label">Descripton</label>
											<textarea class="form-control" name="google_analytics_code"
												rows="4"></textarea>
										</div>
										<div class="mb-3 d-flex flex-row-reverse">
											<button class="btn btn-primary btn-lg" type="submit">
												<i class="fas fa-check"></i> Thêm
											</button>
										</div>
								</form>
								
							</div>
								
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