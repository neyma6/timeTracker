package com.expedia.sol.dao;

import java.util.List;

import com.expedia.sol.dao.domain.TimeInterval;
import com.expedia.sol.domain.Status;

public interface IDBAccessor {

	boolean save(Status status);
	List<Status> getStatus(TimeInterval interval);
}
