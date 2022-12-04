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

import com.courses.dao.GroupStudentDAO;

import com.courses.dao.StudentDAO;
import com.courses.models.GroupStudent;


import com.courses.models.Student;
import com.courses.services.GroupService;

import com.courses.services.admin.user.StudentService;

/**
 * Servlet implementation class AddMemberToGroup
 */
@WebServlet(urlPatterns = {"/add-member-to-group"})
public class AddMemberToGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public AddMemberToGroup() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Kiểm tra xem người yêu cầu thêm thành viên có phải là nhóm trưởng hay không
//		Kiểm tra mã sinh viên vừa nhập vào đã có nhóm hay chưa. Nếu chưa mới cho thêm vào nhóm
//		Kiểm tra tính hợp lệ của id được nhập vào.
		String url = "/student/group-manage";
		GroupService groupService = new GroupService(request, response);
		StudentService studentService = new StudentService(request, response);
//		JoinGroupService joinGroupService = new JoinGroupService(request, response);
		
		StudentDAO studentDAO = new StudentDAO(); 
		GroupStudentDAO groupStudentDAO = new GroupStudentDAO();
		
		Student student = new Student();
		GroupStudent groupStudent = new GroupStudent();

		
		List<GroupStudent> groupStudents = new ArrayList<GroupStudent>();
		List<Student> students = new ArrayList<Student>();
// Lấy dữ đối tượng đăng nhập vào thôgn qua login	
		String leaderId = studentService.getStudentByPersonToLoginData().getStudentId();
		
		Map<String, Object> map = new HashMap<String, Object>();
//		Kiểm tra xe người đăng nhập vào có phải là trưởng nhóm hay ko
//		"ST00000002": sẽ được lấy từ login trả về
		map.put("leaderId", leaderId);
		groupStudents = groupService.checkLeader(map);
		
		String student_id = request.getParameter("studentId");
		System.out.println("==============="+ student_id +"===============");
		map.clear();
		map.put("studentId", student_id);
		students = studentService.checkStudentAndGroup(map);
//		Kiểm tra xe người đăng nhập vào có phải là trưởng nhóm hay ko và có phải sinh viên hay không
		if(groupStudents.size() > 0 && students.size() > 0) {
			request.setAttribute("message", "Cần chọn đề tài trước khi thêm thành viên vào nhóm");
		} else {
//			Nếu sinh viên đăng nhập là nhóm trưởng thì mới có quyền thêm sinh viên mới vào nhóm.
			groupStudents = groupService.checkRole(leaderId);
			if (groupStudents.size() > 0) {
				student = students.get(0);
				groupStudent = groupStudents.get(0);
				if (groupStudent.getTopic().getMaxMoMember() > groupStudent.getCurrentNumber()) {
					groupStudent.setCurrentNumber(groupStudent.getCurrentNumber() + 1);
					student.setGroupstudent(groupStudents.get(0));
					groupStudentDAO.update(groupStudent);
					studentDAO.update(student);	
//			Xóa 1 sinh viên ra khỏi hàng chò xin vào nhóm khi xin viên đó được thêm vào nhóm nào đó
//					joinGroupService.deleteRequestJoinGroup(student.getStudentId(), groupStudent.getGroupId());
//					joinGroupService.deleteAllRequestJoinGroupsRelatedToStudentId(student_id);
					request.setAttribute("message", "Thêm thành viên thành công");
					
				} else {
					request.setAttribute("message", "Số lượng thành viên của nhóm vượt quá số lượng thành viên cho phép của đề tài");
				}
			} else {
				request.setAttribute("message", "Chỉ có trưởng nhóm mới được thêm thành viên");
			}
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
