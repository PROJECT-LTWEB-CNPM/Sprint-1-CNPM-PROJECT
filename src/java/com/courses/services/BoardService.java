package com.courses.services;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.dao.BoardDAO;
import com.courses.models.Board;

public class BoardService extends SuperService {

	BoardDAO boardDAO = null;
	public BoardService(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		this.boardDAO = new BoardDAO();
	}

	public void handleGetListBoard() throws ServletException, IOException {
		String pageUrl = "/pages/admin/board/board.jsp";
		try {
			String semester = this.request.getParameter("semester");
			String year = this.request.getParameter("year");
			List<Board> boards = this.boardDAO.findAll();
			int maxEntries = this.boardDAO.count();
			this.request.setAttribute("boards", boards);
			this.request.setAttribute("maxEntries", maxEntries);
		} catch (Exception e) {
			pageUrl = "/pages/500.jsp";
		}
		this.request.getRequestDispatcher(pageUrl).forward(request, response);
	}

}
