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

			<!-- Body -->
			<div class="container">
				<div class="grid">
					<div class="grid_row">
						<div class="grid_column_1">
							<jsp:include page="./sidebar.jsp" />
						</div>
						<div class="grid_column_3">
							<div class="topic_registration">
								<ion-icon name="people-outline"></ion-icon>
								<h3>QUẢN LÝ NHÓM</h3>
							</div>
							 <div class="topic_registration-filter">
								<h3 class="topic_registration-filter-active">tạo nhóm</h3>
								<h3 class="topic_registration-filter-active">Them thanh vien</h3>
								<h3 class="topic_registration-filter-active">tạo nhóm</h3>
							</div> 
							<p class="topic_registration-notification highlight_content">Bạn
								chưa có nhóm nào</p>
						</div>
					</div>
					<div class="grid_row">
						<div class="grid_column_1">
							<ul class="category-list">
								<li class="category-item"><ion-icon name="home-outline"></ion-icon>
									<a href="/users/home" class="category-item__link">Trang của
										bạn</a></li>
								<li class="category-item"><ion-icon name="person-outline"></ion-icon>
									<a href="" class="category-item__link">Thông tin sinh viên</a>
								</li>
								<li class="category-item"><ion-icon name="pencil"></ion-icon>
									<a href="/users/signup" class="category-item__link">Đăng ký
										đề tài</a></li>
								<li class="category-item"><ion-icon name="people-outline"></ion-icon>
									<a href="/users/manage-group" class="category-item__link">Quản
										lý nhóm</a></li>
								<li class="category-item"><ion-icon name="log-out-outline"></ion-icon>
									<a href="/" class="category-item__link">Đăng xuất</a></li>
							</ul>
						</div>
						<div class="grid_column_3">
							<div class="topic_registration">
								<ion-icon name="people-outline"></ion-icon>
								<h3>QUẢN LÝ NHÓM</h3>
							</div>
							<div class="topic_registration-filter">
								<h3 class="topic_registration-filter-active">Danh sách sinh
									viên xin vào nhóm</h3>
							</div>
							<div class="topic_info_contaniner">
								<div class="topic_info_contaniner-item">
									<label>Tên đề tài:</label>
									<h3>Máy phát nhạc MP3 trên nền kit nhúng</h3>
								</div>
								<div class="topic_info_contaniner-item">
									<label>Giảng viên hướng dẫn:</label>
									<h3>Đinh Công Đoan</h3>
								</div>
								<div class="topic_info_contaniner-item">
									<label>Số lượng sinh viên </label>
									<h3>2</h3>
								</div>
							</div>
							<div class="topic_registration-detail">
								<div class="group_topic_registration-to-manage">
									<table>
										<tr>
											<th width="20%">Mã sinh viên</th>
											<th width="45%">Tên sinh viên</th>
											<th width="25%">Chức vụ</th>
											<th class="hide_element">Xem chi tiết</th>
										</tr>
									</table>
								</div>
								<div class="group_topic_registration-to-manage">
									<table>
										<tr>
											<th width="20%" class="highlight_content">20110743</th>
											<th width="45%">Đỗ Dương Thái Tuấn</th>
											<th width="25%">Trưởng nhóm</th>
											<th><a href="#" class="hide_element">Đăng ký</a></th>
										</tr>
									</table>
								</div>
								<div class="group_topic_registration-to-manage">
									<table>
										<tr>
											<th width="20%" class="highlight_content">20110756</th>
											<th width="45%">Phạm Nguyễn Nhựt Trường</th>
											<th width="25%">Thành viên</th>
											<th><a href="#" class="highlight_content">Chi tiết</a></th>
										</tr>
									</table>
								</div>

							</div>
						</div>
					</div>
					<div class="grid_row">
						<div class="grid_column_1">
							<ul class="category-list">
								<li class="category-item"><ion-icon name="home-outline"></ion-icon>
									<a href="" class="category-item__link">Trang của bạn</a></li>
								<li class="category-item"><ion-icon name="person-outline"></ion-icon>
									<a href="" class="category-item__link">Thông tin sinh viên</a>
								</li>
								<li class="category-item"><ion-icon name="pencil"></ion-icon>
									<a href="" class="category-item__link">Đăng ký đề tài</a></li>
								<li class="category-item"><ion-icon name="people-outline"></ion-icon>
									<a href="" class="category-item__link">Quản lý nhóm</a></li>
								<li class="category-item"><ion-icon name="log-out-outline"></ion-icon>
									<a href="" class="category-item__link">Đăng xuất</a></li>
							</ul>
						</div>
						<div class="grid_column_3">
							<div class="topic_registration">
								<ion-icon name="people-outline"></ion-icon>
								<h3>QUẢN LÝ NHÓM</h3>
							</div>
							<div class="topic_registration-filter">
								<h3 class="topic_registration-filter-active">Danh sách sinh
									viên xin vào nhóm</h3>
							</div>
							<div class="topic_info_contaniner">
								<div class="topic_info_contaniner-item">
									<label>Tên đề tài:</label>
									<h3>Máy phát nhạc MP3 trên nền kit nhúng</h3>
								</div>
								<div class="topic_info_contaniner-item">
									<label>Giảng viên hướng dẫn:</label>
									<h3>Đinh Công Đoan</h3>
								</div>
								<div class="topic_info_contaniner-item">
									<label>Số lượng sinh viên </label>
									<h3>2</h3>
								</div>
							</div>
							<div class="topic_registration-detail">
								<div class="group_topic_registration-to-manage">
									<table>
										<tr>
											<th width="20%">Mã sinh viên</th>
											<th width="45%">Tên sinh viên</th>
											<th width="25%">Chức vụ</th>
											<th class="hide_element">Xem chi tiết</th>
										</tr>
									</table>
								</div>
								<div class="group_topic_registration-to-manage">
									<table>
										<tr>
											<th width="20%" class="highlight_content">20110743</th>
											<th width="45%">Đỗ Dương Thái Tuấn</th>
											<th width="25%">Trưởng nhóm</th>
											<th><a href="#" class="hide_element">Đăng ký</a></th>
										</tr>
									</table>
								</div>
								<div class="group_topic_registration-to-manage">
									<table>
										<tr>
											<th width="20%" class="highlight_content">20110756</th>
											<th width="45%">Phạm Nguyễn Nhựt Trường</th>
											<th width="25%">Thành viên</th>
											<th><a href="#" class="highlight_content">Chi tiết</a></th>
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