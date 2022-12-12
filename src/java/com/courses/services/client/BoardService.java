package com.courses.services.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.dao.BoardDAO;
import com.courses.dao.TeacherBoardDAO;
import com.courses.dao.TeacherDAO;
import com.courses.models.Board;
import com.courses.models.Teacher;
import com.courses.models.TeacherBoard;
import com.courses.models.TeacherBoardPK;
import com.courses.services.SuperService;

public class BoardService extends SuperService {
	BoardDAO boardDAO = null;
	TeacherBoardDAO teacherBoardDAO = null;
	TeacherDAO teacherDAO = null;

	public BoardService(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		this.boardDAO = new BoardDAO();
		this.teacherBoardDAO = new TeacherBoardDAO();
		this.teacherDAO = new TeacherDAO();
	}

	public void getListHeadBoard() throws ServletException, IOException {
		try {
			super.setEncoding();
			// Url
			String url = "/pages/client/teacher/head/boardManage.jsp";

			List<Board> boards = this.boardDAO.findAll();
			request.setAttribute("boards", boards);

			super.forwardToPage(url);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			String url = "/pages/500.jsp";
			super.forwardToPage(url);
		}
	}

	public void getDetailHeadBoard() throws ServletException, IOException {
		try {
			super.setEncoding();
			// Url
			String url = "/pages/client/teacher/head/detailHeadBoard.jsp";

			// Get Board
			String boardId = request.getParameter("board_id");
			Board board = this.boardDAO.find(boardId);
			List<TeacherBoard> teacherBoards = new ArrayList<>();

			if (board != null) {
				Map<String, Object> params = new HashMap<>();
				params.put("board", board);
				teacherBoards = this.teacherBoardDAO.findByBoard(params);
			}

			// Set board
			request.setAttribute("board", board);
			request.setAttribute("boardId", boardId);
			request.setAttribute("teacherBoards", teacherBoards);
			super.forwardToPage(url);

			// Remove status add member to board
			request.getSession().setAttribute("addedTeacherBoardStatus", null);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			String url = "/pages/500.jsp";
			super.forwardToPage(url);
		}
	}

	public void getAddMemberToBoardForm() throws ServletException, IOException {
		try {
			super.setEncoding();
			System.out.println("Hello world");
			// Url
			String url = "/pages/client/teacher/head/addMemberForm.jsp";

			// Get Board
			String boardId = request.getParameter("board_id");
			// Set board
			request.setAttribute("boardId", boardId);

			super.forwardToPage(url);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			String url = "/pages/500.jsp";
			super.forwardToPage(url);
		}
	}

	public void submitAddMemberToBoardForm() throws ServletException, IOException {
		try {
			super.setEncoding();

			// Url

			// Get param
			String boardId = request.getParameter("boardId");
			String teacherId = super.getParameter("teacherId");
			String url = super.getContextPath() + "/teacher/board/head/detail/?board_id=" + boardId;
			String addedTeacherBoardStatus = "";

			// Get data
			Board board = this.boardDAO.find(boardId);
			Teacher teacher = this.teacherDAO.find(teacherId);

			// Make primary key
			TeacherBoardPK teacherBoardPK = new TeacherBoardPK();
			teacherBoardPK.setBoardId(boardId);
			teacherBoardPK.setTeacherId(teacherId);

			TeacherBoard teacherBoardExist = this.teacherBoardDAO.find(teacherBoardPK);

			if (teacherBoardExist == null) {
				// Make record
				TeacherBoard teacherBoard = new TeacherBoard();
				teacherBoard.setId(teacherBoardPK);
				teacherBoard.setBoard(board);
				teacherBoard.setTeacher(teacher);
				teacherBoard.setIsDeleted((byte) 0);
				// create
				this.teacherBoardDAO.create(teacherBoard);
				addedTeacherBoardStatus = "success";
			} else {
				addedTeacherBoardStatus = "fail";
			}
			request.getSession().setAttribute("addedTeacherBoardStatus", addedTeacherBoardStatus);
			// redirect
			super.redirectToPage(url);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			String url = "/pages/500.jsp";
			super.forwardToPage(url);
		}
	}
}
