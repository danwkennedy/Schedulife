package com.lmaas.schedulife;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTimeFormatters {

	private static DateFormat _dateFormat;
	private static DateFormat _timeFormat;
	
	public static DateFormat getDefaultDateFormat() {
		if (_dateFormat == null) {
			_dateFormat = DateFormat.getDateInstance(SimpleDateFormat.MEDIUM, Locale.getDefault());
		}
		return _dateFormat;
	}
	
	public static String formatDate(Date date) {
		return getDefaultDateFormat().format(date);
	}
	
	public static DateFormat getDefaultTimeFormat() {
		if (_timeFormat == null) {
			_timeFormat = DateFormat.getTimeInstance(SimpleDateFormat.SHORT, Locale.getDefault());
		}
		return _timeFormat; 
	}
	
	public static String formatTime(Date time) {
		return getDefaultTimeFormat().format(time);
	}
	
}
