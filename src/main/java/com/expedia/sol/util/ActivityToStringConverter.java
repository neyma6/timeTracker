package com.expedia.sol.util;

import java.util.ArrayList;
import java.util.List;

import com.expedia.sol.domain.Activity;

public class ActivityToStringConverter {
	
	public static List<String> getTasks(List<Activity> activities) {
		List<String> tasks = new ArrayList<>();
		for (Activity act : activities) {
			tasks.add(act.getName());
		}
		return tasks;
	}
}
