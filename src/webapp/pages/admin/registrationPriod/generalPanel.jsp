<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="tab-pane fade active show" id="general" role="tabpanel"
	aria-labelledby="general-tab">
	<div class="col-md-6">
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
				<option value="">select your gender</option>
				<option value="">male</option>
				<option value="">female</option>
			</select>
		</div>
		<div class="mb-3">
			<label for="site-title" class="form-label">Phonenumber</label> <input
				type="text" name="site_title" class="form-control" />
		</div>
		<div class="mb-3">
			<label for="site-description" class="form-label">Role</label> <select
				name="timezone" class="form-select">
				<option value="">Student</option>
			</select>
		</div>
		<div class="mb-3">
			<label for="site-title" class="form-label">Descripton</label>
			<textarea class="form-control" name="google_analytics_code" rows="4"></textarea>
		</div>
		<jsp:include page="./navPanelBottom.jsp"></jsp:include>
	</div>
</div>