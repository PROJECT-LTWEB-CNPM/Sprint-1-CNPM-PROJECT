<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String context = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="./partials/head.jsp" />
<title>Trang chủ - Đăng ký đề tài</title>
</head>
<body>
	<div id="root">
		<!-- Header -->
		<jsp:include page="./partials/header.jsp" />
		<!-- Body -->
		<main id="main">
			<div class="container">
				<div class="grid">
					<div class="grid_row">
						<div class="grid_column_1">
							<jsp:include page="./sidebar.jsp" />
						</div>
						<div class="grid_column_3">
							<div class="topic_registration">
								<ion-icon name="home-outline"></ion-icon>
								<h3>TRANG CỦA BẠN</h3>
							</div>
							<!-- avai -->
							<div class="topic_registration-detail">
								<div class="group_topic_registration-to-manage">
									<table>
										<tr>
											<th width="30%">Tiêu đề</th>
											<th width="30%">Người gửi</th>
											<th width="30%">Ngày gửi</th>
										</tr>
									</table>
								</div>
								<c:forEach begin="0" end="10">
									<div class="group_topic_registration-to-manage">
										<table>
											<tr>
												<th width="30%" class="highlight_content bold_content">Thông
													báo đăng ký chuyên ngành khóa 2018</th>
												<th width="30%" class="bold_content">QLSV_Đặng Hữu
													Khanh</th>
												<th width="30%" class="bold_content">26-09-2022
													15:21:00</th>
											</tr>
										</table>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</div>
		</main>
		<!-- Footer -->
		<jsp:include page="./partials/footer.jsp" />
	</div>
</body>
</html>
