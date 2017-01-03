package com.expedia.sol.dao.request;

import com.expedia.sol.dao.DBRequest;
import com.expedia.sol.domain.Activity;

public class ActivityDbRequest implements DBRequest<Activity> {

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

	@Override
	public Activity createEntity() {
		Activity activity = new Activity();
		activity.setName(this.name);
		return activity;
	}

}
