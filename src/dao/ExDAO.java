package dao;

import java.sql.*;

import util.JdbcUtil;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import vo.ExVO;
import vo.PlacesVO;
import vo.ReviewsVO;

public class ExDAO {

	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	// INSERT
	public int insertEx(String title, String startdate, String enddate,
			String placename, int price) throws Exception {

		String sql = "INSERT INTO ex (exid, title, startdate, enddate, placeid, price)"
				+ " VALUES ((select nvl(max(exid), 0) + 1 from exhibition), ?, ?, ?, "
				+ "(select placeid from places where placename = ?), ?)";

		con = JdbcUtil.getConnection();

		ps = con.prepareStatement(sql);
		ps.setString(1,	title);
		ps.setString(2,	startdate);
		ps.setString(3,	enddate);
		ps.setString(4,	placename);
		ps.setInt(5, price);

		int result = ps.executeUpdate();

		System.out.println(result + " row created.");

		JdbcUtil.close(rs, ps, con);

		return result;
	}

	// DELETE
	public int deleteEx(String title) {

		String sql = "DELETE FROM ex WHERE lower(title) = ?";
		int result = 0;

		con = JdbcUtil.getConnection();

		try {

			ps = con.prepareStatement(sql);
			ps.setString(1,	title);
			result = ps.executeUpdate();

			System.out.println(result + " rows deleted.");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		JdbcUtil.close(rs, ps, con);

		return result;
	}

	// UPDATE
	public int updateEx(String title, String startdate, String enddate,
			String placename, int price) {

		String sql = "UPDATE ex SET startdate = ?, enddate = ?, "
				+ "placeid = (SELECT placeid FROM places WHERE placename = ?), "
				+ "price = ? " + 
				"WHERE exid = (SELECT exid FROM ex WHERE title = ?)";

		con = JdbcUtil.getConnection();

		try {

			ps = con.prepareStatement(sql);
			ps.setString(1,	startdate);
			ps.setString(2,	enddate);
			ps.setString(3,	placename);
			ps.setInt(4, price);
			ps.setString(5,	title);
			int result = ps.executeUpdate();

			System.out.println(result + " rows updated.");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		JdbcUtil.close(rs, ps, con);

		return price;
	}

	// 전체 조회
	public List<ExVO> getEx() {

		String sql = "SELECT * FROM ex ORDER BY exid";

		ExVO ex = null;
		List<ExVO> list = new ArrayList<ExVO>();

		con = JdbcUtil.getConnection();

		try {

			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while(rs.next()) {
				ex = new ExVO();

				ex.setExid(rs.getInt("exid"));
				ex.setTitle(rs.getString("title"));
				ex.setStartdate(rs.getString("startdate"));
				ex.setEnddate(rs.getString("enddate"));
				ex.setPlaceid(rs.getInt("placeid"));
				ex.setPrice(rs.getInt("price"));

				list.add(ex);

				// System.out.println(ex);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			JdbcUtil.close(rs, ps, con);
		}
		// System.out.println(list);


		return list;
	}

	// Search by title
	public List<ExVO> searchExByTitle(String title) {

		String sql = "SELECT * FROM ex WHERE lower(title) like ? ORDER BY exid";

		ExVO ex = null;
		List<ExVO> list = new ArrayList<ExVO>();

		con = JdbcUtil.getConnection();

		try {

			ps = con.prepareStatement(sql);
			ps.setString(1, "%" + title.toLowerCase() + "%");
			rs = ps.executeQuery();

			while(rs.next()) {
				ex = new ExVO();

				ex.setExid(rs.getInt("exid"));
				ex.setTitle(rs.getString("title"));
				ex.setStartdate(rs.getString("startdate"));
				ex.setEnddate(rs.getString("enddate"));
				ex.setPlaceid(rs.getInt("placeid"));
				ex.setPrice(rs.getInt("price"));

				list.add(ex);

				// System.out.println(ex);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			JdbcUtil.close(rs, ps, con);
		}

		return list;
	}

	// Search by date
	public List<ExVO> searchExByDate(String startdate, String sdate, String enddate, String edate) {

		String sql = "SELECT * FROM ex WHERE (startdate >= '2018.'||?||'.'||? "
				+ "AND enddate <= '2018.'||?||'.'||?)";

		ExVO ex = null;
		List<ExVO> list = new ArrayList<ExVO>();

		con = JdbcUtil.getConnection();

		try {

			ps = con.prepareStatement(sql);
			ps.setString(1, startdate);
			ps.setString(2, sdate);
			ps.setString(3, enddate);
			ps.setString(4, edate);
			rs = ps.executeQuery();

			while(rs.next()) {
				ex = new ExVO();

				ex.setExid(rs.getInt("exid"));
				ex.setTitle(rs.getString("title"));
				ex.setStartdate(rs.getString("startdate"));
				ex.setEnddate(rs.getString("enddate"));
				ex.setPlaceid(rs.getInt("placeid"));
				ex.setPrice(rs.getInt("price"));

				list.add(ex);

				// System.out.println(ex);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			JdbcUtil.close(rs, ps, con);
		}

		return list;
	}

	// Search by place
	public List<ExVO> searchExByPlace(String placename) {

		String sql = "SELECT exid, title, startdate, enddate, ex.placeid, price "
				+ "FROM ex, places "
				+ "WHERE ex.placeid = places.placeid AND lower(placename) like ?";

		ExVO ex = null;
		List<ExVO> list = new ArrayList<ExVO>();

		con = JdbcUtil.getConnection();

		try {

			ps = con.prepareStatement(sql);
			ps.setString(1, "%" + placename.toLowerCase() + "%");
			rs = ps.executeQuery();

			while(rs.next()) {
				ex = new ExVO();

				ex.setExid(rs.getInt("exid"));
				ex.setTitle(rs.getString("title"));
				ex.setStartdate(rs.getString("startdate"));
				ex.setEnddate(rs.getString("enddate"));
				ex.setPlaceid(rs.getInt("placeid"));
				ex.setPrice(rs.getInt("price"));

				list.add(ex);

				// System.out.println(ex);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			JdbcUtil.close(rs, ps, con);
		}

		return list;
	}


	// Search by locations
	public List<ExVO> searchExByLoc(String locname) {

		String sql = "SELECT * " + 
				"FROM ex e, places p, loc l " + 
				"WHERE e.placeid = p.placeid AND p.locid = l.locid "
				+ "AND lower(l.locname) like ?";

		ExVO ex = null;
		List<ExVO> list = new ArrayList<ExVO>();

		con = JdbcUtil.getConnection();

		try {

			ps = con.prepareStatement(sql);
			ps.setString(1, "%" + locname + "%");
			rs = ps.executeQuery();

			while(rs.next()) {
				ex = new ExVO();

				ex.setExid(rs.getInt("exid"));
				ex.setTitle(rs.getString("title"));
				ex.setStartdate(rs.getString("startdate"));
				ex.setEnddate(rs.getString("enddate"));
				ex.setPlaceid(rs.getInt("placeid"));
				ex.setPrice(rs.getInt("price"));

				list.add(ex);

				// System.out.println(ex);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			JdbcUtil.close(rs, ps, con);
		}

		return list;
	}
	
	public List<ExVO> search(String title, String startdate, String sdate, 
			String enddate, String edate, String placename) {
		
//		String sql = "SELECT exid, title, startdate, enddate, ex.placeid, price "
//				+ "FROM ex, places WHERE "
//				+ "lower(title) like ? "
//				+ "AND "
//				+ "((startdate <= '2018.'||?||'%' AND enddate >= '2018.'||?||'%')) "
//				+ "AND ex.placeid = places.placeid AND lower(placename) like ?";
		
		String sql = "SELECT exid, title, startdate, enddate, ex.placeid, price FROM ex, places "
				+ "WHERE "
				+ "lower(title) like ? "
				+ "AND "
				+ "(startdate >= '2018.'||?||'.'||? AND enddate <= '2018.'||?||'.'||?) "
				+ "AND "
				+ "(ex.placeid = places.placeid AND lower(placename) like ?)";
		
		ExVO ex = null;
		List<ExVO> list = new ArrayList<ExVO>();

		con = JdbcUtil.getConnection();

		try {

			ps = con.prepareStatement(sql);
			ps.setString(1, "%" + title.toLowerCase() + "%");
			ps.setString(2, startdate);
			ps.setString(3, sdate);
			ps.setString(4, enddate);
			ps.setString(5, edate);
			ps.setString(6, "%" + placename.toLowerCase() + "%");
			//ps.setString(4, "%" + placename.toLowerCase() + "%");
			rs = ps.executeQuery();

			while(rs.next()) {
				ex = new ExVO();

				ex.setExid(rs.getInt("exid"));
				ex.setTitle(rs.getString("title"));
				ex.setStartdate(rs.getString("startdate"));
				ex.setEnddate(rs.getString("enddate"));
				ex.setPlaceid(rs.getInt("placeid"));
				ex.setPrice(rs.getInt("price"));

				list.add(ex);

				// System.out.println(ex);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			JdbcUtil.close(rs, ps, con);
		}

		return list;
	}

	public String getStartDate(int exid) {

		String sql = "SELECT * FROM ex WHERE exid = ?";

		ExVO ex = null;
		con = JdbcUtil.getConnection();

		try {

			ps = con.prepareStatement(sql);
			ps.setInt(1, exid);
			rs = ps.executeQuery();

			while(rs.next()) {
				ex = new ExVO();
				ex.setStartdate(rs.getString("startdate"));

				// System.out.println(ex);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			JdbcUtil.close(rs, ps, con);
		}
		// System.out.println(list);


		return ex.getStartdate();
	}

	public String getEndDate(int exid) {

		String sql = "SELECT * FROM ex WHERE exid = ?";

		ExVO ex = null;
		con = JdbcUtil.getConnection();

		try {

			ps = con.prepareStatement(sql);
			ps.setInt(1, exid);
			rs = ps.executeQuery();

			while(rs.next()) {
				ex = new ExVO();
				ex.setEnddate(rs.getString("enddate"));

				// System.out.println(ex);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			JdbcUtil.close(rs, ps, con);
		}
		// System.out.println(list);


		return ex.getEnddate();
	}

	public String getTitle(int exid) {

		String sql = "SELECT * FROM ex WHERE exid = ?";

		ExVO ex = null;
		con = JdbcUtil.getConnection();

		try {

			ps = con.prepareStatement(sql);
			ps.setInt(1, exid);
			rs = ps.executeQuery();

			while(rs.next()) {
				ex = new ExVO();
				ex.setTitle(rs.getString("title"));

				// System.out.println(ex);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			JdbcUtil.close(rs, ps, con);
		}
		// System.out.println(list);


		return ex.getTitle();
	}
	
	public int getPlaceid(int exid) {

		String sql = "SELECT * FROM ex, places "
				+ "WHERE ex.placeid = places.placeid AND exid = ?";

		ExVO ex = null;
		con = JdbcUtil.getConnection();

		try {

			ps = con.prepareStatement(sql);
			ps.setInt(1, exid);
			rs = ps.executeQuery();

			while(rs.next()) {
				ex = new ExVO();
				ex.setPlaceid(rs.getInt("placeid"));

				// System.out.println(ex);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			JdbcUtil.close(rs, ps, con);
		}
		// System.out.println(list);


		return ex.getPlaceid();
	}
	
	public List<ExVO> getThisMonEx(int month) {
		
		String sql = "SELECT * FROM ex WHERE (startdate <= '2018.0'||?||'%' "
				+ "AND enddate >= '2018.0'||?||'%')";

		ExVO ex = null;
		List<ExVO> list = new ArrayList<ExVO>();

		con = JdbcUtil.getConnection();

		try {

			ps = con.prepareStatement(sql);
			ps.setInt(1, month);
			ps.setInt(2, (month+1));
			rs = ps.executeQuery();

			while(rs.next()) {
				ex = new ExVO();

				ex.setExid(rs.getInt("exid"));
				ex.setTitle(rs.getString("title"));
				ex.setStartdate(rs.getString("startdate"));
				ex.setEnddate(rs.getString("enddate"));
				ex.setPlaceid(rs.getInt("placeid"));
				ex.setPrice(rs.getInt("price"));

				list.add(ex);

				// System.out.println(ex);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			JdbcUtil.close(rs, ps, con);
		}

		return list;
	}
	
	public ExVO searchExById(int exid) {

		String sql = "SELECT * FROM ex WHERE exid = ?";

		ExVO ex = null;

		con = JdbcUtil.getConnection();

		try {

			ps = con.prepareStatement(sql);
			ps.setInt(1, exid);
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
			System.out.println(e.getMessage());
		} finally {
			JdbcUtil.close(rs, ps, con);
		}

		return ex;
	}
	
	public List<ExVO> getBestBr() {

		String sql = "SELECT ex.exid, title, startdate, enddate, placeid, price "
				+ "FROM ex, reviews where ex.exid = reviews.exid "
				+ "GROUP BY ex.exid, title, startdate, enddate, placeid, price "
				+ "ORDER BY avg(grade) DESC";

		ExVO ex = null;
		List<ExVO> list = new ArrayList<ExVO>();

		con = JdbcUtil.getConnection();

		try {

			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while(rs.next()) {
				ex = new ExVO();

				ex.setExid(rs.getInt("exid"));
				ex.setTitle(rs.getString("title"));
				ex.setStartdate(rs.getString("startdate"));
				ex.setEnddate(rs.getString("enddate"));
				ex.setPlaceid(rs.getInt("placeid"));
				ex.setPrice(rs.getInt("price"));

				list.add(ex);

				// System.out.println(rev);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			JdbcUtil.close(rs, ps, con);
		}

		return list;
	}
	
	public List<ExVO> getBestRes() {

		String sql = "SELECT ex.exid, title, startdate, enddate, placeid, price "
				+ "FROM ex, users where ex.exid = users.resexid "
				+ "GROUP BY ex.exid, title, startdate, enddate, placeid, price  "
				+ "ORDER BY count(resexid) DESC";

		ExVO ex = null;
		List<ExVO> list = new ArrayList<ExVO>();

		con = JdbcUtil.getConnection();

		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while(rs.next()) {
				ex = new ExVO();

				ex.setExid(rs.getInt("exid"));
				ex.setTitle(rs.getString("title"));
				ex.setStartdate(rs.getString("startdate"));
				ex.setEnddate(rs.getString("enddate"));
				ex.setPlaceid(rs.getInt("placeid"));
				ex.setPrice(rs.getInt("price"));

				list.add(ex);

				// System.out.println(rev);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			JdbcUtil.close(rs, ps, con);
		}

		return list;
	}

}
