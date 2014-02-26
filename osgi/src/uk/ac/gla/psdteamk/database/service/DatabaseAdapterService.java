package uk.ac.gla.psdteamk.database.service;

import java.sql.Connection;
import java.util.ArrayList;

import uk.ac.gla.psdteamk.objects.*;

public interface DatabaseAdapterService {
	
	public Connection getConnection(); //??????
	public ArrayList<Course> getAllCourses();
	public ArrayList<Session> getAllSessions();
	public Course getCourse(int id);
	public ArrayList<String> getAllStudents();
	public boolean addCourseToDatabase(Course course);
	public boolean addSessionToDatabase(Session session);
	public boolean addUserToDatabase(Account account);
}
