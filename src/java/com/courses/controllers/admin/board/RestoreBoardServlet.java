package com.courses.controllers.admin.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.services.BoardService;


@WebServlet(urlPatterns = {"/admin/boards/is-deleted/restore", "/admin/boards/is-deleted/restore/"})
public class RestoreBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public RestoreBoardServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardService boardService = new BoardService(request, response);
		boardService.restoreBoard();
		boardService.handleGetListBoardIsDelete();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
