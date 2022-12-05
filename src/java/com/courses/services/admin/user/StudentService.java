package com.courses.services.admin.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.courses.dao.StudentDAO;
import com.courses.models.Person;
import com.courses.models.Student;
import com.courses.services.SuperService;

public class StudentService extends SuperService {

	StudentDAO studentDAO = null;

	public StudentService(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		this.studentDAO = new StudentDAO();
	}
	
	public Student getStudentByPersonToLoginData() {
		Map<String, Object> map = new HashMap<String, Object>();
		
		Student student = new Student();
		Person person = new Person();
		
		PersonService personService = new PersonService(request, response);
		
		StudentDAO studentDAO = new StudentDAO();
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		person = personService.getPersonByEmail(username);
		
		map.put("personId", person.getPersonId());
		student = studentDAO.findWithNamedQuery("Student.getStudentByPersonId", map).get(0);
		return student;
	}
	
	//	Kiểm tra xem người dùng vừa mới đăng nhập vào có phải là sinh viên và đồng thời chưa có nhóm hay không
	public List<Student> checkStudentAndGroup(Map<String,Object> map) {
		List<Student> students = this.studentDAO.findWithNamedQuery("Student.checkStudentAndGroup", map);
		return students;
	}
	
	//	Lấy thông tin của học sinh dựa vào id(Có thể lấy được cả nhóm trưởng và khoogn có nhóm trưởng)
	public Student getStudentByStudentId(String studentId) {
		Student student = new Student();
		//		Sex thay thế "ST00000002" bằng studentId sau khi đã có dữ liệu từ login trả về.
		student = this.studentDAO.find(studentId);
		return student;
	}

}
