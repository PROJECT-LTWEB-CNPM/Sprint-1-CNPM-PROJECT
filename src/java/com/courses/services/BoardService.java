package com.courses.services;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.internal.build.AllowSysOut;

import com.courses.dao.BoardDAO;
import com.courses.models.Board;
import com.courses.models.Topic;

public class BoardService extends SuperService {

	BoardDAO boardDAO = null;

	public BoardService(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		this.boardDAO = new BoardDAO();
	}

	public BoardService() {
	}

	public void handleGetListBoard() throws ServletException, IOException {
		super.setEncoding();
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
		super.forwardToPage(pageUrl);

		request.getSession().setAttribute("createdBoardStatus", null);
		request.getSession().setAttribute("updatedBoardStatus", null);
	}

	public void submitBoardForm() throws IOException, ServletException {
		try {
			super.setEncoding();
			// Url
			String url = super.getContextPath() + "/admin/boards/";

			// Get data
			String boardId = super.getParameter("boardId");
			String boardName = super.getParameter("boardName");
			String noOfMemberStr = super.getParameter("noOfMember");
			String description = super.getParameter("description");
			String createdBoardStatus = "";
			int noOfMember = 0;

			if (noOfMemberStr != null) {
				noOfMember = Integer.parseInt(noOfMemberStr);
			}
			// Board

			Board board = new Board();
			board.setBoardId(boardId);
			board.setBoardName(boardName);
			board.setNoMember(noOfMember);
			board.setDescription(description);
			this.boardDAO.create(board);
			createdBoardStatus = "success";

			request.getSession().setAttribute("createdBoardStatus", createdBoardStatus);

			super.redirectToPage(url);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			String url = "/pages/500.jsp";
			super.forwardToPage(url);
		}
	}

	public void getEditBoardForm() throws ServletException, IOException {
		try {
			super.setEncoding();
			// Url
			String url = "/pages/admin/board/editBoard.jsp";

			String boardId = super.getParameter("board_id");
			System.out.println(boardId);
			Board board = this.boardDAO.find(boardId);

			request.setAttribute("board", board);
			super.forwardToPage(url);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			String url = "/pages/500.jsp";
			super.forwardToPage(url);
		}
	}

	public void submitEditBoardForm() throws ServletException, IOException {
		try {
			super.setEncoding();
			// Url
			String url = super.getContextPath() + "/admin/boards/";

			String boardId = super.getParameter("boardId");
			String boardName = super.getParameter("boardName");
			String noOfMemberStr = super.getParameter("noOfMember");
			String description = super.getParameter("description");
			String updatedBoardStatus = "";

			int noOfMember = 0;

			if (noOfMemberStr != null) {
				noOfMember = Integer.parseInt(noOfMemberStr);
			}

			// Update
			Board board = this.boardDAO.find(boardId);
			board.setBoardName(boardName);
			board.setNoMember(noOfMember);
			board.setDescription(description);

			this.boardDAO.update(board);
			updatedBoardStatus = "success";

			request.getSession().setAttribute("updatedBoardStatus", updatedBoardStatus);
			super.redirectToPage(url);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			String url = "/pages/500.jsp";
			super.forwardToPage(url);
		}
	}
}
