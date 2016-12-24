package com.expedia.sol.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.expedia.sol.dao.IDBAccessor;
import com.expedia.sol.dao.domain.TimeInterval;
import com.expedia.sol.domain.Status;

public class HibernateDbAccessor implements IDBAccessor {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean save(Status status) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(status);
		tx.commit();
		session.close();
		return false;
	}

	@Override
	public List<Status> getStatus(TimeInterval interval) {
		// TODO Auto-generated method stub
		return null;
	}

}
