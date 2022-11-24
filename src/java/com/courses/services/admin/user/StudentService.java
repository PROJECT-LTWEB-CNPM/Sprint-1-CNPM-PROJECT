package com.courses.services.admin.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.dao.StudentDAO;
import com.courses.services.SuperService;

public class StudentService extends SuperService {

	StudentDAO studentDAO = null;

	public StudentService(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		this.studentDAO = new StudentDAO();
	}

}
