package com.conygre.training.entities.query;

public class UserStory13Structure {

	private int totalFails;
	private int percentOfTotal;
	private int cellID;
	private String Country;
	private String Operator;

	public UserStory13Structure(int totalFails, int percentOfTotal, int cellID,
			String country, String operator) {
		super();
		this.totalFails = totalFails;
		this.cellID = cellID;
		this.Country = country;
		this.Operator = operator;
	}

	public int getTotalFails() {
		return totalFails;
	}

	public void setTotalFails(int totalFails) {
		this.totalFails = totalFails;
	}

	public int getCellID() {
		return cellID;
	}

	public void setCellID(int cellID) {
		this.cellID = cellID;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		this.Country = country;
	}

	public String getOperator() {
		return Operator;
	}

	public void setOperator(String operator) {
		this.Operator = operator;
	}

	public int getPercentOfTotal() {
		return percentOfTotal;
	}

	public void setPercentOfTotal(int percentOfTotal) {
		this.percentOfTotal = percentOfTotal;
	}

}
