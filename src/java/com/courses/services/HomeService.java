package com.courses.services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.courses.models.Person;

public class HomeService extends SuperService{

	public HomeService(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
	
	public void handleGetHome() throws ServletException, IOException {
		// define url
		String url = "/pages/client/home.jsp";	
		
		// forward information to jsp file
		super.forwardToPage(url);
	}
	
	public void handlePostHome() throws ServletException, IOException {
		
	}
	
	public void handleGetTeacherHomeService() throws ServletException, IOException{
		// define url for teacher home page
		String url = "/pages/client/teacher/home.jsp";
		//forward information to jsp file
		super.forwardToPage(url);
	}


}
