package uk.ac.gla.psdteamk.sessions.service;

import uk.ac.gla.psdteamk.objects.*;

public interface SessionManagerService {
	public boolean addSessionToCourse(Session session);
	public boolean assignRoom(int sessionId, int roomId);
	public boolean bookSession(int sessionId, String username);
	public boolean checkIfFullyRegistered(Account student);
	public boolean checkSessionDetails(String sessionID);
	//public boolean createTimetableSlot(BufferedReader reader, Session session);
	public boolean importCourse(Course course);
	public boolean changeFrequency(int sessionId, int frequency);
}
