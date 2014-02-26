package uk.ac.gla.psdteamk.sessions;

import uk.ac.gla.psdteamk.objects.Account;
import uk.ac.gla.psdteamk.objects.Course;
import uk.ac.gla.psdteamk.objects.Session;
import uk.ac.gla.psdteamk.sessions.service.SessionManagerService;
import uk.ac.gla.psdteamk.database.service.DatabaseAdapterService;
import uk.ac.gla.psdteamk.mycampus.service.MyCampusService;
import java.util.concurrent.ConcurrentHashMap;

public class SessionManager implements SessionManagerService {
	private DatabaseAdapterService da;
	private MyCampusService mc;
	private ConcurrentHashMap<Integer, Account> logins;
	
	public SessionManager(DatabaseAdapterService da, MyCampusService mc) {
		this.da = da;
		this.mc = mc;
		this.logins = new ConcurrentHashMap<Integer, Account>();
	}

	/** create a login session - return auth token, or -1 if failed */
	@Override
	public int authenticate(String username, String password) {
		Account acc = mc.authenticate(username, password);
		if (acc != null) {
			logins.put(acc.getToken(), acc);
			return acc.getToken();
		}
		return -1;
	}
	
	private boolean accountIsType(int token, String type) {
		Account acc = logins.get(token);
		return (acc != null && acc.getType().equals(type));
	}
	
	@Override
	public boolean addSessionToCourse(Session session) {
		return AddSessionToCourse.addSessionToDatabase(da, session);
	}

	@Override
	public boolean assignRoom(int sessionId, int roomId) {
		return AssignRoomToTimetableSlot.assignRoom(da, sessionId, roomId);
	}

	@Override
	public boolean bookSession(int sessionId, String username) {
		return BookTimetableSlot.bookSession(da, sessionId, username);
	}

	@Override
	public boolean checkIfFullyRegistered(Account student) {
		return CheckCompulsoryCourses.checkIfFullyRegistered(da, student);
	}

	@Override
	public boolean checkSessionDetails(String sessionID) {
		return CheckSessionDetails.checkSessionDetails(da, sessionID);
	}

	@Override
	public boolean createTimetableSlot(Session session) {
		return CreateTimetableSlotForSession.createTimetableSlot(da, session);
	}
	
	@Override
	public boolean importCourse(Course course) {
		return ImportMyCampusCourses.importCourse(da, course);
	}

	@Override
	public boolean changeFrequency(int sessionId, int frequency) {
		return SpecifySessionFrequency.changeFrequency(da, sessionId, frequency);
	}

}
