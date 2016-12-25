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
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "time")
	private String time;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "timestamp")
	private long timestamp;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
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
				+ timestamp + "]";
	}
	
	
}
