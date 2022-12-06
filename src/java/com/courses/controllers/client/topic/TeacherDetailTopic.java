package com.courses.controllers.client.topic;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/teacher/topic-manage/detail")
public class TeacherDetailTopic extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public TeacherDetailTopic() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/pages/client/teacher/detailTopic.jsp";
		response.sendRedirect(request.getContextPath() + url);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
