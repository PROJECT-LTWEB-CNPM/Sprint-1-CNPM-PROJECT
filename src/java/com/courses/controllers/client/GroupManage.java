package com.courses.controllers.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.courses.models.GroupStudent;
import com.courses.models.Student;
import com.courses.services.GroupService;
import com.courses.services.RegisterGroupService;
import com.courses.services.admin.user.StudentService;


@WebServlet("/student/group-manage")
public class GroupManage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    public GroupManage() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String url = "/pages/client/student/groupManage.jsp";
		Student student = new Student();
		
		RegisterGroupService registerGroupService = new RegisterGroupService(request, response);
		StudentService studentService = new StudentService(request, response);
		GroupService groupService = new GroupService(request, response);
//		JoinGroupService joinGroupService = new JoinGroupService(request, response);
//		String studentId: Biến dùng để truyền thông tin của account đăng nhập(Lấy mã sinh viên hay mã định danh thay thế cho "ST00000002": thông tin được fix cứng)
		String studentId = studentService.getStudentByPersonToLoginData().getStudentId();
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("studentId", studentId);
		
		List<Student> students = new ArrayList<Student>();
		List<GroupStudent> groupStudents = new ArrayList<GroupStudent>();
//		List<JoinGroup> joinGroups = new ArrayList<JoinGroup>();
		
		students = studentService.checkStudentAndGroup(map);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	

}
