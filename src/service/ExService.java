package service;

import java.util.List;

import vo.ExVO;
import vo.PlacesVO;
import vo.ReviewsVO;

public interface ExService {

	// Ex
	public int insertEx(String title, String startdate, String enddate,
						String placename, int price) throws Exception;
	
	public int deleteEx(String title);
	
	public int updateEx(String title, String startdate, String enddate,
			String placename, int price);
	
	public List<ExVO> getEx();
	public String getTitle(int exid);
	public String getStartDate(int exid);
	public String getEndDate(int exid);
	public int getPlaceid(int exid);
	public List<ExVO> getThisMonEx(int month);
	public List<ExVO> getBestBr();
	public List<ExVO> getBestRes();
	
	public List<ExVO> searchExByTitle(String title);	
	public List<ExVO> searchExByDate(String startdate, String sdate, String enddate, String edate);
	public List<ExVO> searchExByPlace(String placename);
	public List<ExVO> searchExByLoc(String locname);
	public ExVO searchExById(int exid);
	public List<ExVO> search(String title, String startdate, String sdate, 
			String enddate, String edate, String placename);
	
	
	
	// Places
	public int insertPl(int placeid, String placename, String closedday, int locid) throws Exception;
	public int deletePl(String placename);
	public String updatePl(String placename, String closedday);
	public List<PlacesVO> getPl();
	public String getPlacename(int placeid);
	
	
	// Locations
	public String getLocname(int locid);
	
	// Users
	public int insertUser(String userid, String pw) throws Exception;
	public boolean logIn(String userid, String pw);
	public int reserve(String userid, int resid) throws Exception;
	public ExVO chkRes(String userid);
	
	// Reviews
	public int insertRev(int exid, String userid, 
							int grade, String review) throws Exception;
	public int deleteRev(int reviewid);
	public int updateRev(int exid, String userid, int grade, String review);
	public List<ReviewsVO> searchRevByExid(int exid);
	public List<ReviewsVO> searchRevByUserid(String userid);
	public int getGradeNum(int exid);
	public double getAvgGr(int exid);
	
}
