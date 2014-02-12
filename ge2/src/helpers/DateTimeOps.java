package helpers;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.sql.Time;

public class DateTimeOps {
	public static DateTime parseTimeToJoda(String time){
		DateTimeFormatter df = DateTimeFormat.forPattern("HH:mm");
		return df.parseDateTime(time);
	}
	
	@SuppressWarnings("deprecation")
	public static Time parseJodaTimeToSqlTime(DateTime time){
		return new Time(time.getHourOfDay(), time.getMinuteOfHour(), 0);
	}
	
	public static Time parseTimeToSqlTime(String time){
		return parseJodaTimeToSqlTime(parseTimeToJoda(time));
	}
}
