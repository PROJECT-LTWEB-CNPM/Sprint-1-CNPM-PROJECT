package com.courses.services.admin.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.dao.PersonDAO;
import com.courses.models.Person;
import com.courses.services.SuperService;

public class PersonService extends SuperService {
	PersonDAO personDAO = null;

	public PersonService(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		this.personDAO = new PersonDAO();
	}

	public void handlePostUpdatePerson() throws IOException, ServletException {
		this.request.setCharacterEncoding("UTF-8");
		this.response.setCharacterEncoding("UTF-8");
		String type = this.request.getParameter("type");
		String pageUrl = this.request.getContextPath() + "/admin/users/?type=" + type;
		try {
			String personId = this.request.getParameter("personId");
			String fullname = this.request.getParameter("fullname");
			String address = this.request.getParameter("address");
			String phonenumber = this.request.getParameter("phonenumber");
			String descripton = this.request.getParameter("descripton");
			byte genderByte = Byte.parseByte(this.request.getParameter("gender"));

			Person curPerson = this.personDAO.find(personId);
			if (curPerson != null) {
				curPerson.setFullName(fullname);
				curPerson.setAddress(address);
				curPerson.setPhonenumber(phonenumber);
				curPerson.setDescription(descripton);
				curPerson.setGender(genderByte);
			}
			this.personDAO.update(curPerson);
		} catch (Exception e) {
			pageUrl = "/pages/500.jsp";
			this.request.getRequestDispatcher(pageUrl).forward(request, response);
			return;
		}
		this.response.sendRedirect(pageUrl);
	}
}
