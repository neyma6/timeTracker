package com.expedia.sol.dao;

import java.util.List;

import com.expedia.sol.dao.domain.TimeInterval;
import com.expedia.sol.domain.Status;

public interface IDBAccessor {

	boolean save(Status status);
	boolean update(Status status);
	boolean delete(int id);
	Status getStatusById(int id);
	List<Status> getStatus(String name, TimeInterval interval);
}
