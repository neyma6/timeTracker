package com.expedia.sol.domain;

public class Status {

	private String name;
	private double time;
	private String description;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getTime() {
		return time;
	}
	public void setTime(double time) {
		this.time = time;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "Status [name=" + name + ", time=" + time + ", description=" + description + "]";
	}
	
	
}
