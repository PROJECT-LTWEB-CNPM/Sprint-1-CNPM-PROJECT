<%@page import="com.courses.utils.constants.GenderConstants"%>
<%@page import="com.courses.utils.constants.RoleConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String type = (String) request.getAttribute("type");
String title = "";
%>

<div class="modal fade" id="modalCreateRP" tabindex="-1"
	aria-labelledby="exampleModalLabel" aria-hidden="true"
	data-bs-backdrop="static">
	<div class="modal-dialog modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="modalCreateRP">
					<i class="fas fa-clock"></i> Create Schedule
				</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="col-md-12">
					<div class="mb-3">
						<label for="site-title" class="form-label">Person ID</label> <input
							type="text" name="site_title" class="form-control" disabled>
					</div>
					<div class="mb-3">
						<label for="site-title" class="form-label">Full name</label> <input
							type="text" name="site_title" class="form-control" />
					</div>
					<div class="mb-3">
						<label for="site-title" class="form-label">Address</label> <input
							type="text" name="site_title" class="form-control" />
					</div>
					<div class="mb-3">
						<label for="site-description" class="form-label">gender</label> <select
							name="timezone" class="form-select">
							<option value="<%=GenderConstants.MALE%>">Male</option>
							<option value="<%=GenderConstants.FEMALE%>">Female</option>
						</select>
					</div>
					<div class="mb-3">
						<label for="site-title" class="form-label">Phonenumber</label> <input
							type="text" name="site_title" class="form-control" />
					</div>
					<div class="mb-3">
						<label for="site-description" class="form-label">Role</label> <select
							name="timezone" class="form-select">
							<option value="${type}"><%=title %></option>

						</select>
					</div>
					<div class="mb-3">
						<label for="site-title" class="form-label">Descripton</label>
						<textarea class="form-control" name="google_analytics_code"
							rows="4"></textarea>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary"
					data-bs-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary">Save changes</button>
			</div>
		</div>
	</div>
</div>