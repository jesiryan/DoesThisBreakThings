package com.conygre.training.entities.query;

public class UserStory07Structure {
	
	private String imsi;
	private String dateTime ;
	private String description;
	
	public UserStory07Structure(String imsi, String dateTime, String description) {
		this.imsi = imsi;
		this.dateTime = dateTime;
		this.description = description;
	}
	
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
