package uk.ac.gla.psdteamk.objects;

import org.joda.time.DateTime;

import uk.ac.gla.psdteamk.helpers.DateTimeOps;

public class TimetableSlot {
	private int id;
	private int session_id;
	private DateTime date;
	private DateTime start_time;
	private DateTime end_time;
	private int room;
	private int capacity;
	
	public TimetableSlot(int id, int session_id, DateTime date,
			DateTime start_time, DateTime end_time, int room, int capacity) {
		super();
		this.id = id;
		this.session_id = session_id;
		this.date = date;
		this.start_time = start_time;
		this.end_time = end_time;
		this.room = room;
		this.capacity = capacity;
	}
	
	public TimetableSlot(int id, int session_id, String date,
			String start_time, String end_time, int room, int capacity) {
		super();
		this.id = id;
		this.session_id = session_id;
		this.date = DateTimeOps.parseDateStringToJodaTime(date);
		this.start_time = DateTimeOps.parseTimeStringToJodaTime(start_time);
		this.end_time = DateTimeOps.parseTimeStringToJodaTime(end_time);
		this.room = room;
		this.capacity = capacity;
	}	
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSession_id() {
		return session_id;
	}

	public void setSession_id(int session_id) {
		this.session_id = session_id;
	}

	public String getDateString() {
		return DateTimeOps.parseJodaTimeToDateString(date);
	}

	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public String getStart_timeString() {
		return DateTimeOps.parseJodaTimeToTimeString(start_time);
	}

	public DateTime getStart_time() {
		return start_time;
	}

	public void setStart_time(DateTime start_time) {
		this.start_time = start_time;
	}

	public String getEnd_timeString() {
		return DateTimeOps.parseJodaTimeToTimeString(end_time);
	}

	public DateTime getEnd_time() {
		return end_time;
	}

	public void setEnd_time(DateTime end_time) {
		this.end_time = end_time;
	}

	public int getRoom() {
		return room;
	}

	public void setRoom(int room) {
		this.room = room;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public String toString() {
		return "TimetableSlot [id=" + id + ", session_id=" + session_id
				+ ", date=" + date + ", start_time=" + start_time
				+ ", end_time=" + end_time + ", room=" + room + ", capacity="
				+ capacity + "]";
	}

}
