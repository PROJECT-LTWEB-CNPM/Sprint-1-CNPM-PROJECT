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
import com.courses.models.GroupStudent;

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
	
	public String randomIdNotDuplicate() {
		GroupStudentDAO groupStudentDAO = new GroupStudentDAO();
		String id = "";
		do {
			id = groupStudentDAO.randomId();
		} while(groupStudentDAO.find(id) != null);
		return id;
	}
	
	public List<GroupStudent> checkRole(String studentId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("leaderId", studentId);
		GroupStudentDAO groupStudentDAO = new GroupStudentDAO();
		List<GroupStudent> groupStudents = new ArrayList<GroupStudent>();
		
		groupStudents = groupStudentDAO.findWithNamedQuery("GroupStudent.checkRole", map);
		return groupStudents;
	}
	
	public List<GroupStudent> checkLeader(Map<String, Object> map) {
		List<GroupStudent> groupStudents = new ArrayList<GroupStudent>();
		groupStudents = this.groupDAO.findWithNamedQuery("GroupStudent.checkLeader", map);
		return groupStudents;
	}
}
