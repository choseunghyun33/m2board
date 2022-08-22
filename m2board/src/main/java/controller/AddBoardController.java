package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.BoardService;
import service.IBoardService;
import vo.Board;
								
@WebServlet("/after/addBoard")
public class AddBoardController extends HttpServlet {
	private IBoardService boardService;
	
	// add
	// C -> V
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// forwarding
		request.getRequestDispatcher("/WEB-INF/view/addBoard.jsp").forward(request, response);
	}
	
	
	// action
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// new
		this.boardService = new BoardService();
		
		// 값 받기
		Board board = new Board();
		board.setTitle(request.getParameter("title"));
		board.setId(request.getParameter("id"));
		board.setContent(request.getParameter("content"));
		
		// 메서드실행
		this.boardService.addBoard(board);
		
		// 재요청 - 새로운 컨트롤러
		response.sendRedirect(request.getContextPath() + "/public/boardList");
	}
}
