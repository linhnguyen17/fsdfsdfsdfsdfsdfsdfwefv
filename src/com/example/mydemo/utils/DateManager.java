package com.example.mydemo.utils;

import java.util.Calendar;
import java.util.Date;

public class DateManager {

	public static Date getCurrentDateZeroTime() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
}
