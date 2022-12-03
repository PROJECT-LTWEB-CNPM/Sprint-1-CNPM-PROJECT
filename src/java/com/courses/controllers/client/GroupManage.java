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

import com.courses.models.GroupStudent;
import com.courses.models.Student;
import com.courses.services.GroupService;
import com.courses.services.RegisterGroupService;
import com.courses.services.admin.user.StudentService;


@WebServlet("/group-manage")
public class GroupManage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    public GroupManage() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/pages/client/student/groupManage.jsp";
		request.getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	

}
