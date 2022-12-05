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
							<h1>TÊN ĐỀ TÀI</h1>
							<div class="topic-group">
								<ion-icon name="receipt-outline"></ion-icon>
								<h3>NHÓM SINH VIÊN ĐĂNG KÝ</h3>
							</div>
							<div class="student-table">
								<table id="students">
									<tr>
										<th>Mã nhóm</th>
										<th>Tên thành viên</th>
										<th>Mã số sinh viên</th>
										<th>Vai trò</th>
									</tr>
									<tr>
										<td>GR000001</td>
										<td>Đỗ Dương Thái Tuấn</td>
										<td>2011xxxx</td>
										<td>Nhóm trưởng</td>
									</tr>
									<tr>
										<td></td>
										<td>Phạm Nguyễn Nhựt Trường</td>
										<td>2011yyyy</td>
										<td>Thành viên</td>
									</tr>
									<tr>
										<td></td>
										<td>Bùi Thanh Duy</td>
										<td>20110623</td>
										<td>Thành viên</td>
									</tr>
								</table>

								<div class="student-table">
									<table id="students">
										<tr>
											<th>Mã nhóm</th>
											<th>Tên thành viên</th>
											<th>Mã số sinh viên</th>
											<th>Vai trò</th>
										</tr>
										<tr>
											<td>GR000002</td>
											<td>Đỗ Dương Thái Tuấn</td>
											<td>2011xxxx</td>
											<td>Nhóm trưởng</td>
										</tr>
										<tr>
											<td></td>
											<td>Phạm Nguyễn Nhựt Trường</td>
											<td>2011yyyy</td>
											<td>Thành viên</td>
										</tr>
										<tr>
											<td></td>
											<td>Bùi Thanh Duy</td>
											<td>20110623</td>
											<td>Thành viên</td>
										</tr>
									</table>

									<div class="student-table">
										<table id="students">
											<tr>
												<th>Mã nhóm</th>
												<th>Tên thành viên</th>
												<th>Mã số sinh viên</th>
												<th>Vai trò</th>
											</tr>
											<tr>
												<td>GR000003</td>
												<td>Đỗ Dương Thái Tuấn</td>
												<td>2011xxxx</td>
												<td>Nhóm trưởng</td>
											</tr>
											<tr>
												<td></td>
												<td>Phạm Nguyễn Nhựt Trường</td>
												<td>2011yyyy</td>
												<td>Thành viên</td>
											</tr>
											<tr>
												<td></td>
												<td>Bùi Thanh Duy</td>
												<td>20110623</td>
												<td>Thành viên</td>
											</tr>
										</table>

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