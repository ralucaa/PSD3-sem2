package uk.ac.gla.psdteamk.sessions.service;

import java.util.List;

import uk.ac.gla.psdteamk.objects.*;

public interface SessionManagerService {
	/**
	 * Log in before calling the other functions.
	 * @param username
	 * @param password
	 * @return authentication token, or -1 on failure
	 */
	public int authenticate(String username, String password);
	
	/**
	 * AS A lecturer I WANT TO add a session to a course, SO THAT timetable slots can be identified (story 2).
	 * @param token
	 * @param session
	 * @return true on success, false on failure
	 */
	public boolean addSessionToCourse(int token, Session session);
	
	/**
	 * AS A administrator I WANT TO assign a room to a timetable slot, 
	 * SO THAT room bookings can be recorded (story 8).
	 * @param token
	 * @param sessionId
	 * @param roomId
	 * @return true on success, false on failure
	 */
	public boolean assignRoom(int token, int sessionId, int roomId);
	
	/**
	 * AS A student I WANT TO book a timetable slot for each session of my course, 
	 * SO THAT I can take the course (story 11).
	 * @param token
	 * @param sessionId
	 * @return true on success, false on failure
	 */
	public boolean bookSession(int token, int sessionId);
	
	/**
	 * AS A student I WANT TO check that I have signed up for all compulsory courses, 
	 * SO THAT I don't fail the course (story 12).
	 * @param token
	 * @return true on success, false on failure
	 */
	public boolean checkIfFullyRegistered(int token);
	
	/**
	 * AS A lecturer I WANT TO see the details (time, location, students, tutor) 
	 * of every timetable slot in a session, 
	 * SO THAT I know when sessions happen (story 14).
	 * @param token
	 * @param sessionID
	 * @return true on success, false on failure
	 */
	public boolean checkSessionDetails(int token, int sessionID);
	
	/**
	 * AS A administrator I WANT to create a timetable slot for a session 
	 * SO THAT rooms can be assigned to slots (story 23).
	 * @param token
	 * @param timetableSlot
	 * @return true on success, false on failure
	 */
	public boolean createTimetableSlot(int token, TimetableSlot timetableSlot);
	
	/**
	 * AS A lecturer I WANT TO import a MyCampus course, 
	 * SO THAT teaching sessions can be identified (story 1).
	 * @param token
	 * @param course
	 * @return true on success, false on failure
	 */
	public boolean importCourse(int token, Course course);
	
	/**
	 * AS A lecturer I WANT TO specify that a session is a one off, or recurs weekly or fortnightly, 
	 * SO THAT I don't have to create a large number of sessions (story 4).
	 * @param token
	 * @param sessionId
	 * @param frequency
	 * @return true on success, false on failure
	 */
	public boolean changeFrequency(int token, int sessionId, int frequency);
	
	/**
	 * AS A administrator I WANT TO check that there are no timetable slot clashes between courses 
	 * SO THAT students are able to complete the course.
	 * @param token
	 * @return list of clashing sessions, or null on failure
	 */
	public List<Session> checkForClashes(int token);
	
	/**
	 * Gets the user type for the user who owns the specified token.
	 * @param token - The token to check.
	 * @return the user type
	 */
	public String accountGetType(int token);
}
