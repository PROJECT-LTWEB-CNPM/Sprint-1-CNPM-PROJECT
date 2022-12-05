package com.courses.services.admin.user;

import java.util.ArrayList;
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
	
//	Lấy danh sách các sinh viên trong cùng 1 group
	public List<Student> getStudentTheSameGroupByGroupId(Map<String,Object> map){
		List<Student> students = this.studentDAO.findWithNamedQuery("Student.getStudentTheSameGroupByGroupId", map);
		return students;
	}
	
//	Đầu tiên dùng id của người đăng nhập ST00000002 để lấy ra group_id = GS00000005(Student)
//	Sau đó dùng group_id = GS00000005 lấy ra DANH SÁCH các person_id(Student) có group_id(Student) = GS00000005  vừa tìm được
//	Duyệt qua DANH SÁCH các person_id(Student) để lấy ra danh sách các person(Person)
	public List<Student> getListStudentTheSameGroup(String studentId) {
		Student student = new Student();
		StudentService studentService = new StudentService(request, response);
		Map<String, Object> map = new HashMap<String, Object>();
		List<Student> students = new ArrayList<Student>();
		
		student = studentService.getStudentByStudentId(studentId);
		String groupId = student.getGroupstudent().getGroupId();
		map.put("groupId", groupId);
		students = studentService.getStudentTheSameGroupByGroupId(map);
		return students;
	}

}
