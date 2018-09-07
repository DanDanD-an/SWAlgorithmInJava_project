package vo;

import java.io.Serializable;

public class UsersVO extends Object implements Serializable {

	private String userid;
	private String pw;
	private int resid;
	
	// Constructors
	public UsersVO() {
		super();
	}
	
	public UsersVO(String userid, String pw, int resid) {
		super();
		this.userid = userid;
		this.pw = pw;
		this.resid = resid;
	}

	// setters & getters
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public int getResid() {
		return resid;
	}

	public void setResid(int resid) {
		this.resid = resid;
	}

	
	// toString()
	public String toString() {
		return "UsersVO [userid=" + userid + ", pw=" + pw + ", resid=" + resid + "]";
	}
}
