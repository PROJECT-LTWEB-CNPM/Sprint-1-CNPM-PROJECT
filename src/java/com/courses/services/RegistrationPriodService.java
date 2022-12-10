package com.courses.services;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.dao.RegistrationPeriodDAO;
import com.courses.models.RegistrationPeriod;
import com.courses.utils.constants.RoleConstants;
import com.courses.utils.helper.RandomUtils;

public class RegistrationPriodService extends SuperService {

	RegistrationPeriodDAO registrationPeriodDAO = null;

	public RegistrationPriodService(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		this.registrationPeriodDAO = new RegistrationPeriodDAO();
	}
	
	public RegistrationPriodService() {}

	public void handleGetList() throws ServletException, IOException {
		String pageUrl = "/pages/admin/registrationPriod/registrationPriod.jsp";
		try {
			String type = this.request.getParameter("type");
			byte isForTeacher = 1;
			if (type.equals(RoleConstants.STUDENT)) {
				isForTeacher = 0;
			}
			List<RegistrationPeriod> registrationPeriods = this.registrationPeriodDAO
					.findByIsRegistrationTeacher(isForTeacher);
			this.request.setAttribute("type", type);
			this.request.setAttribute("registrationPeriods", registrationPeriods);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			pageUrl = "/pages/500.jsp";
		}
		this.request.getRequestDispatcher(pageUrl).forward(request, response);
	}

	public void handleGetEditForm() throws ServletException, IOException {
		String pageUrl = "/pages/admin/registrationPriod/editRegistrationPriod.jsp";
		String id = this.request.getParameter("id");
		try {
			String type = this.request.getParameter("type");
			this.request.setAttribute("type", type);
			RegistrationPeriod rp = this.registrationPeriodDAO.find(id);
			this.request.setAttribute("rp", rp);
//			System.out.println(rp.getCloseDate());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			pageUrl = "/pages/500.jsp";
		}
		this.request.getRequestDispatcher(pageUrl).forward(request, response);
	}
	
	public RegistrationPeriod getRegistrationPeriod (Byte isActive) {
		RegistrationPeriod period = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("isActive", isActive);
		if (this.registrationPeriodDAO.findWithNamedQuery("RegistrationPeriod.findByStatus", map).size() > 0) {
			period = this.registrationPeriodDAO.findWithNamedQuery("RegistrationPeriod.findByStatus", map).get(0);
		}
		return period;
	}
	
	public void updateRegistrationPeriod() throws ServletException, IOException {
		String isRegistrationPeriodUpdate = "";
		String url = "/admin/registration-priods";
		Date date = new Date();
		String registrationPeriodId = this.request.getParameter("registrationPeriodId");
		String isRegistrationTeacher = this.request.getParameter("isRegistrationTeacher");
		String registrationPeriodName = this.request.getParameter("registrationPeriodName");
		String semeter = this.request.getParameter("semeter");
		String schoolYear = this.request.getParameter("schoolYear");
		try {	
			Date openDate = RandomUtils.convertStringToDate(this.request.getParameter("openDate").split(" ")[0]);
			Date closeDate = RandomUtils.convertStringToDate(this.request.getParameter("closeDate").split(" ")[0]);
			String description = this.request.getParameter("description");
			Date currentDate = RandomUtils.convertStringToDate(RandomUtils.formatDate(date));

//			System.out.println("openDate: " + openDate);
//			System.out.println("closeDate: " + closeDate);
//			System.out.println("currentDate: " + currentDate);
			
//			Ngày kết thúc đăng kí phải lớn hơn ngày bắt đầu đăng kí và lơn hơn ngày chỉnh sửa hiện tại			
			if (openDate.compareTo(currentDate) < 0 || openDate.compareTo(currentDate) == 0) {
				if(openDate.compareTo(closeDate) < 0 || openDate.compareTo(closeDate) == 0) {
					RegistrationPeriodDAO registrationPeriodDAO = new RegistrationPeriodDAO();
					RegistrationPeriod registrationPeriod = new RegistrationPeriod();
					
					registrationPeriod = registrationPeriodDAO.find(registrationPeriodId);
					
					registrationPeriod.setCloseDate(closeDate);
					registrationPeriod.setDescription(description);
					registrationPeriod.setOpenDate(openDate);
					registrationPeriod.setRegistrationPeriodName(registrationPeriodName);
					registrationPeriod.setSchoolYear(Integer.parseInt(schoolYear));
					registrationPeriod.setSemeter(Integer.parseInt(semeter));
					registrationPeriodDAO.update(registrationPeriod);
					isRegistrationPeriodUpdate = "SUCCESS";	
				} else {
					isRegistrationPeriodUpdate = "FAILED";
				}
			} else {
				isRegistrationPeriodUpdate = "FAILED";
			}
		
//					url = url + isRegistrationTeacher;
			this.request.setAttribute("isRegistrationPeriodUpdate", isRegistrationPeriodUpdate);
			this.request.getRequestDispatcher(url).forward(request, response);
			
		} catch (Exception e) {
			isRegistrationPeriodUpdate = "FAILED";
			System.out.println(e.getMessage());
			this.request.setAttribute("isRegistrationPeriodUpdate", isRegistrationPeriodUpdate);
			this.request.getRequestDispatcher(url).forward(request, response);
		}
	}
}
