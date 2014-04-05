package cia.group6.story.structures;

public class UserStory05Structure {
	
	private String start;
	private String end;
	private String imsi;
	private int count ;
	
	public UserStory05Structure(String start, String end, String imsi, int count) {
		this.start = start;
		this.end = end;
		this.imsi = imsi;
		this.count = count;
	}
	
	
	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
