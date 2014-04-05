package com.conygre.training.entities.query;

public class UserStory08Structure {
	
	private String model;
	private int count ;

	public UserStory08Structure(String model, int count) {

		this.model = model;
		this.count = count;
		
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