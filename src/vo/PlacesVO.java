package vo;

import java.io.Serializable;

public class PlacesVO extends Object implements Serializable {

	private int placeid;
	private String placename;
	private String closedday;
	private int locid;
	
	// Constructor
	public PlacesVO() {
		super();
	}

	public PlacesVO(int placeid, String placename, String closedday, int locid) {
		super();
		this.placeid = placeid;
		this.placename = placename;
		this.closedday = closedday;
		this.locid = locid;
	}

	// setters & getters
	public int getPlaceid() {
		return placeid;
	}

	public void setPlaceid(int placeid) {
		this.placeid = placeid;
	}

	public String getPlacename() {
		return placename;
	}

	public void setPlacename(String placename) {
		this.placename = placename;
	}

	public String getClosedday() {
		return closedday;
	}

	public void setClosedday(String closedday) {
		this.closedday = closedday;
	}

	public int getLocid() {
		return locid;
	}

	public void setLocid(int locid) {
		this.locid = locid;
	}


	// toString()
	public String toString() {
		return "PlacesVO [placeid=" + placeid + ", placename=" + placename + ", closedday=" + closedday + ", locid="
				+ locid + "]";
	}
}
