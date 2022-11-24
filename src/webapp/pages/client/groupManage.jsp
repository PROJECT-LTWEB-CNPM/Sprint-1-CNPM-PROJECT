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
							<div class="topic_info_contaniner">
								<div class="topic_info_contaniner-item">
									<label>Tên đề tài:</label>
									<h3>${student.getGroupstudent().getTopic().getTopicName()}</h3>
								</div>
								<div class="topic_info_contaniner-item">
									<label>Giảng viên hướng dẫn:</label>
									<h3>${student.getGroupstudent().getTopic().getTeacher().getPerson().getFullName()}</h3>
								</div>
								<div class="topic_info_contaniner-item">
									<label>Số lượng sinh viên:</label>
									<h3>${student.getGroupstudent().getTopic().getMaxMoMember()}</h3>
								</div>
							</div>
							<div class="topic_registration-filter">
								<h3 class="topic_registration-filter-active">
									<a href="<%=context%>/group-manage/create-group">Tạo nhóm</a>
								</h3>
								<h3 class="topic_registration-filter-active">
									<a href="<%=context%>/group-manage/add-memeber">Thêm thành viên</a>
								</h3>
							</div> 

							<!-- when: Nếu chưa có nhóm hiển thị giao diện chưa có nhóm -->
							<!-- otherwise: Đã có nhóm hiển thị danh sách nhóm -->
							<c:choose>
								<c:when test="${uiGroupManage == null}">
									<p class="topic_registration-notification highlight_content">Bạn
										chưa có nhóm nào</p>
								</c:when>
								<c:otherwise>
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
										<c:forEach var="item" items="${students}">
											<div class="group_topic_registration-to-manage">
												<table>
													<tr>
														<th width="20%" class="highlight_content">${item.getStudentId()}</th>
														<th width="45%">${item.getPerson().getFullName()}</th>
														<th width="25%">${item.getStudentId() == 'ST00000002' ? 'Trưởng Nhóm' : 'Thành Viên'}</th>
														<th><a href="#" class="hide_element">Đăng ký</a></th>
													</tr>
												</table>
											</div>
										</c:forEach>
									</div>
								</c:otherwise>
							</c:choose>
							<!-- 
							<p class="topic_registration-notification highlight_content">Bạn
								chưa có nhóm nào</p>							
							 -->
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