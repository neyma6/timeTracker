package com.expedia.sol.dao.impl;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;

import com.expedia.sol.dao.request.ActivityDbRequest;
import com.expedia.sol.domain.Activity;
import com.expedia.sol.util.ActivityComparator;

public class ActivityHibernateDbAccessor extends AbstractHibernateDbAccessor<Activity, ActivityDbRequest> {

	@Override
	public Activity getById(ActivityDbRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Activity> get(ActivityDbRequest request) {
		Session session = sessionFactory.openSession();
		List<Activity> activities = session.createCriteria(Activity.class).list();
		session.close();
		Collections.sort(activities, new ActivityComparator());
		return activities;
	}

}
