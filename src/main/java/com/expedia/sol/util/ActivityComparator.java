package com.expedia.sol.util;

import java.util.Comparator;

import com.expedia.sol.domain.Activity;

public class ActivityComparator implements Comparator<Activity> {

	@Override
	public int compare(Activity arg0, Activity arg1) {
		return arg0.getName().compareTo(arg1.getName());
	}
}
