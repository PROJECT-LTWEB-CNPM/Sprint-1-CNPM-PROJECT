package com.courses.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.courses.dao.PersonDAO;
import com.courses.models.Person;
import com.courses.models.Student;
import com.courses.models.Teacher;
import com.courses.services.StudentService;
import com.courses.services.TeacherService;


@WebFilter(urlPatterns = {"/student/*", "/teacher/*"})
public class AuthFilter extends HttpFilter implements Filter {
       
   
	private static final long serialVersionUID = 1L;

	public AuthFilter() {
        super(); 
    }

	
	public void destroy() {
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// cast object type 
				HttpServletRequest req = (HttpServletRequest) request;
				HttpServletResponse res = (HttpServletResponse) response;
				
				// get cookie is existing 
				boolean check = false;
				String personId = "";
				Cookie[] cookies = req.getCookies();
				if (cookies != null) {
					for (int i=0; i<cookies.length; i++ ) {
						if (cookies[i].getName().equals("userIdCookie")) {
							check = true;
							personId = cookies[i].getValue();
						}
					}
				}
				
				Person person = null;
				// check to forward
				if(check) {
					// create new session
					HttpSession session = req.getSession();
					// find user information
					PersonDAO personDAO = new PersonDAO();
					person = personDAO.find(Person.class, personId);
					// save user information to that session
					session.setAttribute("person", person);
					// find specified user then save information into the session
					if (person.getRole().equals("student")) {
						Student student = null;
						student = StudentService.getStudentByPerson(person);
						session.setAttribute("student", student);
					}else if (person.getRole().equals("teacher")){
						Teacher teacher = null;
						teacher = TeacherService.getTeacherByPerson(person);
						session.setAttribute("teacher", teacher);
					}
					// action on goal page
					chain.doFilter(request, response);
				}else {
					// destroy session
					HttpSession session = req.getSession(false);
					if (session != null) {
						session.invalidate();
					}
					// forward to login page
					String url = "/login";
					res.sendRedirect(req.getContextPath() + url);
				}
		
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
