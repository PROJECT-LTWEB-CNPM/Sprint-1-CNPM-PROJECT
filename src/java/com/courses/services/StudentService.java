package com.courses.services;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.dao.StudentDAO;
import com.courses.models.Person;
import com.courses.models.Student;


public class StudentService extends SuperService{
	private static StudentDAO studentDAO = new StudentDAO();

	public StudentService(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
	
	public static Student getStudentByPerson(Person person) {
		Student foundStudent = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("person", person);
		if (studentDAO.findWithNamedQuery("Student.findStudentByPerson", map).size() >0 )
		{
			foundStudent = studentDAO.findWithNamedQuery("Student.findStudentByPerson", map).get(0);
		}
		return foundStudent;
	}

}
