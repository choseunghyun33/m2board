package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/begin/*")
public class BeginFilter extends HttpFilter implements Filter {
       
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("beginFilter.java session 필터 진입");
		
		if(request instanceof HttpServletRequest == false) { // 형변환이 불가하다면
			System.out.println("beginFilter.java HttpServletRequest로 형변환 할 수 없습니다.");
			return;
		} else {
			HttpSession session = ((HttpServletRequest)request).getSession();
			// 로그인이 되어있을 경우
			if(session.getAttribute("loginMember") != null) {
				if(response instanceof HttpServletResponse) {
					System.out.println("beginFilter.java 잘못된접근입니다");
					((HttpServletResponse)response).sendRedirect(
							 ((HttpServletRequest)request).getContextPath() + "/after/index"
							);
				}
				return;
			}
		}
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}
}
