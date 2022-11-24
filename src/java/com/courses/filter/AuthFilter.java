package com.courses.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebFilter("/home/*")
public class AuthFilter extends HttpFilter implements Filter {
       
   
	private static final long serialVersionUID = 1L;

	public AuthFilter() {
        super(); 
    }

	
	public void destroy() {
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// cast object type 
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		// get cookie is existing 
		boolean check = false;
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (int i=0; i<cookies.length; i++ ) {
				if (cookies[i].getName().equals("userIdCookie")) {
					check = true;
				}
			}
		}
		
		// check to forward
		if(check) {
			chain.doFilter(request, response);
		}else {
			// destroy session
			HttpSession session = req.getSession(false);
			if (session != null) {
				session.invalidate();
			}
			// forward to login page
			String url = "/login";
			res.sendRedirect(req.getContextPath() + url);
		}
		
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
