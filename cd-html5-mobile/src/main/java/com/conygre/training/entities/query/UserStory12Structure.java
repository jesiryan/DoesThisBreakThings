package com.conygre.training.entities.query;

public class UserStory12Structure {

	private String imsi;
	private int count ;
	
	public UserStory12Structure(String imsi, int count) {

		this.imsi = imsi;
		this.count = count;
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
