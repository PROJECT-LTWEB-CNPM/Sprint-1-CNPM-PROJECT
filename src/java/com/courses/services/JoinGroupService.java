package com.courses.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JoinGroupService extends SuperService{
	public JoinGroupService(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
	
	public void deleteAllRequestJoinGroupsRelatedToStudentId(String studentId) {
		
	}
}
