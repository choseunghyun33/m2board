package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DriverListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce)  { // tomcat이 실행때 실행
    	// tomcat실행될때 사용하면 좋은 것
    	try {
			Class.forName("org.mariadb.jdbc.Driver");
			// 디버깅
			System.out.println("DrivarListener.java Class.forName 드라이버로딩 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }
	
}
