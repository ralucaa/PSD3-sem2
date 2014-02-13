package uk.ac.gla.psdteamk.sessions.service;

import uk.ac.gla.psdteamk.objects.*;

public interface SessionManagerService {
	public void addSessionToCourse(Session session);
	public void assignRoom(int sessionId, int roomId);
	public void bookSession(int sessionId, String username);
	public void checkIfFullyRegistered(Account student);
	public void checkSessionDetails(String sessionID);
	//public void createTimetableSlot(BufferedReader reader, Session session);
	public void importCourse(Course course);
	public void changeFrequency(int sessionId, int frequency);
}
