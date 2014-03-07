package uk.ac.gla.psdteamk.sessions;

import uk.ac.gla.psdteamk.objects.Account;
import uk.ac.gla.psdteamk.objects.Course;
import uk.ac.gla.psdteamk.objects.Session;
import uk.ac.gla.psdteamk.objects.TimetableSlot;
import uk.ac.gla.psdteamk.sessions.service.SessionManagerService;
import uk.ac.gla.psdteamk.database.service.DatabaseAdapterService;
import uk.ac.gla.psdteamk.mycampus.service.MyCampusService;

import java.util.List;
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
	public boolean addSessionToCourse(int token, Session session) {
		if (accountIsType(token, Account.TYPE_LECTURER)) {
			return AddSessionToCourse.addSessionToDatabase(da, session);
		} else {
			System.out.println("Access denied");
			return false;
		}
	}

	@Override
	public boolean assignRoom(int token, int sessionId, int roomId) {
		if (accountIsType(token, Account.TYPE_ADMIN)) {
			return AssignRoomToTimetableSlot.assignRoom(da, sessionId, roomId);
		} else {
			System.out.println("Access denied");
			return false;
		}
	}

	@Override
	public boolean bookSlot(int token, int slotId) {
		if (accountIsType(token, Account.TYPE_STUDENT)) {
			Account acc = logins.get(token);
			return BookTimetableSlot.bookSession(da, slotId, acc.getUsername());
		} else {
			System.out.println("Access denied");
			return false;
		}
	}

	@Override
	public boolean checkIfFullyRegistered(int token) {
		if (accountIsType(token, Account.TYPE_STUDENT)) {
			Account acc = logins.get(token);
			return CheckCompulsoryCourses.checkIfFullyRegistered(da, acc);
		} else {
			System.out.println("Access denied");
			return false;
		}
	}

	@Override
	public boolean checkSessionDetails(int token, int sessionID) {
		if (accountIsType(token, Account.TYPE_LECTURER)) {
			return CheckSessionDetails.checkSessionDetails(da, sessionID);
		} else {
			System.out.println("Access denied");
			return false;
		}
	}

	@Override
	public boolean createTimetableSlot(int token, TimetableSlot timetableSlot) {
		if (accountIsType(token, Account.TYPE_ADMIN)) {
			return CreateTimetableSlotForSession.createTimetableSlot(da, timetableSlot);
		} else {
			System.out.println("Access denied");
			return false;
		}
	}
	
	@Override
	public boolean importCourse(int token, Course course) {
		if (accountIsType(token, Account.TYPE_LECTURER)) {
			return ImportMyCampusCourses.importCourse(da, course);
		} else {
			System.out.println("Access denied");
			return false;
		}
	}

	@Override
	public boolean changeFrequency(int token, int sessionId, int frequency) {
		if (accountIsType(token, Account.TYPE_LECTURER)) {
			return SpecifySessionFrequency.changeFrequency(da, sessionId, frequency);
		} else {
			System.out.println("Access denied");
			return false;
		}
	}

	@Override
	public List<Session> checkForClashes(int token) {
		if (accountIsType(token, Account.TYPE_ADMIN)) {
			return CheckForClashes.checkForClashes(da);
		} else {
			System.out.println("Access denied");
			return null;
		}
	}

}
