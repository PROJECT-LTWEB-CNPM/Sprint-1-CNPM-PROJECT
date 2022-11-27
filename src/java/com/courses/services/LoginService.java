package com.courses.services;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.courses.models.Account;
import com.courses.models.Person;
import com.courses.dao.AccountDAO;

public class LoginService extends SuperService {

	public LoginService(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	public void handleGetLogin() throws ServletException, IOException {
		String url = "/pages/client/login.jsp";

		super.forwardToPage(url);
	}

	// get an exist account
	private Account getAccount(String username) {
		AccountDAO ad = new AccountDAO();
		List<Account> listAccounts = ad.findAll();
		for (Account acc : listAccounts) {
			if (username.equals(acc.getUsername())) {
				return acc;
			}
		}
		return null;
	}

	private boolean checkRole(String roleName, Person user) {
		if (user.getRole().equals(roleName)) {
			return true;
		}
		return false;
	}

	public void handlePostLogin() throws IOException, ServletException {
		try {
			// define default url
			String url = "/pages/client/login.jsp";
			String errorMessage = "";

			// get parameters from login box
			String role = this.request.getParameter("role-account");
			String username = this.request.getParameter("username");
			String password = this.request.getParameter("password");

			// find account and user
			Account foundAccount = getAccount(username);
			System.out.println("============= Here ==============");
			Person person = null;
			if (foundAccount != null) {
				person = foundAccount.getPerson();
			}

			HttpSession session = request.getSession();
			
			// check if this account is existing
			if (foundAccount != null && checkRole(role, person)) {
				if (password.equals(foundAccount.getPassword())) {
					// define user id cookie timeout 30'
					Cookie c = new Cookie("userIdCookie", username);
					c.setMaxAge(2 * 60);
					c.setPath("/");
					this.response.addCookie(c);

					// set user information
					session.setAttribute("user", person);

					// define url base on role
					if (role.equals("student")) {
						url = "/home/student";
					} else if (role.equals("teacher")) {
						// boilerplate code
						url = "pages/teacher/home";
					}

				} else {
					// exist account but incorrect password was found
					url = "/pages/client/login.jsp";
					errorMessage = "* Mật khẩu không đúng !";
				}

			} else {
				// doesn't exist account
				url = "/pages/client/login.jsp";
				errorMessage = "* Không tìm thấy tài khoản !";
			}

			// save information to reuse
			session.setAttribute("username", username);
			session.setAttribute("password", password);
			request.setAttribute("error", errorMessage);

			// forward request to jsp file
			if (!url.equals("/pages/client/login.jsp")) {
				super.redirectToPage(request.getContextPath() + url);
			} else {
				super.forwardToPage(url);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
