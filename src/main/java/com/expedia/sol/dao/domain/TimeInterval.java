package com.expedia.sol.dao.domain;

import java.time.LocalTime;

public class TimeInterval {

	private final long start;
	private final long end;
	
	public TimeInterval(long start, long end) {
		this.start = start;
		this.end = end;
	}

	public long getStart() {
		return start;
	}

	public long getEnd() {
		return end;
	}
	
	
}
