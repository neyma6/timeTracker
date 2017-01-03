package com.expedia.sol.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;

import com.expedia.sol.dao.IDBAccessor;
import com.expedia.sol.dao.domain.TimeInterval;
import com.expedia.sol.domain.Status;
import com.expedia.sol.util.DateFormatter;

public class StatusHibernateDbAccessor implements IDBAccessor<Status, ListStatusRequest> {

	private static final String SELECT_STATUS_WITH_NAME = "select s.id, s.name, s.description, s.time, s.timestamp from Status as s where s.name = :name and s.timestamp between :start and :end";
	private static final String SELECT_STATUS_WITHOUT_NAME = "select s.id, s.name, s.description, s.time, s.timestamp from Status as s where s.timestamp between :start and :end";
	private static final String DELETE_STATUS = "delete from Status as s where s.id = :id";
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean save(Status status) {
		boolean success = true;
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.save(status);
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
	@SuppressWarnings("unchecked")
	public List<Status> get(ListStatusRequest request) {
		Session session = sessionFactory.openSession();
		System.out.println(request.getInterval().getStart() + " " + request.getInterval().getEnd());
		
		String query;
		if (request.getName() != null) {
			query = SELECT_STATUS_WITH_NAME;
		} else {
			query = SELECT_STATUS_WITHOUT_NAME;
		}
		
		Query hibernateQuery = createQuery(session, query, request.getInterval());
		
		if (request.getName() != null) {
			hibernateQuery = hibernateQuery.setParameter("name", request.getName());
		}
		
		List<List<Object>> result = hibernateQuery.list();
		
		session.close();
		List<Status> statuses = new ArrayList<>();
		for (List<Object> list : result) {
			Status status = new Status();
			status.setId((int)list.get(0));
			status.setName((String)list.get(1));
			status.setDescription((String)list.get(2));
			status.setTime((String)list.get(3));
			status.setDisplayDate(DateFormatter.formateTimeStampToDate((long)list.get(4)));
			
			statuses.add(status);
		}
		return statuses;
	}
	
	@Override
	public Status getById(int id) {
		Session session = sessionFactory.openSession();
		Status status = (Status) session.get(Status.class, id);
		if (status != null) {
			status.setDisplayDate(DateFormatter.formateTimeStampToDate(status.getTimestamp()));
		} 
		session.close();
		return status;
	}
	
	@Override
	public boolean update(Status status) {
		boolean success = true;
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.update(status);
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
	public boolean delete(int id) {
		boolean success = true;
		try {
			Session session = sessionFactory.openSession();
			Query query = session.createQuery(DELETE_STATUS).setParameter("id", id);
			query.executeUpdate();
			session.close();
		} catch(Exception ex) {
			success = false;
		}
		return success;
	}
	
	private Query createQuery(Session session, String query, TimeInterval interval) {
		return session.createQuery(query)
				.setParameter("start", interval.getStart())
				.setParameter("end", interval.getEnd())
				.setResultTransformer(Transformers.TO_LIST);
	}


}
