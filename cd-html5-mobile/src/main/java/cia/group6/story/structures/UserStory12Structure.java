package cia.group6.story.structures;

public class UserStory12Structure {

	private String imsi;
	private int count ;
	private int class0;
	private int class1;
	private int class2;
	private int class3;
	private int class4;
	
	public UserStory12Structure(String imsi, int count, int class0, int class1, int class2, int class3, int class4) {
		
		this.setClass0(class0);
		this.setClass1(class1);
		this.setClass2(class2);
		this.setClass3(class3);
		this.setClass4(class4);
		this.imsi = imsi;
		this.count = count;
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



}
