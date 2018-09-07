package dao;

import java.sql.*;

import util.JdbcUtil;
import vo.LocVO;
import vo.PlacesVO;

public class LocDAO {
	
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	public String getLocname(int locid) {

		String sql = "SELECT * FROM loc WHERE locid = ?";

		LocVO ex = null;
		con = JdbcUtil.getConnection();

		try {

			ps = con.prepareStatement(sql);
			ps.setInt(1, locid);
			rs = ps.executeQuery();

			while(rs.next()) {
				ex = new LocVO();
				ex.setLocid(rs.getInt("LOCID"));
				ex.setLocname(rs.getString("LOCNAME"));

				System.out.println(ex);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			JdbcUtil.close(rs, ps, con);
		}
		// System.out.println(list);


		return ex.getLocname();
	}
	

}
