package com.expedia.sol.dao.impl;

import com.expedia.sol.dao.DBRequest;
import com.expedia.sol.dao.domain.TimeInterval;

public class ListStatusRequest implements DBRequest {

	private final String name;
	private final TimeInterval interval;
	
	public ListStatusRequest(String name, TimeInterval interval) {
		this.name = name;
		this.interval = interval;
	}

	public String getName() {
		return name;
	}

	public TimeInterval getInterval() {
		return interval;
	}

}
