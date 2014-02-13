package uk.ac.gla.psdteamk.helpers;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateTimeOps {
	public static final String TIME_FORMAT = "HH:mm";
	public static final String DATE_FORMAT = "dd/MM/yyyy";

	private static final String DEFAULT_TIME = "00:00", DEFAULT_DATE = "01/01/2001";

	public static DateTime parseTimeStringToJodaTime(String time){
		DateTimeFormatter df = DateTimeFormat.forPattern(TIME_FORMAT);
		return df.parseDateTime(time);
	}

	public static String parseJodaTimeToTimeString(DateTime time){
		try {
			DateTimeFormatter df = DateTimeFormat.forPattern(TIME_FORMAT);
			return df.print(time);
		} catch(Exception ex) {
			return DEFAULT_TIME;
		}
	}

	public static DateTime parseDateStringToJodaTime(String date){
		DateTimeFormatter df = DateTimeFormat.forPattern(DATE_FORMAT);
		return df.parseDateTime(date);
	}

	public static String parseJodaTimeToDateString(DateTime date){
		try {
			DateTimeFormatter df = DateTimeFormat.forPattern(DATE_FORMAT);
			return df.print(date);
		} catch (Exception ex) {
			return DEFAULT_DATE;
		}
	}
}
