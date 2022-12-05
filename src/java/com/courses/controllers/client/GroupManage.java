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
//import com.courses.models.JoinGroup; => Day ne
import com.courses.models.Student;
import com.courses.services.GroupService;
//import com.courses.services.JoinGroupService; => Day ne
import com.courses.services.RegisterGroupService;
import com.courses.services.admin.user.StudentService;

@WebServlet(urlPatterns = { "/student/group-manage", "/student/group-manage/create-group", "/student/group-manage/add-memeber", "/student/group-manage/delete-memeber" })
public class GroupManage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GroupManage() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String url = "/pages/client/student/groupManage.jsp";
		Student student = new Student();
		
		RegisterGroupService registerGroupService = new RegisterGroupService(request, response);
		StudentService studentService = new StudentService(request, response);
		GroupService groupService = new GroupService(request, response);
		//JoinGroupService joinGroupService = new JoinGroupService(request, response); => Day ne
//		String studentId: Biến dùng để truyền thông tin của account đăng nhập(Lấy mã sinh viên hay mã định danh thay thế cho "ST00000002": thông tin được fix cứng)
		String studentId = studentService.getStudentByPersonToLoginData().getStudentId();
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("studentId", studentId);
		
		List<Student> students = new ArrayList<Student>();
		List<GroupStudent> groupStudents = new ArrayList<GroupStudent>();
		//List<JoinGroup> joinGroups = new ArrayList<JoinGroup>(); => Day ne
		
		students = studentService.checkStudentAndGroup(map);
		
		
		if (request.getRequestURI().contains("create-group")) {
			registerGroupService.createGroupStudent(studentId);
		} else if (request.getRequestURI().contains("add-memeber")) {
			 url = "/pages/client/student/addMemberForm.jsp";
			 request.getRequestDispatcher(url).forward(request, response);
		} else if (request.getRequestURI().contains("delete-memeber")) {
			//studentService.deleteStudenFromStudentGroup(); => Đây nè
		} else {
//			Nếu students.size() == 0 --> sinh viên đó đã có nhóm
			if(students.size() == 0) {
//			Khi có dữ liệu trả về từ login thì thay cho "ST00000002"
				students = studentService.getListStudentTheSameGroup(studentId);
				student = studentService.getStudentByStudentId(studentId);
				request.setAttribute("uiGroupManage", "NOT NULL");
				request.setAttribute("students", students);
				request.setAttribute("student", student);
			}
			//joinGroups = joinGroupService.getRequestJoinGroup(studentId);
			groupStudents = groupService.getGroupStudent();
			request.setAttribute("groupStudents", groupStudents);
			//session.setAttribute("joinGroups", joinGroups); => Đây nè
			request.getRequestDispatcher(url).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
