package com.expedia.sol.dao.impl;

import java.util.List;

import com.expedia.sol.dao.IDBAccessor;
import com.expedia.sol.dao.domain.TimeInterval;
import com.expedia.sol.domain.Status;

public class DummyDbAccessor implements IDBAccessor {

	@Override
	public boolean save(Status status) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Status> getStatus(TimeInterval interval) {
		// TODO Auto-generated method stub
		return null;
	}

}
