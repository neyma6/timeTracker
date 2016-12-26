package com.expedia.sol.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {

	public static String formateTimeStampToDate(long timeStamp) {
		Date dateObj = new Date(timeStamp * 1000);
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		return df.format(dateObj);
	}
}
