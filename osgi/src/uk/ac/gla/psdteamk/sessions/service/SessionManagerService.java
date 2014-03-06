package uk.ac.gla.psdteamk.sessions.service;

import java.util.List;

import uk.ac.gla.psdteamk.objects.*;

public interface SessionManagerService {
	public int authenticate(String username, String password);
	public boolean addSessionToCourse(int token, Session session);
	public boolean assignRoom(int token, int sessionId, int roomId);
	public boolean bookSession(int token, int sessionId, String username);
	public boolean checkIfFullyRegistered(int token, Account student);
	public boolean checkSessionDetails(int token, String sessionID);
	public boolean createTimetableSlot(int token, Session session);
	public boolean importCourse(int token, Course course);
	public boolean changeFrequency(int token, int sessionId, int frequency);
	public List<Session> checkForClashes(int token);
}
