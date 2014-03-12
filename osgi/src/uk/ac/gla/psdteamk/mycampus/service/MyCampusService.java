package uk.ac.gla.psdteamk.mycampus.service;

import java.util.ArrayList;

import uk.ac.gla.psdteamk.objects.*;

public interface MyCampusService {
	public Account authenticate(String username, String password);
	
	public ArrayList<Course> getAllCourses();
	
	public boolean addUser(Account account);
}
