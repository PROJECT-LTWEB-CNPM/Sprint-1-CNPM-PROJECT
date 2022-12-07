<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String context = request.getContextPath();
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
							<c:if test="${notifications.size() != 0}">
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
									<c:forEach var="item" items="${notifications}">
										<div class="group_topic_registration-to-manage">
											<table>
												<tr>
													<th width="30%" class="highlight_content bold_content">${item.getNotificationTitle()}</th>
													<th width="30%" class="bold_content">${item.getPerson1().getFullName()}</th>
													<th width="30%" class="bold_content">${item.getTime()}</th>
												</tr>
											</table>
										</div>
									</c:forEach>
								</div>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</main>
		<!-- Modal -->
		<jsp:include page="../partials/logoutModal.jsp"></jsp:include>
		<!-- Footer -->
		<jsp:include page="../partials/footer.jsp" />
	</div>
</body>
</html>
