package com.courses.services;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.courses.dao.PersonDAO;
import com.courses.dao.TeacherDAO;
import com.courses.models.Person;
import com.courses.models.Teacher;

public class TeacherService extends SuperService implements UserService{

	TeacherDAO teacherDAO = new TeacherDAO();
	PersonDAO personDAO = new PersonDAO();
	
	public TeacherService(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public void getUserInformation() {
//		//define url
//		String url = "/pages/client/teacher/teacherInfor.jsp";
//		
//		// get saved user information in existing session
//		HttpSession session = this.request.getSession();
//		Person person = (Person)session.getAttribute("user");
//		
	}
	
	
	public void handleGetTeacher() throws ServletException, IOException{
		// define default url
		String url = "/pages/client/teacher/teacherInfor.jsp";
		
		// get person
		HttpSession session = this.request.getSession();
		Person person = (Person) session.getAttribute("user");
		
		// get teacher by person
		Teacher teacher = getTeacherByPerson(person);
		session.setAttribute("teacher", teacher);
		
		// forward to specified url
		super.forwardToPage(url);
	}
	
	
	public Teacher getTeacherByPerson(Person person) {
		Teacher foundTeacher = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("person", person);
		if (this.teacherDAO.findWithNamedQuery("Teacher.findTeacherByPerson", map).size() >0 )
		{
			foundTeacher = this.teacherDAO.findWithNamedQuery("Teacher.findTeacherByPerson", map).get(0);
		}
		return foundTeacher;
	}

}
