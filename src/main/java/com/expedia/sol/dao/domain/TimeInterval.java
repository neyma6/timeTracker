package com.expedia.sol.dao.domain;

public class TimeInterval {

	public static final TimeInterval EMPTY = new TimeInterval(0, 0);
	
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
