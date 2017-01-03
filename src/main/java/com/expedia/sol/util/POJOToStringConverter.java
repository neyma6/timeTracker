package com.expedia.sol.util;

import java.util.ArrayList;
import java.util.List;

import com.expedia.sol.domain.Activity;
import com.expedia.sol.domain.Person;

// nice copy pasting...
public class POJOToStringConverter {
	
	public static List<String> getTasks(List<Activity> activities) {
		List<String> tasks = new ArrayList<>();
		for (Activity act : activities) {
			tasks.add(act.getName());
		}
		return tasks;
	}
	
	public static List<String> getPersons(List<Person> persons) {
		List<String> personList = new ArrayList<>();
		for (Person p : persons) {
			personList.add(p.getName());
		}
		return personList;
	}
}
