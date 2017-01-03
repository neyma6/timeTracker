package com.expedia.sol.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.expedia.sol.dao.DBRequest;
import com.expedia.sol.dao.IDBAccessor;

public abstract class AbstractHibernateDbAccessor<T, R extends DBRequest> implements IDBAccessor<T, R> {

	@Autowired
	protected SessionFactory sessionFactory;
	
	@Override
	public boolean save(T type) {
		boolean success = true;
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.save(type);
		} catch(Exception ex) {
			ex.printStackTrace();
			success = false;
		} finally {
			try {
				tx.commit();
				session.close();
			} catch(Exception ex) {
				ex.printStackTrace();
				success = false;
			}
		}
		return success;
	}

	@Override
	public boolean update(T type) {
		boolean success = true;
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.update(type);
		} catch(Exception ex) {
			ex.printStackTrace();
			success = false;
		} finally {
			try {
				tx.commit();
				session.close();
			} catch(Exception ex) {
				ex.printStackTrace();
				success = false;
			}
		}
		return success;
	}

}
