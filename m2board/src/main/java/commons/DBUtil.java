package commons;

import java.sql.*;

public class DBUtil {
	public Connection getConnection() throws Exception {
		Class.forName("org.mariadb.jdbc.Driver");
		String url = "jdbc:mariadb://localhost:3306/m2board";
		String dbuser = "root";
		String dbpw = "1234";
		Connection conn = DriverManager.getConnection(url, dbuser, dbpw);
		
		return conn;
	}
}
