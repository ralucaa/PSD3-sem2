package uk.ac.gla.psdteamk.objects;

import uk.ac.gla.psdteamk.helpers.DateTimeOps;

import org.joda.time.DateTime;

public class Session {

	private int id;
	private int course;
	private DateTime date;
	private DateTime start_time;
	private DateTime end_time;
	private int frequency;
	private String room;
	private int capacity;
	private String type;

	public Session(int id, int course, DateTime date, DateTime start_time,
			DateTime end_time, int frequency, String room, int capacity, String type) {
		this.id = id;
		this.course = course;
		this.date = date;
		try {
			this.start_time = start_time;
			this.end_time = end_time;
		} catch (Exception ex) { //Not specified, use 0.
			this.start_time = DateTimeOps.parseTimeStringToJodaTime("00:00");
			this.end_time = DateTimeOps.parseTimeStringToJodaTime("00:00");
		}
		this.frequency = frequency;
		try {
			this.room = room;
		} catch (Exception ex) { //Not specified, use empty.
			room = "";
		}
		this.capacity = capacity;
		this.type = type;
	}
	public Session(int id, int course, String date, String start_time,
			String end_time, int frequency, String room, int capacity, String type) {
		this(id, course, DateTimeOps.parseDateStringToJodaTime(date), DateTimeOps.parseTimeStringToJodaTime(start_time), DateTimeOps.parseTimeStringToJodaTime(end_time), frequency, room, capacity, type);
	}

	public Session(){
		//Empty constructor.
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCourse() {
		return course;
	}

	public void setCourse(int course) {
		this.course = course;
	}

	public DateTime getDate() {
		return date;
	}

	public String getDateString() {
		return DateTimeOps.parseJodaTimeToDateString(date);
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public DateTime getStart_time() {
		return start_time;
	}

	public String getStart_timeString() {
		return DateTimeOps.parseJodaTimeToTimeString(start_time);
	}

	public void setStart_time(DateTime start_time) {
		this.start_time = start_time;
	}

	public DateTime getEnd_time() {
		return end_time;
	}

	public String getEnd_timeString() {
		return DateTimeOps.parseJodaTimeToTimeString(end_time);
	}

	public void setEnd_time(DateTime end_time) throws Exception {
		if (this.start_time == null || this.start_time.isAfter(end_time)){
			throw new Exception("The end time cannot be earlier than the start time (" + start_time.toString() + ")!");
		}
		this.end_time = end_time;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return id + ": course=" + course + ", date=" + date
				+ ", start_time=" + start_time + ", end_time=" + end_time
				+ ", frequency=" + frequency + ", room=" + room + ", capacity="
				+ capacity + ", type=" + type;
	}

}
