package cia.group6.story.structures;

public class UserStory06Structure {
	
	private String imsi;
	private double cause_causeCode;
	private double cause_eventid;
	private String description;

	public UserStory06Structure(String imsi, double cause_causeCode, double cause_eventid, String description) {
		this.imsi = imsi;
		this.cause_causeCode = cause_causeCode;
		this.cause_eventid = cause_eventid;
		this.description = description;
	}
	
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public double getCause_causeCode() {
		return cause_causeCode;
	}
	public void setCause_causeCode(double cause_causeCode) {
		this.cause_causeCode = cause_causeCode;
	}
	public double getCause_eventid() {
		return cause_eventid;
	}
	public void setCause_eventid(double cause_eventid) {
		this.cause_eventid = cause_eventid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
