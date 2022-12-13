package com.courses.services;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.dao.NotificationDAO;
import com.courses.dao.StudentDAO;
import com.courses.models.Notification;
import com.courses.models.Person;
import com.courses.models.Topic;
import com.courses.services.admin.user.StudentService;

public class NotificationService extends SuperService{
	NotificationDAO notificationDAO = null;
	public NotificationService(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		this.notificationDAO = new NotificationDAO();
	}
	
	public NotificationService() {}
	
	public String randomIdNotDuplicate() {
		NotificationDAO notificationDAO = new NotificationDAO();
		String id = "";
		do {
			id = notificationDAO.randomId();
		} while(notificationDAO.find(id) != null);
		return id;
	}
	
	public void addNotification(String sender_id, String reciever_id, String title, String content) throws UnsupportedEncodingException {
		super.setEncoding();
		StudentDAO studentDAO = new StudentDAO();
		Date date = new Date();
		
		Person senderPerson = new Person();
		Person recieverPerson = new Person();
		Notification notification = new Notification();
		
		if( sender_id != reciever_id) {
			senderPerson = studentDAO.find(sender_id).getPerson();
			recieverPerson = studentDAO.find(reciever_id).getPerson();
			
			notification.setNotificationId(this.randomIdNotDuplicate());
			notification.setNotificationTitle(title);
			notification.setPerson1(senderPerson);
			notification.setPerson2(recieverPerson);
			notification.setStatus(0); // chưa đọc
			notification.setContent(content);
			notification.setTime(date);
			notificationDAO.create(notification);
		} else {
			System.out.println("================CAN NOT CREATE NOTIFICATION===================");
		}
	}
	
	public void getNotificationByLoginAccount() throws ServletException, IOException {
		String url = "/pages/client/student/home.jsp";
		StudentService studentService = new StudentService(request, response);
		NotificationDAO notificationDAO = new NotificationDAO();
		List<Notification> notifications = new ArrayList<Notification>();
		String person_id = studentService.getStudentByPersonToLoginData().getPerson().getPersonId();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("personId", person_id);
		
		notifications = notificationDAO.findWithNamedQuery("Notification.getNotificationByLoginAccount", map);
		this.request.setAttribute("notifications",notifications);
		this.request.getRequestDispatcher(url).forward(request, response);
	}
	
	public void showDetailOneNotification() throws ServletException, IOException {
		String notification_id = this.request.getParameter("notification_id");
		NotificationDAO notificationDAO = new NotificationDAO();
		Notification notification = new Notification();
		
		notification = this.notificationDAO.find(notification_id);
		notification.setStatus((byte)1);
		notificationDAO.update(notification);
		getNotificationByLoginAccount();
//		System.out.println("================THIS IS TEST NOTIFICATION===================");
	}

}
