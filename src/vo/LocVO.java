package vo;

import java.io.Serializable;

public class LocVO extends Object implements Serializable {

	private int locid;
	private String locname;
	
	// Contructor
	public LocVO() {
		super();
	}
	
	public LocVO(int locid, String locname) {
		super();
		this.locid = locid;
		this.locname = locname;
	}

	// setters & getters
	public int getLocid() {
		return locid;
	}

	public void setLocid(int locid) {
		this.locid = locid;
	}

	public String getLocname() {
		return locname;
	}

	public void setLocname(String locname) {
		this.locname = locname;
	}

	
	// toString()
	public String toString() {
		return "LocVO [locid=" + locid + ", locname=" + locname + "]";
	}
}
