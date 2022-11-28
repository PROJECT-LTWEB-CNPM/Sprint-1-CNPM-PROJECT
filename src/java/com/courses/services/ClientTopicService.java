package com.courses.services;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.dao.TopicDAO;
import com.courses.models.Topic;

public class ClientTopicService extends SuperService{

	TopicDAO topicDAO = null;
	public ClientTopicService(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		this.topicDAO = new TopicDAO();
	}
	
	public void handleGetListTopic() throws ServletException, IOException {
		// define default url
		String pageUrl = "/pages/client/student/topicRegistration.jsp";
		
		// getParameter
		String isSelected = this.request.getParameter("select");
		
		List<Topic> topics = null;
	
			// get topics
			if (isSelected == null) {
				topics = this.topicDAO.findAll();
			}else {
				topics = findSelectedTopic(Byte.parseByte(isSelected));
			}
			
			this.request.setAttribute("topics", topics);
			this.request.getRequestDispatcher(pageUrl).forward(request, response);
	
		
	}
	
	
	private List<Topic> findSelectedTopic(Byte isSelected){
		List<Topic> foundTopics = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isSelected", isSelected);
		foundTopics = this.topicDAO.findWithNamedQuery("Topic.findSelectedTopic", map);
		return foundTopics;
	}
	

}
