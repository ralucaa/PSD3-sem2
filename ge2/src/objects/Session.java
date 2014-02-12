package objects;

import java.sql.Date;
import java.sql.Time;

public class Session {
	
	private int id;
	private int course;
	private Date date;
	private Time start_time;
	private Time end_time;
	private int frequency;
	private String room;
	private int capacity;
	private String type;
		
	public Session(int id, int course, Date date, Time start_time,
			Time end_time, int frequency, String room, int capacity, String type) {
		this.id = id;
		this.course = course;
		this.date = date;
		this.start_time = start_time;
		this.end_time = end_time;
		this.frequency = frequency;
		this.room = room;
		this.capacity = capacity;
		this.type = type;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getStart_time() {
		return start_time;
	}

	public void setStart_time(Time start_time) {
		this.start_time = start_time;
	}

	public Time getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Time end_time) {
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
	
	
	
	
}
