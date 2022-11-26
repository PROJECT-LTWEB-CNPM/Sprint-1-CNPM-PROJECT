package com.courses.services.admin.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.courses.dao.AdminDAO;
import com.courses.dao.StudentDAO;
import com.courses.dao.TeacherDAO;
import com.courses.models.Admin;
import com.courses.models.Person;
import com.courses.models.Student;
import com.courses.models.Teacher;
import com.courses.services.SuperService;
import com.courses.utils.constants.RoleConstants;

public class UserService extends SuperService {

	StudentDAO studentDAO = null;
	TeacherDAO teacherDAO = null;
	AdminDAO adminDAO = null;

	public UserService(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		this.studentDAO = new StudentDAO();
		this.teacherDAO = new TeacherDAO();
		this.adminDAO = new AdminDAO();
	}

	public void handleGetListUser() throws ServletException, IOException {
		String pageUrl = "/pages/admin/user/user.jsp";
		String userType = this.request.getParameter("type");
		try {
			switch (userType) {
			case RoleConstants.ADMIN:
				List<Admin> admins = this.adminDAO.findAll();
				this.request.setAttribute("users", admins);
				break;
			case RoleConstants.TEACHER:
				List<Teacher> teachers = this.teacherDAO.findAll();
				this.request.setAttribute("users", teachers);
				break;
			default:
				List<Student> s2 = this.studentDAO.findAll();
				this.request.setAttribute("users", s2);
				break;
			}

		} catch (Exception e) {
			pageUrl = "/pages/500.jsp";
		}
		this.request.setAttribute("type", userType);
		this.request.getRequestDispatcher(pageUrl).forward(request, response);
	}

	public void handleGetEditUserForm() throws ServletException, IOException {
		String pageUrl = "";
		// Get Params
		String userType = this.request.getParameter("type");
		String id = this.request.getParameter("id");
		try {

			pageUrl = "/pages/admin/user/" + userType + "/editUser.jsp";
			switch (userType) {
			case RoleConstants.ADMIN:
				Admin admin = this.adminDAO.find(id);
				this.request.setAttribute("user", admin);
				break;

			case RoleConstants.TEACHER:
				Teacher teacher = this.teacherDAO.find(id);
				this.request.setAttribute("user", teacher);
				break;

			case RoleConstants.STUDENT:
				Student student = this.studentDAO.find(id);
				this.request.setAttribute("user", student);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			pageUrl = "/pages/500.jsp";
		}
		this.request.setAttribute("type", userType);
		this.request.getRequestDispatcher(pageUrl).forward(request, response);
	}
	
	public void handleGetStudentInfo() throws ServletException, IOException {
		String url = "/pages/client/student/studentInfo.jsp";
		HttpSession session = this.request.getSession();
		String username = (String) session.getAttribute("username");
		System.out.println("===================="+ username +"=========================");
		PersonService personService = new PersonService(request, response);
		Person person = new Person();
//		person = personService.getPersonByPersonId("PE00000002");
		person = personService.getPersonByEmail(username);
		this.request.setAttribute("person", person);
		this.request.getRequestDispatcher(url).forward(request, response);
	}
}
