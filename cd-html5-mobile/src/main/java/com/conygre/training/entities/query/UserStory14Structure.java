package com.conygre.training.entities.query;

public class UserStory14Structure {
	
	private String imsi;
	private int count;
	private double causeCode;
	private double eventId;
	private String description;

	public UserStory14Structure(String imsi, int count, double causeCode, double eventId, String description) {
		this.imsi = imsi;
		this.count = count;
		this.causeCode = causeCode;
		this.eventId = eventId;
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

	public double getCauseCode() {
		return causeCode;
	}

	public void setCauseCode(double causeCode) {
		this.causeCode = causeCode;
	}

	public double getEventId() {
		return eventId;
	}

	public void setEventId(double eventId) {
		this.eventId = eventId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
