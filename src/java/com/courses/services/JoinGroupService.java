package com.courses.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.courses.dao.JoinGroupDAO;
import com.courses.models.GroupStudent;
import com.courses.models.JoinGroup;
import com.courses.models.JoinGroupPK;
import com.courses.services.admin.user.StudentService;



public class JoinGroupService extends SuperService {
	public JoinGroupService(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
	
	public JoinGroupService() {}
	
	public void handleJoinGroup() throws ServletException, IOException {
		String url = "/student/group-manage";
		StudentService studentService = new StudentService(request, response);
		
		JoinGroup joinGroup = new JoinGroup();
		JoinGroupPK joinGroupPK = new JoinGroupPK();
		
		JoinGroupDAO joinGroupDAO = new JoinGroupDAO();
		String group_id = this.request.getParameter("groupt_id");
		String studentId = studentService.getStudentByPersonToLoginData().getStudentId();
		
		joinGroupPK.setGroupId(group_id);
		joinGroupPK.setStudentId(studentId);
		
		joinGroup.setId(joinGroupPK);
		joinGroupDAO.create(joinGroup);
		request.setAttribute("message", "Xin tham gia nhóm " + group_id + " thành công");
		this.request.getRequestDispatcher(url).forward(request, response);
		
//		System.out.println("===============JOIN GROUP===================");
//		System.out.println("===============groud_id: " + group_id + "===================");
//		System.out.println("===============studentId: " + studentId + "===================");
	}
	
	public List<JoinGroup>getRequestJoinGroup(String studentId) {
		GroupService groupService = new GroupService(request, response);
		JoinGroupDAO joinGroupDAO = new JoinGroupDAO();
		List<GroupStudent> groupStudents = new ArrayList<GroupStudent>();
		List<JoinGroup> joinGroups = new ArrayList<JoinGroup>();
		Map<String, Object> map = new HashMap<String, Object>();
		
		groupStudents = groupService.checkRole(studentId);
		if (groupStudents.size() > 0) {
			String group_id = groupStudents.get(0).getGroupId();
			map.put("groupId", group_id);
			joinGroups = joinGroupDAO.findWithNamedQuery("JoinGroup.getRequestJoinGroup", map);
			if (joinGroups.size() > 0) {
				return joinGroups;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
//	Xóa đi 1 đối tượng JoinGroup khi trưởng nhóm ấn vào nút từ chối
	public void deleteRequestJoinGroup(String studentId, String groupId) {
		JoinGroupDAO joinGroupDAO = new JoinGroupDAO();
		JoinGroupPK joinGroupPK = new JoinGroupPK();
		joinGroupPK.setGroupId(groupId);
		joinGroupPK.setStudentId(studentId);
		
		joinGroupDAO.delete(joinGroupPK);
	}
	
//	xóa đi tất cả các đối tượn JoinGroup 
//	khi sinh viên đó được phê duyệt vào 1 group(Mỗi sinh viên chỉ được ở duy nhất 1 group - Sử dụng cho 
//			trường hợp add, chập nhật xin vào group)
	public void deleteAllRequestJoinGroupsRelatedToStudentId(String studentId) {
		JoinGroupDAO joinGroupDAO = new JoinGroupDAO();
		List<JoinGroup> joinGroups = new ArrayList<JoinGroup>();
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("studentId", studentId);
		joinGroups = joinGroupDAO.findWithNamedQuery("JoinGroup.getJoinGroupByStudentId", map);
		
		for (JoinGroup joinGroup : joinGroups) {
//			System.out.println("=====" + joinGroup.getGroupstudent().getGroupId() + "==========" + joinGroup.getStudent().getStudentId());
			deleteRequestJoinGroup(studentId,joinGroup.getGroupstudent().getGroupId());
		}
	}
	
	
	public void handleDeleteRequestJoinGroup() throws ServletException, IOException {
		String url = "/student/group-manage";
		StudentService studentService = new StudentService(request, response);
		GroupService groupService = new GroupService(request, response);
		List<GroupStudent> groupStudents = new ArrayList<GroupStudent>();
//		Sinh viên cần xóa ra khỏi danh sách hàng chờ xin vào nhóm
		String student_id = request.getParameter("studentId");
//		Nhóm trưởng của nhóm sinh viên cần xóa 
		String leaderId = studentService.getStudentByPersonToLoginData().getStudentId();
//		Kiểm tra nếu là trưởng nhóm mới có thể được xóa sinh viên ra khỏi dánh sách hàng chờ
		groupStudents = groupService.checkRole(leaderId);
		if (groupStudents.size() > 0) {
			String groupId = groupStudents.get(0).getGroupId();
			this.deleteRequestJoinGroup(student_id, groupId);
			this.request.setAttribute("message", "Xóa khỏi danh sách chờ thành công");
		} else {
			this.request.setAttribute("message", "Không tồn tại trong danh sách cần xóa");
		}
		this.request.getRequestDispatcher(url).forward(request, response);
	}
}
