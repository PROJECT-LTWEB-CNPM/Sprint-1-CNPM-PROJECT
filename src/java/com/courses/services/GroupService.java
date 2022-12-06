package com.courses.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.dao.GroupStudentDAO;
import com.courses.dao.StudentDAO;
import com.courses.dao.TopicDAO;
import com.courses.models.GroupStudent;
import com.courses.models.Person;
import com.courses.models.Student;
import com.courses.models.Topic;
import com.courses.services.admin.user.PersonService;

public class GroupService extends SuperService {

	GroupStudentDAO groupDAO = null;

	public GroupService(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		this.groupDAO = new GroupStudentDAO();
	}
	
	public GroupService() {}
	
	public void handleGetListGroup() throws ServletException, IOException {
		try {
			String pageUrl = "/pages/admin/group/group.jsp";

			List<GroupStudent> groups = this.groupDAO.findAll();
			this.request.setAttribute("groups", groups);
			this.request.getRequestDispatcher(pageUrl).forward(request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			String errorUrl = "/pages/500.jsp";
			this.request.getRequestDispatcher(errorUrl).forward(request, response);
		}
	}

	public void handleGetCreateGroupForm() throws ServletException, IOException {
		try {
			String pageUrl = "/pages/admin/group/editGroup.jsp";
			this.request.getRequestDispatcher(pageUrl).forward(request, response);
		} catch (Exception e) {
			String errorUrl = "/pages/500.jsp";
			this.request.getRequestDispatcher(errorUrl).forward(request, response);
		}
	}

	public List<GroupStudent> checkLeader(Map<String, Object> map) {
		List<GroupStudent> groupStudents = new ArrayList<GroupStudent>();
		groupStudents = this.groupDAO.findWithNamedQuery("GroupStudent.checkLeader", map);
		return groupStudents;
	}

	public void choiceTopic(String studentId, Topic topic) {
//		cần phải kiểm tra sinh viên đó có nhóm hay chưa. Đã có nhóm mới cho tạo.
//		sinh viên đó phải là trưởng nhóm.
//		Lấy ra được nhóm cần group sinh viên cần chọn đề tài
		GroupService groupService = new GroupService(request, response);
		GroupStudent groupStudent = null;
		TopicDAO topicDAO = new TopicDAO();
		List<GroupStudent> groupStudents = new ArrayList<GroupStudent>();
		Map<String, Object> map = new HashMap<String, Object>();

//		Kiểm tra xe người đăng nhập vào có phải là trưởng nhóm hay ko
//		"ST00000002": sẽ được lấy từ login trả về
		map.put("leaderId", studentId);

		groupStudents = groupService.checkLeader(map);
// 		Kiểm tra xe người đăng nhập vào có phải là trưởng nhóm hay ko và có phải sinh viên hay không
		if (groupStudents.size() > 0 ) {
			topic.setIsSelected((byte)1);
			groupStudent = groupStudents.get(0);
//			Kiểm tra xem số lượng thành viên của nhóm sinh viên đó có lớn hơn số lượng ho phép hay không
			if (groupStudent.getCurrentNumber() <= topic.getMaxMoMember()) {
				groupStudent.setTopic(topic);
				this.groupDAO.update(groupStudent);
				topicDAO.update(topic);
			} else {
				this.request.setAttribute("message", "Số lượng thành viên của nhóm vượt quá số lượng cho phép");
			}
		} else {
			this.request.setAttribute("message", "Bạn chưa có nhóm. Hoặc đã đăng kí đề tài");
		}
	}
	
	
	public String randomIdNotDuplicate() {
		GroupStudentDAO groupStudentDAO = new GroupStudentDAO();
		String id = "";
		do {
			id = groupStudentDAO.randomId();
		} while(groupStudentDAO.find(id) != null);
		return id;
	}
	
	public List<GroupStudent> getGroupStudent() {
		GroupStudentDAO groupStudentDAO = new GroupStudentDAO();
		List<GroupStudent> groupStudents = new ArrayList<GroupStudent>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isDeleted", (byte)0);
		
		groupStudents = groupStudentDAO.findWithNamedQuery("GroupStudent.getGroupStudent", map);
		return groupStudents;
	}
	
	
	public List<GroupStudent> checkRole(String studentId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("leaderId", studentId);
		GroupStudentDAO groupStudentDAO = new GroupStudentDAO();
		List<GroupStudent> groupStudents = new ArrayList<GroupStudent>();
		
		groupStudents = groupStudentDAO.findWithNamedQuery("GroupStudent.checkRole", map);
		return groupStudents;
	}
	
	public String grantPermissionDelete(String username) {
		Map<String, Object> map = new HashMap<String, Object>();
		Person person = new Person();
		Student student = new Student();
		StudentDAO studentDAO = new StudentDAO();
		PersonService personService = new PersonService(request, response);
		List<GroupStudent> groupStudents = new ArrayList<GroupStudent>();
		person = personService.getPersonByEmail(username);
		map.put("personId", person.getPersonId());
		student = studentDAO.findWithNamedQuery("Student.getStudentByPersonId", map).get(0);
		groupStudents = checkRole(student.getStudentId());
		if (groupStudents.size() > 0) {
			return "leader";
		} else {
			return "member";
		}
	}


	public void addMemberToGroup() throws ServletException, IOException {
//		Kiểm tra xem người yêu cầu thêm thành viên có phải là nhóm trưởng hay không
//		Kiểm tra mã sinh viên vừa nhập vào đã có nhóm hay chưa. Nếu chưa mới cho thêm vào nhóm
//		Kiểm tra tính hợp lệ của id được nhập vào.
		String url = "/student/group-manage";
		GroupService groupService = new GroupService(request, response);
		StudentService studentService = new StudentService(request, response);
		JoinGroupService joinGroupService = new JoinGroupService(request, response);
		
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
			this.request.setAttribute("message", "Cần chọn đề tài trước khi thêm thành viên vào nhóm");
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
					joinGroupService.deleteAllRequestJoinGroupsRelatedToStudentId(student_id);
					this.request.setAttribute("message", "Thêm thành viên thành công");
					
				} else {
					this.request.setAttribute("message", "Số lượng thành viên của nhóm vượt quá số lượng thành viên cho phép của đề tài");
				}
			} else {
				this.request.setAttribute("message", "Chỉ có trưởng nhóm mới được thêm thành viên");
			}
		}
		this.request.getRequestDispatcher(url).forward(request, response);
	}
}
