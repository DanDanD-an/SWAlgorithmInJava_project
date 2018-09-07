package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import util.JdbcUtil;
import vo.ExVO;
import vo.ReviewsVO;

public class ReviewsDAO {

	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	// INSERT
	public int insertRev(int exid, String userid,
						int grade, String review) throws Exception {

		String sql = "INSERT INTO reviews (reviewid, exid, userid, grade, review) " + 
				"VALUES ((SELECT NVL(max(reviewid), 0) + 1 FROM reviews), ?, ?, ?, ?)";

		con = JdbcUtil.getConnection();

		ps = con.prepareStatement(sql);
		ps.setInt(1, exid);
		ps.setString(2,	userid);
		ps.setInt(3, grade);
		ps.setString(4,	review);

		int result = ps.executeUpdate();

		System.out.println(result + " row created.");

		JdbcUtil.close(rs, ps, con);

		return result;
	}

	// DELETE
	public int deleteRev(int reviewid) {

		String sql = "DELETE FROM reviews WHERE reviewid = ?";
		int result = 0;

		con = JdbcUtil.getConnection();

		try {

			ps = con.prepareStatement(sql);
			ps.setInt(1, reviewid);
			result = ps.executeUpdate();

			System.out.println(result + " rows deleted.");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		JdbcUtil.close(rs, ps, con);

		return result;
	}

	// UPDATE
	public int updateRev(int exid, String userid, int grade, String review) {

		String sql = "UPDATE reviews SET grade = ?, review = ? " + 
					"WHERE exid = ? AND userid = ?";

		con = JdbcUtil.getConnection();

		try {

			ps = con.prepareStatement(sql);
			ps.setInt(1, grade);
			ps.setString(2,	review);
			ps.setInt(3, exid);
			ps.setString(4, userid);
		
			int result = ps.executeUpdate();

			System.out.println(result + " rows updated.");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		JdbcUtil.close(rs, ps, con);

		return grade;
	}
	
	// Search by exid
	public List<ReviewsVO> searchRevByExid(int exid) {

		String sql = "SELECT * FROM reviews WHERE exid = ? ORDER BY reviewid";

		ReviewsVO rev = null;
		List<ReviewsVO> list = new ArrayList<ReviewsVO>();

		con = JdbcUtil.getConnection();

		try {

			ps = con.prepareStatement(sql);
			ps.setInt(1, exid);
			rs = ps.executeQuery();

			while(rs.next()) {
				rev = new ReviewsVO();

				rev.setReviewid(rs.getInt("reviewid"));
				rev.setExid(rs.getInt("exid"));
				rev.setUserid(rs.getString("userid"));
				rev.setGrade(rs.getInt("grade"));
				rev.setReview(rs.getString("review"));

				list.add(rev);

				// System.out.println(rev);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			JdbcUtil.close(rs, ps, con);
		}

		return list;
	}
	
	// Search by userid
	public List<ReviewsVO> searchRevByUserid(String userid) {

		String sql = "SELECT * FROM reviews WHERE lower(userid) like ? ORDER BY reviewid";

		ReviewsVO rev = null;
		List<ReviewsVO> list = new ArrayList<ReviewsVO>();

		con = JdbcUtil.getConnection();

		try {

			ps = con.prepareStatement(sql);
			ps.setString(1, "%" + userid.toLowerCase() + "%");
			rs = ps.executeQuery();

			while(rs.next()) {
				rev = new ReviewsVO();

				rev.setReviewid(rs.getInt("reviewid"));
				rev.setExid(rs.getInt("exid"));
				rev.setUserid(rs.getString("userid"));
				rev.setGrade(rs.getInt("grade"));
				rev.setReview(rs.getString("review"));

				list.add(rev);

				// System.out.println(rev);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			JdbcUtil.close(rs, ps, con);
		}

		return list;
	}

	public int getGradeNum(int exid) {
		String sql = "SELECT * FROM reviews " + 
					"WHERE exid = ?";

		ReviewsVO rev = null;
		List<ReviewsVO> list = new ArrayList<ReviewsVO>();
		int result = 0;

		con = JdbcUtil.getConnection();

		try {

			ps = con.prepareStatement(sql);
			ps.setInt(1, exid);
			rs = ps.executeQuery();

			while(rs.next()) {
				rev = new ReviewsVO();

				list.add(rev);
				
				result++;
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			JdbcUtil.close(rs, ps, con);
		}

		return result;
	}
	
	public double getAvgGr(int exid) {
		
		String sql = "SELECT * FROM reviews WHERE exid = ?";
		
		con = JdbcUtil.getConnection();
		int[] grade = new int[100];
		double result = 0.0;
		double res = 0.0;
		int i = 0;

		try {

			ps = con.prepareStatement(sql);
			ps.setInt(1, exid);
			rs = ps.executeQuery();

			while(rs.next()) {
				ReviewsVO rev = null;
				rev = new ReviewsVO();
				rev.setGrade(rs.getInt("grade"));
				
				grade[i++] = rev.getGrade();
			}
			
			for (int j = 0; j < i; j++) {
				result += grade[j];
			}
			
			result /= i;
			res = Double.parseDouble(String.format("%.1f", result));
			
			System.out.println(res);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			JdbcUtil.close(rs, ps, con);
		}
		
		return res;
	}
	
	
}
