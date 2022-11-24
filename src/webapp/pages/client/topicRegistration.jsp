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
		<main>
			<div class="container">
				<div class="grid">
					<div class="grid_row">
						<div class="grid_column_1">
							<jsp:include page="./sidebar.jsp" />
						</div>
						<div class="grid_column_3">
							<div class="topic_registration">
								<ion-icon name="pencil"></ion-icon>
								<h3>ĐĂNG KÝ ĐỀ TÀI</h3>
							</div>
							<div class="topic_registration-filter">
								<h3>Đề tài chưa được đăng ký</h3>
								<h3 class="topic_registration-filter-active">Đề tài đã được
									đăng ký</h3>
							</div>
							<div class="topic_registration-detail">
								<div class="group_topic_registration-to-manage">
									<table>
										<tr>
											<th width="40%">Tên đề tài</th>
											<th width="25%">Giảng viên hướng đẫn</th>
											<th width="25%">Số lượng thành viên</th>
											<th class="hide_element">Xem chi tiết</th>
										</tr>
									</table>
								</div>
								<c:forEach begin="0" end="10">
									<div class="group_topic_registration-to-manage">
										<table>
											<tr>
												<th width="40%" class="highlight_content">Hệ thống giám
													sát nhà dùng Raspberry Pi B+</th>
												<th width="25%">Đinh Công Đoan</th>
												<th width="25%">2</th>
												<th><a href="#" class="highlight_content">Đăng ký</a></th>
											</tr>
										</table>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
					<div class="grid_row">
						<div class="grid_column_1">
							<jsp:include page="./sidebar.jsp" />
						</div>
						<div class="grid_column_3">
							<div class="topic_registration">
								<ion-icon name="pencil"></ion-icon>
								<h3>ĐĂNG KÝ ĐỀ TÀI</h3>
							</div>
							<div class="topic_registration-filter">
								<h3>Đề tài chưa được đăng ký</h3>
								<h3 class="topic_registration-filter-active">Đề tài đã được
									đăng ký</h3>
							</div>
							<div class="topic_registration-detail">
								<div class="group_topic_registration-to-manage">
									<table>
										<tr>
											<th width="40%">Tên đề tài</th>
											<th width="25%">Giảng viên hướng đẫn</th>
											<th width="25%">Số lượng thành viên</th>
											<th class="hide_element">Xem chi tiết</th>
										</tr>
									</table>
								</div>
								<div class="group_topic_registration-to-manage">
									<table>
										<tr>
											<th width="40%" class="highlight_content">Hệ thống giám
												sát nhà dùng Raspberry Pi B+</th>
											<th width="25%">Đinh Công Đoan</th>
											<th width="25%">1/2</th>
											<th><a href="#" class="highlight_content">Đăng ký</a></th>
										</tr>
									</table>
								</div>
								<div class="group_topic_registration-to-manage">
									<table>
										<tr>
											<th width="40%" class="highlight_content">Hệ thống cảnh
												báo bằng phát hiện chuyển động trên kit nhúng Raspberry Pi</th>
											<th width="25%">Đinh Công Đoan</th>
											<th width="25%">2/3</th>
											<th><a href="#" class="highlight_content">Đăng ký</a></th>
										</tr>
									</table>
								</div>

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