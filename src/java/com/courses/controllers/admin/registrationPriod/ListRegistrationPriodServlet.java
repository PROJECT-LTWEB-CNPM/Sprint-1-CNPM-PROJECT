package com.courses.controllers.admin.registrationPriod;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.dao.RegistrationPeriodDAO;
import com.courses.services.RegistrationPriodService;

@WebServlet(urlPatterns = {"/admin/registration-priods"})
public class ListRegistrationPriodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListRegistrationPriodServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RegistrationPriodService registrationPriodService = new RegistrationPriodService(request, response);
		registrationPriodService.handleGetList();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
