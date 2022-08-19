package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/logout")
public class LogoutController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		// 로그인 안된 상태 재요청
		if(session.getAttribute("loginMember") == null) { 
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		
		session.invalidate();
		// 재요청
		response.sendRedirect(request.getContextPath() + "/login");
	}
}
