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

@WebServlet("/signUp")
public class SignUpController extends HttpServlet {
	private IMemberService memberService;
	
	// form
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") != null) { // 로그인 된 상태
			response.sendRedirect(request.getContextPath() + "/index");
			return;
		}
		
		// 포워딩
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/signUp.jsp");
		rd.forward(request, response);
	}
	
	// action
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 인코딩
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") != null) { // 로그인 된 상태
			response.sendRedirect(request.getContextPath() + "/index");
			return;
		}
		
		// 값 객체에 넣기
		Member paramMember = new Member();
		paramMember.setId(request.getParameter("id"));
		paramMember.setPw(request.getParameter("pw"));
		paramMember.setName(request.getParameter("name"));
		paramMember.setAddr(request.getParameter("addr") + " " + request.getParameter("detailAddr"));
		
		// 디버깅
		System.out.println("SignUpController.java paramMember : " + paramMember.toString());
		
		// 메서드
		this.memberService = new MemberService();
		this.memberService.addMember(paramMember);
		
		// 재요청
		response.sendRedirect(request.getContextPath() + "/login");
	}
}
