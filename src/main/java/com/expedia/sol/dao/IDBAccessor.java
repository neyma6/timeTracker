package com.expedia.sol.dao;

import java.util.List;

public interface IDBAccessor<T, R extends DBRequest> {

	boolean save(T type);
	boolean update(T type);
	boolean delete(R request);
	T getById(R request);
	List<T> get(R request);
}
