package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.INiceService;
import service.NiceService;
import vo.Member;
import vo.Nice;

@WebServlet("/addNice")
public class AddNiceController extends HttpServlet {
	private INiceService niceService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) { // 로그인 안된 상태
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		
		this.niceService = new NiceService();
		
		// 값 받기
		Nice nice = new Nice();
		
		nice.setBoardNo(Integer.parseInt(request.getParameter("boardNo")));
		nice.setId(((Member) session.getAttribute("loginMember")).getId());
		
		// 메서드 실행 - 리턴값 없음
		this.niceService.addNice(nice);

		// 브라우저(클라이언트)에게 리다이렉트 요청
		response.sendRedirect(request.getContextPath() + "/boardList");
	}
}
