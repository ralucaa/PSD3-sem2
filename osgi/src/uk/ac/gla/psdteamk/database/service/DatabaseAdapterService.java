package uk.ac.gla.psdteamk.database.service;

import java.sql.Connection;
import java.util.ArrayList;

import uk.ac.gla.psdteamk.objects.*;

public interface DatabaseAdapterService {
	
	public Connection getConnection(); //??????
	public ArrayList<Course> getAllCourses();
	public ArrayList<Session> getAllSessions();
	public Course getCourse(int id);
	
	
}
