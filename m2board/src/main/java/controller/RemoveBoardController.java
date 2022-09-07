package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;
import service.IBoardService;

@WebServlet("/after/removeBoard")
public class RemoveBoardController extends HttpServlet {
	private IBoardService boardService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// new
		boardService = new BoardService();
		
		// 값받기
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		// 디버깅
		System.out.println("RemoveBoardController.java boardNo : " + boardNo);
		
		// 필요한 메서드 부르기
		boardService.removeBoard(boardNo);
		
		// 재요청하기
		response.sendRedirect(request.getContextPath() + "/public/boardList");
	}

}
