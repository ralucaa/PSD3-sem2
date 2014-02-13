package uk.ac.gla.psdteamk.sessions.service;

import uk.ac.gla.psdteamk.objects.*;

public interface SessionManagerService {
	void addSessionToDatabase(Session session);
	void assignRoom(int sessionId, int roomId);
	void bookSession(int sessionId, String username);
	void checkIfFullyRegistered(Account student);
	void checkSessionDetails(String sessionID);
	//void createTimetableSlot(BufferedReader reader, Session session);
	void importCourse(Course course);
	void changeFrequency(int sessionId, int frequency);
}
