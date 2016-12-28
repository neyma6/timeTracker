package com.expedia.sol.dao.impl;

import java.lang.reflect.Constructor;
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

public class HibernateDbAccessor implements IDBAccessor {

	private static final String SELECT_STATUS_WITH_NAME = "select s.id, s.name, s.description, s.time, s.timestamp from Status as s where s.name = :name and s.timestamp between :start and :end";
	private static final String SELECT_STATUS_WITHOUT_NAME = "select s.id, s.name, s.description, s.time, s.timestamp from Status as s where s.timestamp between :start and :end";
	private static final String DELETE_STATUS = "delete from Status as s where s.id = :id";
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean save(Status status) {
		boolean success = true;
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.save(status);
			tx.commit();
			session.close();
		} catch(Exception ex) {
			success = false;
		}
		return success;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Status> getStatus(String name, TimeInterval interval) {
		Session session = sessionFactory.openSession();
		System.out.println(interval.getStart() + " " + interval.getEnd());
		
		String query;
		if (name != null) {
			query = SELECT_STATUS_WITH_NAME;
		} else {
			query = SELECT_STATUS_WITHOUT_NAME;
		}
		
		Query hibernateQuery = createQuery(session, query, interval);
		
		if (name != null) {
			hibernateQuery = hibernateQuery.setParameter("name", name);
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
	public Status getStatusById(int id) {
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
		// TODO Auto-generated method stub
		return false;
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
	
	public static <T> T map(Class<T> type, Object[] tuple){
		   List<Class<?>> tupleTypes = new ArrayList<>();
		   for(Object field : tuple){
		      tupleTypes.add(field.getClass());
		   }
		   try {
		      Constructor<T> ctor = type.getConstructor(tupleTypes.toArray(new Class<?>[tuple.length]));
		      return ctor.newInstance(tuple);
		   } catch (Exception e) {
		      throw new RuntimeException(e);
		   }
		}

	

	

}
