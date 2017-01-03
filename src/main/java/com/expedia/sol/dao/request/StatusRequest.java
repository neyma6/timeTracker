package com.expedia.sol.dao.request;

import com.expedia.sol.dao.DBRequest;
import com.expedia.sol.dao.domain.TimeInterval;
import com.expedia.sol.domain.Status;

public class StatusRequest implements DBRequest<Status> {

	private final String name;
	private final TimeInterval interval;
	private final int id;
	
	public StatusRequest(String name, TimeInterval interval) {
		this.name = name;
		this.interval = interval;
		this.id = 0;
	}
	
	public StatusRequest(int id) {
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

	@Override
	public Status createEntity() {
		Status status = new Status();
		status.setId(this.id);
		return status;
	}
	
	

}
