package controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.BoardService;
import service.IBoardService;

@WebServlet("/boardOne")
public class BoardOneController extends HttpServlet {
	private IBoardService boardService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) { // 로그인 안된 상태
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		
		// new
		this.boardService = new BoardService();
		
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		Map<String, Object> m = this.boardService.getBoardOneByBoardNo(boardNo);
		request.setAttribute("m", m);
		
		// 뷰 포워딩
		request.getRequestDispatcher("/WEB-INF/view/boardOne.jsp").forward(request, response);
	}
}
