package com.courses.services;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.dao.RegistrationPeriodDAO;
import com.courses.models.RegistrationPeriod;

public class RegistrationPriodService extends SuperService {

	RegistrationPeriodDAO registrationPeriodDAO = null;

	public RegistrationPriodService(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		this.registrationPeriodDAO = new RegistrationPeriodDAO();
	}

	public void handleGetList() throws ServletException, IOException {
		String pageUrl = "/pages/admin/registrationPriod/registrationPriod.jsp";
		try {
			String type = this.request.getParameter("teacher");
			byte isForTeacher = 1;
			List<RegistrationPeriod> registrationPeriods = this.registrationPeriodDAO
					.findByIsRegistrationTeacher(isForTeacher);
			this.request.setAttribute("registrationPeriods", registrationPeriods);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			pageUrl = "/pages/500.jsp";
		}
		this.request.getRequestDispatcher(pageUrl).forward(request, response);
	}

}
