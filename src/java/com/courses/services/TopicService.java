package com.courses.services;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.dao.TopicDAO;
import com.courses.models.Topic;

public class TopicService extends SuperService{

	TopicDAO topicDAO = null;
	public TopicService(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		this.topicDAO = new TopicDAO();
	}
	
	public void handleGetListTopic() throws ServletException, IOException {
		try {
			String pageUrl = "/pages/admin/topic/topic.jsp";
			List<Topic> topics = this.topicDAO.findAll();
			this.request.setAttribute("topics", topics);
			this.request.getRequestDispatcher(pageUrl).forward(request, response);
		} catch (Exception e) {
			String pageUrl = "/pages/500.jsp";
			this.request.getRequestDispatcher(pageUrl).forward(request, response);
		}
	}

}
