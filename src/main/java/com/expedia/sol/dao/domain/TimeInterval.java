package com.expedia.sol.dao.domain;

import java.time.LocalTime;

public class TimeInterval {

	private final LocalTime start;
	private final LocalTime end;
	
	public TimeInterval(LocalTime start, LocalTime end) {
		this.start = start;
		this.end = end;
	}

	public LocalTime getStart() {
		return start;
	}

	public LocalTime getEnd() {
		return end;
	}
	
	
}
