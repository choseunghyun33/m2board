package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.IMemberService;
import service.MemberService;
import vo.Member;

@WebServlet("/begin/login")
public class LoginController extends HttpServlet {
	private IMemberService memberService;
	
	// login form
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 포워딩
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
		rd.forward(request, response);
	}

	// login action
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		// 객체에 값담기
		Member paramMember = new Member();
		
		paramMember.setId(request.getParameter("id"));
		paramMember.setPw(request.getParameter("pw"));
		
		// 디버깅
		System.out.println("LoginController.java paramMember : " + paramMember);
		
		this.memberService = new MemberService();
		
		Member member = memberService.getMemberLogin(paramMember);
		
		if(member == null) {
			System.out.println("로그인 실패");
			response.sendRedirect(request.getContextPath() + "/begin/login");
			return;
		}
		
		session.setAttribute("loginMember", member);
		response.sendRedirect(request.getContextPath() + "/after/index");
	}

}
