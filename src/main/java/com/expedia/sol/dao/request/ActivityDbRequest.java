package com.expedia.sol.dao.request;

import com.expedia.sol.dao.DBRequest;

public class ActivityDbRequest implements DBRequest {

	private final String name;

	public ActivityDbRequest(String name) {
		this.name = name;
	}
	
	public ActivityDbRequest() {
		this.name = "";
	}

	public String getName() {
		return name;
	}

}
