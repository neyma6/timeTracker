package com.expedia.sol.dao;

import java.util.List;

public interface IDBAccessor<T, R extends DBRequest> {

	boolean save(T type);
	boolean update(T type);
	boolean delete(int id);
	T getById(int id);
	List<T> get(R request);
}
