package com.conygre.training.entities.query;

public class UserStory08Structure {
	
	private String model;
	private int count ;
	private String start;
	private String end;
	
	public UserStory08Structure(String model, int count, String start, String end) {

		this.model = model;
		this.count = count;
		this.start = start;
		this.end = end;		
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

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	

}