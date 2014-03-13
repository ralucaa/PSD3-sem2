package uk.ac.gla.psdteamk.database.service;

import java.sql.Connection;
import java.util.ArrayList;

import uk.ac.gla.psdteamk.objects.*;

public interface DatabaseAdapterService {
	
	public Connection getConnection();
	public Course getCourse(int id);
	public ArrayList<Course> getAllCourses();
	public Session getSession(int session_id);
	public ArrayList<Session> getAllSessions();
	public TimetableSlot getTimetableSlot(int slotId);
	public ArrayList<TimetableSlot> getAllTimetableSlots();
	public ArrayList<TimetableSlot> getAllTimetableSlotsByCourse(int courseId);
	public ArrayList<String> getAllStudents();
	public boolean addCourseToDatabase(Course course);
	public boolean addSessionToDatabase(Session session);
	public boolean addTimetableSlotToDatabase(TimetableSlot session);
	public boolean addUserToDatabase(Account account);
	public boolean resetTables();
}
