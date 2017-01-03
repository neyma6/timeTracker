package com.expedia.sol.util;

import java.util.Comparator;

import com.expedia.sol.domain.Person;

public class PersonComparator implements Comparator<Person> {

	@Override
	public int compare(Person o1, Person o2) {
		return o1.getName().compareTo(o2.getName());
	}

}
