package com.expedia.sol.dao.request;

import com.expedia.sol.dao.DBRequest;
import com.expedia.sol.dao.domain.TimeInterval;

public class ListStatusRequest implements DBRequest {

	private final String name;
	private final TimeInterval interval;
	private final int id;
	
	public ListStatusRequest(String name, TimeInterval interval) {
		this.name = name;
		this.interval = interval;
		this.id = 0;
	}
	
	public ListStatusRequest(int id) {
		this.id = id;
		this.name = "";
		this.interval = TimeInterval.EMPTY;
	}

	public String getName() {
		return name;
	}

	public TimeInterval getInterval() {
		return interval;
	}

	public int getId() {
		return id;
	}
	
	

}
