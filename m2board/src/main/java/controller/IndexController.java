package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CounterService;
import service.ICounterService;

@WebServlet("/after/index")
public class IndexController extends HttpServlet {
	private ICounterService counterService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// new
		this.counterService = new CounterService();
		
		// 방문자 수
		int totalCounter = this.counterService.getTotalCount();
		int todayCounter = this.counterService.getTodayCount();
		
		request.setAttribute("totalCounter", totalCounter);
		request.setAttribute("todayCounter", todayCounter);
		
		// 포워딩
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/index.jsp");
		rd.forward(request, response);
	}
}
