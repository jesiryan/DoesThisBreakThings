package cia.group6.story.structures;

public class UserStory10Structure {	
	
	private double causecode;
	private double eventId;
	private String causeDescription;
	private int count;
			
	public UserStory10Structure(double causecode, double eventId,
			String causeDescription, int count) {
		this.causecode = causecode;
		this.eventId = eventId;
		this.causeDescription = causeDescription;
		this.count = count;
	}
	public double getCausecode() {
		return causecode;
	}
	public void setCausecode(double causecode) {
		this.causecode = causecode;
	}
	public double getEventId() {
		return eventId;
	}
	public void setEventId(double eventId) {
		this.eventId = eventId;
	}
	public String getCauseDescription() {
		return causeDescription;
	}
	public void setCauseDescription(String causeDescription) {
		this.causeDescription = causeDescription;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	

}
