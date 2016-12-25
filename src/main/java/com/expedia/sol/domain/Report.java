package com.expedia.sol.domain;

public class Report {

	private String name;
	private int week;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getWeek() {
		return week;
	}
	public void setWeek(int week) {
		this.week = week;
	}
	
	@Override
	public String toString() {
		return "Report [name=" + name + ", week=" + week + "]";
	}
	
	
}
