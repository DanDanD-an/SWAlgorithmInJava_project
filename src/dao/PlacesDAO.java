package dao;

import java.sql.*;

import util.JdbcUtil;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import vo.ExVO;
import vo.PlacesVO;

public class PlacesDAO {

	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	// INSERT
	public int insertPl(int placeid, String placename, String closedday, int locid) throws Exception {

		String sql = "INSERT INTO places (placeid, placename, closedday, locid) " + 
				"VALUES(?, ?, ?, ?)";

		con = JdbcUtil.getConnection();

		ps = con.prepareStatement(sql);
		ps.setInt(1, placeid);
		ps.setString(2,	placename);
		ps.setString(3,	closedday);
		ps.setInt(4, locid);

		int result = ps.executeUpdate();

		System.out.println(result + " row created.");

		JdbcUtil.close(rs, ps, con);

		return result;
	}

	// DELETE
	public int deletePl(String placename) {

		String sql = "DELETE FROM places WHERE lower(placename) = ?";
		int result = 0;

		con = JdbcUtil.getConnection();

		try {

			ps = con.prepareStatement(sql);
			ps.setString(1,	placename);
			result = ps.executeUpdate();

			System.out.println(result + " rows deleted.");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		JdbcUtil.close(rs, ps, con);

		return result;
	}

	// UPDATE
	public String updatePl(String placename, String closedday) {

		String sql = "UPDATE places SET closedday = ? " + 
				"WHERE placename = ?";

		con = JdbcUtil.getConnection();

		try {

			ps = con.prepareStatement(sql);
			ps.setString(1,	closedday);
			ps.setString(2,	placename);
			int result = ps.executeUpdate();

			System.out.println(result + " rows updated.");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		JdbcUtil.close(rs, ps, con);

		return closedday;
	}

	// 전체 조회
	public List<PlacesVO> getPl() {

		String sql = "SELECT * FROM places ORDER BY placeid";

		PlacesVO pl = null;
		List<PlacesVO> list = new ArrayList<PlacesVO>();

		con = JdbcUtil.getConnection();

		try {

			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while(rs.next()) {
				pl = new PlacesVO();

				pl.setPlaceid(rs.getInt("placeid"));
				pl.setPlacename(rs.getString("placename"));
				pl.setClosedday(rs.getString("closedday"));
				pl.setLocid(rs.getInt("locid"));

				list.add(pl);

				// System.out.println(pl);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			JdbcUtil.close(rs, ps, con);
		}
		// System.out.println(list);


		return list;
	}
	
	public String getPlacename(int placeid) {

		String sql = "SELECT * FROM places WHERE placeid = ?";

		PlacesVO ex = null;
		con = JdbcUtil.getConnection();

		try {

			ps = con.prepareStatement(sql);
			ps.setInt(1, placeid);
			rs = ps.executeQuery();

			while(rs.next()) {
				ex = new PlacesVO();
				ex.setPlacename(rs.getString("placename"));

				// System.out.println(ex);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			JdbcUtil.close(rs, ps, con);
		}
		// System.out.println(list);


		return ex.getPlacename();
	}
	
	public int getLocid(int placeid) {

		String sql = "SELECT * FROM places WHERE placeid = ?";

		PlacesVO ex = null;
		con = JdbcUtil.getConnection();

		try {

			ps = con.prepareStatement(sql);
			ps.setInt(1, placeid);
			rs = ps.executeQuery();

			while(rs.next()) {
				ex = new PlacesVO();
				ex.setLocid(rs.getInt("locid"));

				// System.out.println(ex);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			JdbcUtil.close(rs, ps, con);
		}
		// System.out.println(list);


		return ex.getLocid();
	}
}
