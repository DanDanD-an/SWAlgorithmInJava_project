package dao;

import java.io.Serializable;
import java.sql.*;

import util.JdbcUtil;
import vo.ExVO;
import vo.UsersVO;

public class UsersDAO extends Object implements Serializable {

	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	// INSERT
	public int insertUser(String userid, String pw) throws Exception {

		String sql = "INSERT INTO users (userid, pw) " + 
				"VALUES (?, ?)";

		con = JdbcUtil.getConnection();

		ps = con.prepareStatement(sql);
		ps.setString(1,	userid);
		ps.setString(2,	pw);

		int result = ps.executeUpdate();

		System.out.println(result + " row created.");

		JdbcUtil.close(rs, ps, con);

		return result;
	}
	
	// Log in
	public boolean logIn(String userid, String pw) {
		
		String sql = "SELECT * FROM users WHERE userid = ? AND pw = ?";
		boolean result = false;
		
		con = JdbcUtil.getConnection();
		UsersVO ex = null;

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1,	userid);
			ps.setString(2,	pw);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ex = new UsersVO();

				ex.setUserid(rs.getString("userid"));
				ex.setPw(rs.getString("pw"));
				// System.out.println(ex);
				if (rs.getString("pw") != null) {
					result = true;
				}
				else
					result = false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JdbcUtil.close(rs, ps, con);

		return result;
	}
	
	// Reserve
	public int reserve(String userid, int resexid) throws Exception {

		String sql = "UPDATE users SET resexid = ? WHERE userid = ?";

		con = JdbcUtil.getConnection();

		ps = con.prepareStatement(sql);
		ps.setInt(1, resexid);
		ps.setString(2, userid);

		int result = ps.executeUpdate();

		System.out.println(result + " row created.");

		JdbcUtil.close(rs, ps, con);

		return result;
	}
	
	// Check Reservation
	public ExVO chkRes(String userid) {
		String sql = "SELECT * FROM ex, users " + 
					"WHERE ex.exid = users.resexid AND userid = ?";
		
		ExVO ex = null;
		
		con = JdbcUtil.getConnection();

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1,	userid);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ex = new ExVO();

				ex.setExid(rs.getInt("exid"));
				ex.setTitle(rs.getString("title"));
				ex.setStartdate(rs.getString("startdate"));
				ex.setEnddate(rs.getString("enddate"));
				ex.setPlaceid(rs.getInt("placeid"));
				ex.setPrice(rs.getInt("price"));

				// System.out.println(ex);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JdbcUtil.close(rs, ps, con);

		return ex;
	}
}
