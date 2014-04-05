package com.conygre.training.entities.query;

public class UserStory14Structure {
	
	private String imsi;
	private int count;
	private String causeCode;
	private String eventId;

	public UserStory14Structure(String imsi, int count, String causeCode, String eventId) {
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

	public String getCauseCode() {
		return causeCode;
	}

	public void setCauseCode(String causeCode) {
		this.causeCode = causeCode;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

}
