package com.courses.controllers.admin.registrationPriod;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.services.RegistrationPriodService;

@WebServlet(urlPatterns = { "/admin/registration-priods/edit/update-period",
		"/admin/registration-priods/edit/update-period/" })
public class UpdateRegistrationPriodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateRegistrationPriodServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Hello world");
		String openDate = request.getParameter("openDate");
		String schoolYear = request.getParameter("schoolYear");
		System.out.println(openDate);
		System.out.println(schoolYear);
	}

}