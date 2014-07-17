package com.example.mydemo.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

public class DateUtils {

	public static Calendar getCalendarFromDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	public static int getYearFromDate(Date date) {
		Calendar cal = getCalendarFromDate(date);
		int year = cal.get(Calendar.YEAR);
		return year;
	}

	public static int getMonthFromDate(Date date) {
		Calendar cal = getCalendarFromDate(date);
		int month = cal.get(Calendar.MONTH) + 1;
		return month;
	}

	public static int getDayFromDate(Date date) {
		Calendar cal = getCalendarFromDate(date);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		return day;
	}

	public static int[] getYearOld(Calendar tmp, String userBirthDay) {
		int num = Integer.parseInt(userBirthDay);
		LinkedList<Integer> digits = new LinkedList<Integer>();
		for (int i = 0; i < 4; i++) {
			digits.add(i, num % 100);
			num = num / 100;
		}
		int daybrith = digits.get(0);
		int montbrith = digits.get(1);
		int yearbrith = digits.get(3) * 100 + digits.get(2);
		
		int year = tmp.get(Calendar.YEAR);
		int month = tmp.get(Calendar.MONTH)+1;
		int day = tmp.get(Calendar.DAY_OF_MONTH);
		
		int[] rs = new int[2];
		int diffY = 0,diffM = 0,diffD = 0;
		diffY = year - yearbrith;
		if(month>montbrith){
			diffM = month - montbrith;
		}else if(month<montbrith){
			diffM = month+12-montbrith;
			diffY--;
		}else{
			if(day>daybrith){
				diffD = day- daybrith;
			}else if(day<daybrith){
				month--;
			}
		}
		
		rs[0] = diffM;
		rs[1] = diffY;
		
		
		return rs;
	}

}
