<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="tab-pane fade active show" id="general" role="tabpanel"
	aria-labelledby="general-tab">
	<div class="col-md-6">
		<form method="POST" action="update-period">
			<input type="text" name="registrationPeriodId" class="form-control"
				hidden /> <input type="text" name="registrationPeriodId"
				class="form-control" hidden />
			<div class="mb-3">
				<label for="site-title" class="form-label">Name</label> <input
					type="text" name="site_title" class="form-control"
					value="${rp.getRegistrationPeriodName()}" />
			</div>
			<div class="mb-3">
				<label for="site-title" class="form-label">Semester</label> <input
					type="text" name="site_title" class="form-control"
					value="${rp.getSemeter()}" />
			</div>
			<div class="mb-3">
				<label for="site-description" class="form-label">School Year</label>
				<input type="text" name="schoolYear" class="form-control date-own" />
			</div>
			<div class="mb-3">
				<label for="site-title" class="form-label">Open Date</label> <input
					type="text" name="openDate" class="form-control datetime" />
			</div>
			<div class="mb-3">
				<label for="site-description" class="form-label">Close Date</label>
				<input type="text" name="closeDate" class="form-control datetime" />
			</div>
			<div class="mb-3">
				<label for="site-title" class="form-label">Description</label>
				<textarea class="form-control" name="google_analytics_code" rows="4"></textarea>
			</div>
			<jsp:include page="./navPanelBottom.jsp"></jsp:include>
		</form>
	</div>
</div>