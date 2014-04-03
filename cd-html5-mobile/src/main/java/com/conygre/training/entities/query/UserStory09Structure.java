package com.conygre.training.entities.query;

public class UserStory09Structure {
	
	private String imsi;
	private int count ;
	private int duration;
	
	public UserStory09Structure(String imsi, int count, int duration) {
		this.imsi = imsi;
		this.count = count;
		this.duration = duration;
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

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

}
