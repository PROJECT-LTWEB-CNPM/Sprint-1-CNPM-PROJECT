package com.courses.services;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.courses.dao.TeacherDAO;
import com.courses.dao.TopicDAO;
import com.courses.models.Person;
import com.courses.models.RegistrationPeriod;
import com.courses.models.Teacher;
import com.courses.models.Topic;
import com.courses.services.TeacherService;

import net.bytebuddy.description.type.TypeDefinition.SuperClassIterator;

public class TopicService extends SuperService {

	private static TopicDAO topicDAO = new TopicDAO();
	TeacherDAO teacherDAO = new TeacherDAO();

	public TopicService(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	public TopicService() {
	}

	public void handleGetListTopic() throws ServletException, IOException {
		try {
			String pageUrl = "/pages/admin/topic/topic.jsp";
			List<Topic> topics = topicDAO.findAll();
			this.request.setAttribute("topics", topics);
			this.request.getRequestDispatcher(pageUrl).forward(request, response);
		} catch (Exception e) {
			String pageUrl = "/pages/500.jsp";
			this.request.getRequestDispatcher(pageUrl).forward(request, response);
		}
	}

	public void handleGetStudentListTopic() throws ServletException, IOException {
		// define default url
		String pageUrl = "/pages/client/student/topicRegistration.jsp";

		// getParameter
		String isSelected = this.request.getParameter("select");

		List<Topic> topics = null;
		// get topics
		if (isSelected == null) {
			topics = topicDAO.findAll();
		} else {
			topics = findSelectedTopic(Byte.parseByte(isSelected));
		}

		this.request.setAttribute("topics", topics);
		this.request.getRequestDispatcher(pageUrl).forward(request, response);
	}

	public void handleGetTeacherAddTopic() throws ServletException, IOException {
		String url = "/pages/client/teacher/addTopic.jsp";
		super.forwardToPage(url);
	}

	public void handlePostTeacherAddTopic() throws ServletException, IOException {
		this.request.setCharacterEncoding("UTF-8");
		try {
			// get saved information in session
			HttpSession session = this.request.getSession();
			Teacher teacher = (Teacher) session.getAttribute("teacher");
			RegistrationPeriod period = (RegistrationPeriod) session.getAttribute("period");

			// get parameter from the add topic form
			String topicId = this.request.getParameter("topicId");
			System.out.println("topic Id is: " + topicId);
			String topicName = this.request.getParameter("topicName");
			int noOfMember = Integer.parseInt(request.getParameter("numberOfMember"));
			String description = this.request.getParameter("topicDescription");

			// set topic properties
			Topic newTopic = new Topic();
			newTopic.setTopicId(topicId);
			newTopic.setTeacher(teacher);
			newTopic.setMajor(teacher.getMajor());
			newTopic.setRegistrationperiod(period);
			newTopic.setTopicName(topicName);
			newTopic.setMaxMoMember(noOfMember);
			newTopic.setDescription(description);

			// save
			TopicDAO td = new TopicDAO();
			td.create(newTopic);

			this.request.setAttribute("isAdded", "1");
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	private List<Topic> findSelectedTopic(Byte isSelected) {
		List<Topic> foundTopics = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isSelected", isSelected);
		foundTopics = topicDAO.findWithNamedQuery("Topic.findSelectedTopic", map);
		return foundTopics;
	}

	public void getTeacherRecommendTopic() throws ServletException {

		try {
			String url = "/pages/client/teacher/topicManage.jsp";
			// get session
			HttpSession session = request.getSession();
			// get infor in session
			Person person = (Person) session.getAttribute("person");
			String isSelected = request.getParameter("select");
			// get teacher
			Teacher teacher = TeacherService.getTeacherByPerson(person);
			// get list teacher's topic
			List<Topic> topics = null;
			if (teacher != null) {
				if (isSelected == null) {
					topics = getTopicByTeacher(teacher);
				} else {
					topics = getSpecifiedTopic(teacher, Byte.valueOf(isSelected));
				}
				request.setAttribute("topics", topics);
			}
			super.forwardToPage(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<Topic> getTopicByTeacher(Teacher teacher) {
		List<Topic> topics = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("teacher", teacher);
		topics = topicDAO.findWithNamedQuery("Topic.findTopicByTeacher", map);
		return topics;
	}

	public static List<Topic> getSpecifiedTopic(Teacher teacher, Byte isSelected) {
		List<Topic> topics = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("teacher", teacher);
		map.put("isSelected", isSelected);
		topics = topicDAO.findWithNamedQuery("Topic.findSpecifiedTopic", map);
		return topics;
	}

}
