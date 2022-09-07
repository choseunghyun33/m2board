package controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;
import service.IBoardService;
import vo.Board;


@WebServlet("/after/modifyBoard")
public class ModifyBoardController extends HttpServlet {
	private IBoardService boardService;
	
	// form
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// new
		this.boardService = new BoardService();
		
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		Map<String, Object> m = this.boardService.getBoardOneByBoardNo(boardNo);
		request.setAttribute("m", m);
		
		// 포워딩
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/modifyBoard.jsp");
		rd.forward(request, response);
	}
	// action
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// new
		this.boardService = new BoardService();
		
		// 값받기
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		// 객체에 담기
		Board board = new Board();
		
		board.setBoardNo(boardNo);
		board.setTitle(request.getParameter("title"));
		board.setContent(request.getParameter("content"));
		
		// 수정 메서드실행
		this.boardService.modifyBoard(board);
		
		// 재요청
		response.sendRedirect(request.getContextPath() + "/after/boardOne?boardNo=" + boardNo);
		
	}

}
