package com.expedia.sol.dao.util;

import static java.time.DayOfWeek.SUNDAY;
import static java.time.temporal.TemporalAdjusters.next;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import com.expedia.sol.dao.domain.TimeInterval;
import com.expedia.sol.util.DateFormatter;

public class TimeIntervalUtil {

	private static final int DAYS = 7;
	
	public static TimeInterval getTimeInterval(int week) {
		
		LocalDate today = LocalDate.now();
		LocalDate sunday;
		if (today.getDayOfWeek().getValue() == 7) {
			sunday = today;
		} else {
			sunday = today.with(next(SUNDAY));
		}
		
		int daysBack = DAYS * week;
		LocalDate monday = sunday.minusDays(daysBack - 1);
		LocalDate nextSunday = monday.plusDays(DAYS - 1);
		
		ZoneId zoneId = ZoneId.systemDefault(); 
		long mondayEpoch = monday.atStartOfDay(zoneId).toEpochSecond();
		long sundayEpoch = nextSunday.atStartOfDay(zoneId).toEpochSecond();
		
		return new TimeInterval(mondayEpoch, sundayEpoch);
	}
	
	public static List<String> getDisplayableTimeIntervalList(int numberOfWeeks) {
		List<String> weeks = new ArrayList<>();
		
		for (int i = 0; i < numberOfWeeks; i++) {
			TimeInterval interval = getTimeInterval(i + 1);
			weeks.add(DateFormatter.formateTimeStampToDate(interval.getStart()) + " - " 
			+ DateFormatter.formateTimeStampToDate(interval.getEnd()));
		}
		
		return weeks;
	}
}
