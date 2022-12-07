package com.courses.services;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.dao.RegistrationPeriodDAO;
import com.courses.models.RegistrationPeriod;
import com.courses.utils.constants.RoleConstants;

public class RegistrationPriodService extends SuperService {

	RegistrationPeriodDAO registrationPeriodDAO = null;

	public RegistrationPriodService(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		this.registrationPeriodDAO = new RegistrationPeriodDAO();
	}
	
	public RegistrationPriodService() {}

	public void handleGetList() throws ServletException, IOException {
		String pageUrl = "/pages/admin/registrationPriod/registrationPriod.jsp";
		try {
			String type = this.request.getParameter("type");
			byte isForTeacher = 1;
			if (type.equals(RoleConstants.STUDENT)) {
				isForTeacher = 0;
			}
			List<RegistrationPeriod> registrationPeriods = this.registrationPeriodDAO
					.findByIsRegistrationTeacher(isForTeacher);
			this.request.setAttribute("type", type);
			this.request.setAttribute("registrationPeriods", registrationPeriods);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			pageUrl = "/pages/500.jsp";
		}
		this.request.getRequestDispatcher(pageUrl).forward(request, response);
	}

	public void handleGetEditForm() throws ServletException, IOException {
		String pageUrl = "/pages/admin/registrationPriod/editRegistrationPriod.jsp";
		String id = this.request.getParameter("id");
		try {
			String type = this.request.getParameter("type");
			this.request.setAttribute("type", type);
			RegistrationPeriod rp = this.registrationPeriodDAO.find(id);
			this.request.setAttribute("rp", rp);
			System.out.println(rp.getCloseDate());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			pageUrl = "/pages/500.jsp";
		}
		this.request.getRequestDispatcher(pageUrl).forward(request, response);
	}
	
	public RegistrationPeriod getRegistrationPeriod (Byte isActive) {
		RegistrationPeriod period = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("isActive", isActive);
		if (this.registrationPeriodDAO.findWithNamedQuery("RegistrationPeriod.findByStatus", map).size() > 0) {
			period = this.registrationPeriodDAO.findWithNamedQuery("RegistrationPeriod.findByStatus", map).get(0);
		}
		return period;
	}
}
