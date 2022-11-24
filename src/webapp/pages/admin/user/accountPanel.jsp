<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="tab-pane fade show" id="account" role="tabpanel"
	aria-labelledby="account-tab">
	<div class="col-md-6">
		<div class="mb-3">
			<label for="site-title" class="form-label">Username</label> <input
				type="text" name="site_title" class="form-control">
		</div>
		<div class="mb-3">
			<label for="site-title" class="form-label">New password</label> <input
				type="text" name="site_title" class="form-control" />
		</div>
		<div class="mb-3">
			<label for="site-title" class="form-label">Confirm password</label> <input
				type="text" name="site_title" class="form-control" />
		</div>
		<div class="mb-3">
			<label for="site-title" class="form-label">Descripton</label>
			<textarea class="form-control" name="google_analytics_code" rows="4"></textarea>
		</div>
		<jsp:include page="./navPanelBottom.jsp"></jsp:include>
	</div>
</div>