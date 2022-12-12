package com.courses.services;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.courses.dao.GroupStudentDAO;
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

	public RegistrationPriodService() {
	}

	public void handleGetList() throws ServletException, IOException {
		HttpSession session = this.request.getSession();
		
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
		session.invalidate();
	}
	
	public void handleGetListIsDeleted() throws ServletException, IOException {
		HttpSession session = this.request.getSession();
		String pageUrl = "/pages/admin/registrationPriod/registrationPriod.jsp";
		try {
			String type = this.request.getParameter("type");
			byte isForTeacher = 1;
			if (type.equals(RoleConstants.STUDENT)) {
				isForTeacher = 0;
			}
			List<RegistrationPeriod> registrationPeriodsIsDeleted = this.registrationPeriodDAO
					.findByIsRegistrationTeacherIsDeleted(isForTeacher);
			this.request.setAttribute("type", type);
			this.request.setAttribute("registrationPeriodsIsDeleted", registrationPeriodsIsDeleted);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			pageUrl = "/pages/500.jsp";
		}
		this.request.getRequestDispatcher(pageUrl).forward(request, response);
		session.invalidate();
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

	public RegistrationPeriod getRegistrationPeriod(Byte isActive) {
		RegistrationPeriod period = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("isActive", isActive);
		if (this.registrationPeriodDAO.findWithNamedQuery("RegistrationPeriod.findByStatus", map).size() > 0) {
			period = this.registrationPeriodDAO.findWithNamedQuery("RegistrationPeriod.findByStatus", map).get(0);
		}
		return period;
	}

	public void updateRegistrationPeriod() throws ServletException, IOException {
		HttpSession session = this.request.getSession();
		String isRegistrationPeriodUpdate = "FAILED";
		String role = RoleConstants.STUDENT;
		String context = this.request.getContextPath();
		String url = context + "/admin/registration-priods/?type=";
//		String url = "/pages/admin/dashboard/dashboard.jsp";
//		String url = "/admin/registration-priods/?type=student";

		Date date = new Date();
		String registrationPeriodId = this.request.getParameter("registrationPeriodId");
		String registrationPeriodName = this.request.getParameter("registrationPeriodName");
		String semeter = this.request.getParameter("semeter");
		String schoolYear = this.request.getParameter("schoolYear");
		String isRegistrationTeacher = this.request.getParameter("isRegistrationTeacher");

		try {
			Date openDate = RandomUtils.convertStringToDate(this.request.getParameter("openDate").split(" ")[0]);
			Date closeDate = RandomUtils.convertStringToDate(this.request.getParameter("closeDate").split(" ")[0]);
			String description = this.request.getParameter("description");
			Date currentDate = RandomUtils.convertStringToDate(RandomUtils.formatDate(date));

			if (closeDate.compareTo(currentDate) > 0 || closeDate.compareTo(currentDate) == 0) {
				if (openDate.compareTo(closeDate) < 0 || openDate.compareTo(closeDate) == 0) {
					RegistrationPeriodDAO registrationPeriodDAO = new RegistrationPeriodDAO();
					RegistrationPeriod registrationPeriod = new RegistrationPeriod();

					registrationPeriod = registrationPeriodDAO.find(registrationPeriodId);

					if (registrationPeriod.getIsRegistrationTeacher() == 1) {
						System.out.println("Bang 1");
						role = RoleConstants.TEACHER;
					}

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
			url = url + role;
			session.setAttribute("isRegistrationPeriodUpdate", isRegistrationPeriodUpdate);
			this.response.sendRedirect(url);
//			this.request.getRequestDispatcher("/admin/registration-priods/?type=teacher").forward(request, response);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			url = url + role;
			session.setAttribute("isRegistrationPeriodUpdate", isRegistrationPeriodUpdate);
			this.response.sendRedirect(url);
		}
	}

	public void softDeleteRegistrationPeriod() throws ServletException, IOException {
		HttpSession session = this.request.getSession();
		String isSoftDeleteRegistrationPeriod = "FAILED";
		String role = RoleConstants.STUDENT;
		String context = this.request.getContextPath();
		String url = context + "/admin/registration-priods/?type=";
		try {
			RegistrationPeriodDAO registrationPeriodDAO = new RegistrationPeriodDAO();
			RegistrationPeriod registrationPeriod = new RegistrationPeriod();

			String registrationPeriodId = this.request.getParameter("registration-period-id");
			registrationPeriod = registrationPeriodDAO.find(registrationPeriodId);
			registrationPeriod.setIsDeleted((byte) 1);

			if (registrationPeriod.getIsRegistrationTeacher() == 1) {
				System.out.println("Bang 1");
				role = RoleConstants.TEACHER;
			}

			registrationPeriodDAO.update(registrationPeriod);

			url = url + role;
			isSoftDeleteRegistrationPeriod = "SUCCESS";
			session.setAttribute("isSoftDeleteRegistrationPeriod", isSoftDeleteRegistrationPeriod);
			this.response.sendRedirect(url);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			url = url + role;
			session.setAttribute("isSoftDeleteRegistrationPeriod", isSoftDeleteRegistrationPeriod);
			this.response.sendRedirect(url);
		}
	}
	
	
	public void restoreRegistrationPeriod() throws ServletException, IOException {
		HttpSession session = this.request.getSession();
		try {
			String type = this.request.getParameter("type");
			String id = this.request.getParameter("id");
			RegistrationPeriodDAO registrationPeriodDAO = new RegistrationPeriodDAO();
			RegistrationPeriod registrationPeriod = new RegistrationPeriod();

			String registrationPeriodId = this.request.getParameter("id");
			registrationPeriod = registrationPeriodDAO.find(registrationPeriodId);
			registrationPeriod.setIsDeleted((byte) 0);

			registrationPeriodDAO.update(registrationPeriod);
			session.setAttribute("isRestoreRegistrationPeriod", "SUCCESS");
			this.request.setAttribute("type", type);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			session.setAttribute("isRestoreRegistrationPeriod", "FAILED");
		}
	}
	
	public String createRegistrationPeriodForTeacher(String registrationPeriodName, int semeter, int schoolYear, byte isRegistrationTeacher, Date openDate, Date closeDate, String description) {
		try {
			Date date = new Date();
			Date currentDate = RandomUtils.convertStringToDate(RandomUtils.formatDate(date));
			RegistrationPeriod registrationPeriod = new RegistrationPeriod();
			RegistrationPeriodDAO registrationPeriodDAO = new RegistrationPeriodDAO();
			if (closeDate.compareTo(currentDate) > 0 || closeDate.compareTo(currentDate) == 0) {
				if (openDate.compareTo(closeDate) < 0 || openDate.compareTo(closeDate) == 0) {
					if (openDate.getYear() == closeDate.getYear() && closeDate.getYear() == currentDate.getYear()) {
						registrationPeriod.setRegistrationPeriodId(this.randomIdNotDuplicate());
						registrationPeriod.setRegistrationPeriodName(registrationPeriodName);
						registrationPeriod.setSemeter(semeter);
						registrationPeriod.setSchoolYear(schoolYear);
						registrationPeriod.setIsRegistrationTeacher(isRegistrationTeacher);
						registrationPeriod.setOpenDate(openDate);
						registrationPeriod.setCloseDate(closeDate);
						registrationPeriod.setDescription(description);
						registrationPeriodDAO.create(registrationPeriod);
						
						return "SUCCESS";
					}
				}
			}
			return "FAILED";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "FAILED";
		}
	}
	
	
	public String createRegistrationPeriodForStudent(String registrationPeriodName, int semeter, int schoolYear, byte isRegistrationTeacher, Date openDate, Date closeDate, String description) {
		try {
			Date date = new Date();
			Date currentDate = RandomUtils.convertStringToDate(RandomUtils.formatDate(date));
			RegistrationPeriod registrationPeriod = new RegistrationPeriod();
			RegistrationPeriodDAO registrationPeriodDAO = new RegistrationPeriodDAO();
			Map<String, Object> map = new HashMap<>();
			
			List<RegistrationPeriod> registrationPeriods = new ArrayList<RegistrationPeriod>();
			if (closeDate.compareTo(openDate) > 0 || closeDate.compareTo(openDate) == 0) {
				map.put("semeter", semeter);
				map.put("schoolYear", schoolYear);
				map.put("openDate", openDate);
				registrationPeriods = registrationPeriodDAO.findWithNamedQuery("RegistrationPeriod.checkConditionsToCreateRegistrationPeriod", map);
				if (registrationPeriods.size() > 0) {
//					System.out.println("===========STUDENT ĐĂNG KÍ THÀNH CÔNG====================");
					registrationPeriod.setRegistrationPeriodId(this.randomIdNotDuplicate());
					registrationPeriod.setRegistrationPeriodName(registrationPeriodName);
					registrationPeriod.setSemeter(semeter);
					registrationPeriod.setSchoolYear(schoolYear);
					registrationPeriod.setIsRegistrationTeacher(isRegistrationTeacher);
					registrationPeriod.setOpenDate(openDate);
					registrationPeriod.setCloseDate(closeDate);
					registrationPeriod.setDescription(description);
					registrationPeriodDAO.create(registrationPeriod);
					
					return "SUCCESS";
				}
			}
			return "FAILED";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "FAILED";
		}
	}

	public void createRegistrationPeriod() throws ServletException, IOException {
		super.setEncoding();
		String role = "student";
		String context = this.request.getContextPath();
		String url = context + "/admin/registration-priods/?type=";
		HttpSession session = this.request.getSession();
		String isCreateRegistrationPeriod = "";
		try {
//			RegistrationPeriod registrationPeriod = new RegistrationPeriod();
//			RegistrationPeriodDAO registrationPeriodDAO = new RegistrationPeriodDAO();
//			Date date = new Date();
			
			String registrationPeriodName = this.request.getParameter("registrationPeriodName");
			int semeter = Integer.parseInt(this.request.getParameter("semester"));
			int schoolYear = Integer.parseInt(this.request.getParameter("schoolYear"));
			byte isRegistrationTeacher = Byte.parseByte(this.request.getParameter("isRegistrationTeacher"));
			Date openDate = RandomUtils.convertStringToDate(this.request.getParameter("openDate").split(" ")[0]);
			Date closeDate = RandomUtils.convertStringToDate(this.request.getParameter("closeDate").split(" ")[0]);
			String description = this.request.getParameter("description");
//			Date currentDate = RandomUtils.convertStringToDate(RandomUtils.formatDate(date));
			
			if (isRegistrationTeacher == 1) {
				isCreateRegistrationPeriod = createRegistrationPeriodForTeacher(registrationPeriodName, semeter, schoolYear, isRegistrationTeacher, openDate, closeDate, description);
				role = RoleConstants.TEACHER;
			} else if (isRegistrationTeacher == 0) {
				isCreateRegistrationPeriod = createRegistrationPeriodForStudent(registrationPeriodName, semeter, schoolYear, isRegistrationTeacher, openDate, closeDate, description);
				role = RoleConstants.STUDENT;
			}
		
			url = url + role;
			session.setAttribute("isCreateRegistrationPeriod", isCreateRegistrationPeriod);
			this.response.sendRedirect(url);
//			session.removeAttribute("isCreateRegistrationPeriod");

		} catch (Exception e) {
			System.out.println(e.getMessage());
//			url = "/pages/500.jsp";
			url = url + role;
			session.setAttribute("isCreateRegistrationPeriod", isCreateRegistrationPeriod);
			this.response.sendRedirect(url);
		}
	}

	public String randomIdNotDuplicate() {
		RegistrationPeriodDAO registrationPeriodDAO = new RegistrationPeriodDAO();
		String id = "";
		do {
			id = registrationPeriodDAO.randomId();
		} while (registrationPeriodDAO.find(id) != null);
		return id;
	}
	
}
