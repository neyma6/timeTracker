package com.expedia.sol.dao.impl;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;

import com.expedia.sol.dao.request.PersonDbRequest;
import com.expedia.sol.domain.Person;
import com.expedia.sol.util.PersonComparator;

public class PersonHibernateDbAccessor extends AbstractHibernateDbAccessor<Person, PersonDbRequest> {

	@Override
	public Person getById(PersonDbRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Person> get(PersonDbRequest request) {
		Session session = sessionFactory.openSession();
		List<Person> persons = session.createCriteria(Person.class).list();
		session.close();
		Collections.sort(persons, new PersonComparator());
		return persons;
	}

}
