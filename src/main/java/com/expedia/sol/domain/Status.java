package com.expedia.sol.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STATUS")
public class Status {

	@Id
    @GeneratedValue
    @Column(name = "ID")
	private int id;
	
	private String name;
	private double time;
	private String description;
	private long nanotime;

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
	public long getNanotime() {
		return nanotime;
	}
	public void setNanotime(long nanotime) {
		this.nanotime = nanotime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Status [id=" + id + ", name=" + name + ", time=" + time + ", description=" + description + ", nanotime="
				+ nanotime + "]";
	}
	
	
}
