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


@WebFilter("/*")
public class EncodingFilter extends HttpFilter implements Filter {
    
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 디버깅
		System.out.println("EncodingFilter.java POST인코딩 필터 진입");
		// request
		// post요청만 처리 (get요청 경우도 상관은 없음)
		// request.setCharacterEncoding("UTF-8");
		
		// 만약 무조건 post만 처리해야할 경우
		if(request instanceof HttpServletRequest) {
			if(((HttpServletRequest)request).getMethod().equals("POST")) {
				request.setCharacterEncoding("UTF-8");
				
				// 디버깅
				System.out.println("EncodingFilter.java request.setCharacterEncoding(\"UTF-8\") POST인코딩 실행");
			}
		}
		
		
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}
}
