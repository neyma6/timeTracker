package com.expedia.sol.dao.impl;

import java.lang.reflect.Constructor;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	@SuppressWarnings("unchecked")
	public List<Status> getStatus(String name, TimeInterval interval) {
		Session session = sessionFactory.openSession();
		System.out.println(interval.getStart() + " " + interval.getEnd());
		
		List<List<Object>> result = session.createQuery("select s.id, s.name, s.description, s.time, s.timestamp from Status as s where s.name = :name and s.timestamp between :start and :end")
			.setParameter("start", interval.getStart())
			.setParameter("end", interval.getEnd())
			.setParameter("name", name)
			.setResultTransformer(Transformers.TO_LIST)
			.list();
		
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
