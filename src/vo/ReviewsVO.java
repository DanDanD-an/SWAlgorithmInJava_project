package vo;

public class ReviewsVO {
	
	private int reviewid;
	private int exid;
	private String userid;
	private int grade;
	private String review;
	
	
	// Constructor
	public ReviewsVO() {
		super();
	}
	
	public ReviewsVO(int reviewid, int exid, String userid, int grade, String review) {
		super();
		this.reviewid = reviewid;
		this.exid = exid;
		this.userid = userid;
		this.grade = grade;
		this.review = review;
	}
	
	
	// setters & getters
	public int getReviewid() {
		return reviewid;
	}

	public void setReviewid(int reviewid) {
		this.reviewid = reviewid;
	}

	public int getExid() {
		return exid;
	}

	public void setExid(int exid) {
		this.exid = exid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	
	// toString()
	public String toString() {
		return "ReviewsVO [reviewid=" + reviewid + ", exid=" + exid + ", userid=" + userid + ", grade=" + grade
				+ ", review=" + review + "]";
	}
}
