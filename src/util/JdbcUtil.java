package util;

import java.sql.*;

/**
 	- Connection 관리 클래스
 */

public class JdbcUtil {

	// Connection 생성 메소드
	public static Connection getConnection() {
		
		String driver = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String user = "ex";
		String pw = "ex";
		
		Connection con = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pw);
			
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return con;
	}
	
	// 자원 반납 메소드
	public static void close(ResultSet rs, Statement st, Connection con) {
		try {
			
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (con != null)
				con.close();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
