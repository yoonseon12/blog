package blog.commons;

import java.sql.*;

public class DBUtil {
	// Connection
	public static Connection getConection() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		Class.forName("org.mariadb.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/blog", "root", "java1234");
		return conn;
	}
	// close
	public static void close(ResultSet rs, Statement stmt, Connection conn) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("rs close 예외발생");
			}
		}
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("stmt close 예외발생");
			}
		}
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("conn close 예외발생");
			}
		}
	}
}
