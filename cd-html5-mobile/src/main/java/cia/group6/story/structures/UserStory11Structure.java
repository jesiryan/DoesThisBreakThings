package cia.group6.story.structures;

public class UserStory11Structure {

	private int totalFails ;	
	int MCC; 
	int MNC;
	int cellID;
	String Country;
	String Operator;
	int class0;
	int class1;
	int class2;
	int class3;
	int class4;	

	public UserStory11Structure(int totalFails, int mCC, int mNC,
			int cellID, String country, String operator, int class0,
			int class1, int class2, int class3, int class4) {
		//super();
		this.totalFails = totalFails;
		this.MCC = mCC;
		this.MNC = mNC;
		this.cellID = cellID;
		this.Country = country;
		this.Operator = operator;
		this.class0 = class0;
		this.class1 = class1;
		this.class2 = class2;
		this.class3 = class3;
		this.class4 = class4;
	}
	public int getClass0() {
		return class0;
	}
	public void setClass0(int class0) {
		this.class0 = class0;
	}
	public int getClass1() {
		return class1;
	}
	public void setClass1(int class1) {
		this.class1 = class1;
	}
	public int getClass2() {
		return class2;
	}
	public void setClass2(int class2) {
		this.class2 = class2;
	}
	public int getClass3() {
		return class3;
	}
	public void setClass3(int class3) {
		this.class3 = class3;
	}
	public int getClass4() {
		return class4;
	}
	public void setClass4(int class4) {
		this.class4 = class4;
	}
	public int getTotalFails() {
		return totalFails;
	}
	public void setTotalFails(int totalFails) {
		this.totalFails = totalFails;
	}
	public int getMCC() {
		return MCC;
	}
	public void setMCC(int mCC) {
		this.MCC = mCC;
	}
	public int getMNC() {
		return MNC;
	}
	public void setMNC(int mNC) {
		this.MNC = mNC;
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



}
