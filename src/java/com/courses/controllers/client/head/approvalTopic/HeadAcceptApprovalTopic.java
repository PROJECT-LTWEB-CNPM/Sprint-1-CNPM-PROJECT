package com.courses.controllers.client.head.approvalTopic;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.query.criteria.internal.expression.ConcatExpression;

import com.courses.dao.TopicDAO;
import com.courses.models.Topic;

@WebServlet(urlPatterns = { "/teacher/approval/accept", "/teacher/approval/accept/" })
public class HeadAcceptApprovalTopic extends HttpServlet {
	private static final long serialVersionUID = 1L;

	TopicDAO topicDAO = null;

	public HeadAcceptApprovalTopic() {
		super();
		topicDAO = new TopicDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");

			// Topic
			byte status = 1;
			String acceptApprovalTopicStatus = "";
			String topicId = request.getParameter("topic_id");
			System.out.println(topicId);
			if (topicId != null) {
				Topic topic = this.topicDAO.find(topicId);

				// Update
				topic.setStatus(status);
				this.topicDAO.update(topic);
				acceptApprovalTopicStatus = "success";
			}
			request.getSession().setAttribute("acceptApprovalTopicStatus", acceptApprovalTopicStatus);
			response.sendRedirect(request.getContextPath() + "/teacher/approval");
		} catch (Exception e) {

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
