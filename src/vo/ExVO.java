package vo;

import java.io.Serializable;

public class ExVO extends Object implements Serializable {

	private int exid;
	private String title;
	private String startdate;
	private String enddate;
	private int placeid;
	private int price;
	
	// Contructor
	public ExVO() {
		super();
	}
	
	public ExVO(int exid, String title, String startdate, String enddate, int placeid, int price) {
		super();
		this.exid = exid;
		this.title = title;
		this.startdate = startdate;
		this.enddate = enddate;
		this.placeid = placeid;
		this.price = price;
	}

	// setters & getters
	public int getExid() {
		return exid;
	}

	public void setExid(int exid) {
		this.exid = exid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public int getPlaceid() {
		return placeid;
	}

	public void setPlaceid(int placeid) {
		this.placeid = placeid;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}


	// toString()
	public String toString() {
		return "exVO [exid=" + exid + ", title=" + title + ", startdate=" + startdate + ", enddate=" + enddate
				+ ", placeid=" + placeid + ", price=" + price + "]";
	}
}
