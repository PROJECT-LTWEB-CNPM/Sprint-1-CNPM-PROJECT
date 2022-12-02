package com.courses.controllers.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/group-manage")
public class GroupManage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    public GroupManage() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/pages/client/student/groupManage.jsp";
		Student student = new Student();
		
		RegisterGroupService registerGroupService = new RegisterGroupService(request, response);
		StudentService studentService = new StudentService(request, response);
		GroupService groupService = new GroupService(request, response);
//		String studentId: Biến dùng để truyền thông tin của account đăng nhập(Lấy mã sinh viên hay mã định danh thay thế cho "ST00000002": thông tin được fix cứng)
		String studentId = studentService.getStudentByPersonToLoginData().getStudentId();
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("studentId", studentId);
		
		List<Student> students = new ArrayList<Student>();
		List<GroupStudent> groupStudents = new ArrayList<GroupStudent>();
		
		students = studentService.checkStudentAndGroup(map);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
