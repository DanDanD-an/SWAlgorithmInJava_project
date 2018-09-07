package service;

import java.util.List;

import dao.ExDAO;
import dao.LocDAO;
import dao.PlacesDAO;
import dao.ReviewsDAO;
import dao.UsersDAO;
import vo.ExVO;
import vo.PlacesVO;
import vo.ReviewsVO;
import vo.UsersVO;

public class ExServiceImpl implements ExService {

	private ExDAO exDao = new ExDAO();
	private PlacesDAO plDao = new PlacesDAO();
	private UsersDAO usDao = new UsersDAO();
	private ReviewsDAO revDao = new ReviewsDAO();
	private LocDAO locDao = new LocDAO();

	// Constructor
	public ExServiceImpl() {
		super();
	}
	
	public ExServiceImpl(ExDAO exDao, PlacesDAO plDao, LocDAO locDao, UsersDAO usDao, ReviewsDAO revDao) {
		super();
		this.exDao = exDao;
		this.plDao = plDao;
		this.locDao = locDao;
		this.usDao = usDao;
		this.revDao = revDao;
	}

	// setters & getters
	public ExDAO getDao() {
		return exDao;
	}

	public void setDao(ExDAO exDao) {
		this.exDao = exDao;
	}

	public ExDAO getExDao() {
		return exDao;
	}

	public void setExDao(ExDAO exDao) {
		this.exDao = exDao;
	}

	public PlacesDAO getPlDao() {
		return plDao;
	}

	public void setPlDao(PlacesDAO plDao) {
		this.plDao = plDao;
	}

	public UsersDAO getUsDao() {
		return usDao;
	}

	public void setUsDao(UsersDAO usDao) {
		this.usDao = usDao;
	}

	public ReviewsDAO getRevDao() {
		return revDao;
	}

	public void setRevDao(ReviewsDAO revDao) {
		this.revDao = revDao;
	}

	
	// methods
	// Exhibitions
	public int insertEx(String title, String startdate, String enddate, 
						String placename, int price) throws Exception {	
		return exDao.insertEx(title, startdate, enddate, placename, price);
	}

	public int deleteEx(String title) {
		return exDao.deleteEx(title);
	}

	public int updateEx(String title, String startdate, String enddate, 
						String placename, int price) {
		return exDao.updateEx(title, startdate, enddate, placename, price);
	}

	public List<ExVO> getEx() {
		return  exDao.getEx();
	}
	
	public String getStartDate(int exid) {
		return exDao.getStartDate(exid);
	}

	public List<ExVO> searchExByTitle(String title) {
		return exDao.searchExByTitle(title);
	}

	

	public List<ExVO> searchExByPlace(String placename) {
		return exDao.searchExByPlace(placename);
	}
	
	public List<ExVO> searchExByLoc(String locname) {
		return exDao.searchExByLoc(locname);
	}

	// Places
	public int insertPl(int placeid, String placename, String closedday, int locid) throws Exception {
		return plDao.insertPl(placeid, placename, closedday, locid);
	}

	public int deletePl(String placename) {	
		return plDao.deletePl(placename);
	}

	public String updatePl(String placename, String closedday) {
		
		return plDao.updatePl(placename, closedday);
	}

	public List<PlacesVO> getPl() {

		return plDao.getPl();
	}


	// Users
	public int insertUser(String userid, String pw) throws Exception {
		return usDao.insertUser(userid, pw);
	}

	public boolean logIn(String userid, String pw) {

		return usDao.logIn(userid, pw);
	}

	public int reserve(String userid, int resexid) throws Exception {
		return usDao.reserve(userid, resexid);
	}
	

	// Reviews
	public int insertRev(int exid, String userid, int grade, String review) throws Exception {
		return revDao.insertRev(exid, userid, grade, review);
	}

	public int deleteRev(int reviewid) {
		return revDao.deleteRev(reviewid);
	}
	
	public int updateRev(int exid, String userid, int grade, String review) {
		return revDao.updateRev(exid, userid, grade, review);
	}
	
	public List<ReviewsVO> searchRevByExid(int exid) {
		return revDao.searchRevByExid(exid);
	}
	
	public List<ReviewsVO> searchRevByUserid(String userid) {
		return revDao.searchRevByUserid(userid);
	}

	public String getEndDate(int exid) {
		return exDao.getEndDate(exid);
	}

	public String getTitle(int exid) {
		return exDao.getTitle(exid);
	}
	
	public int getPlaceid(int exid) {
		return exDao.getPlaceid(exid);
	}

	public String getPlacename(int placeid) {
		return plDao.getPlacename(placeid);
	}

	public String getLocname(int locid) {
		return locDao.getLocname(locid);
	}

	public List<ExVO> getThisMonEx(int month) {
		return exDao.getThisMonEx(month);
	}

	public ExVO searchExById(int exid) {
		return exDao.searchExById(exid);
	}

	public ExVO chkRes(String userid) {
		return usDao.chkRes(userid);
	}

	public int getGradeNum(int exid) {
		return revDao.getGradeNum(exid);
	}

	public double getAvgGr(int exid) {
		return revDao.getAvgGr(exid);
	}

	public List<ExVO> searchExByDate(String startdate, String sdate, String enddate, String edate) {
		return exDao.searchExByDate(startdate, sdate, enddate, edate);
	}

	public List<ExVO> search(String title, String startdate, String sdate, String enddate, String edate,
			String placename) {
		return exDao.search(title, startdate, sdate, enddate, edate, placename);
	}

	public List<ExVO> getBestBr() {
		return exDao.getBestBr();
	}

	public List<ExVO> getBestRes() {
		return exDao.getBestRes();
	}

	
	
}
