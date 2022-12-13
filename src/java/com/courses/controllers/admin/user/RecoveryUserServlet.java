package com.courses.controllers.admin.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.dao.PersonDAO;
import com.courses.models.Person;

@WebServlet(urlPatterns = { "/admin/users/trash/recovery", "/admin/users/trash/recovery/" })
public class RecoveryUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	PersonDAO personDAO = null;

	public RecoveryUserServlet() {
		super();
		this.personDAO = new PersonDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String type = request.getParameter("type");
			String[] personIds = request.getParameterValues("personId");
			List<Person> persons = new ArrayList<>();
			Person person = null;
			byte isDeleted = (byte) 0;
			// Get item deleted
			if (personIds != null) {
				for (String id : personIds) {
					person = this.personDAO.find(id);
					person.setIsDeleted(isDeleted);
					persons.add(person);
				}
				// Update
				this.personDAO.bulkUpdate(persons);
			}
			// Redirect
			String context = request.getContextPath();
			
			response.sendRedirect(context + "/admin/users/?type=" + type);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			String context = request.getContextPath();
			response.sendRedirect(context + "/admin/500");
		}
	}

}
