package uk.ac.gla.psdteamk.sessions;

import uk.ac.gla.psdteamk.objects.Account;
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
	public synchronized int authenticate(String username, String password) {
		Account acc = mc.authenticate(username, password);
		if (acc != null) {
			logins.put(acc.getToken(), acc);
			return acc.getToken();
		}
		return -1;
	}
	
	private synchronized boolean accountIsType(int token, String type) {
		Account acc = logins.get(token);
		return (acc != null && acc.getType().equals(type));
	}
	
	@Override
	public int getLoggedInUserCount() {
		return logins.size();
	}
	
	@Override
	public synchronized String accountGetType(int token) {
		Account acc = logins.get(token);
		return acc.getType();
	}
	
	@Override
	public synchronized boolean addSessionToCourse(int token, Session session) {
		if (accountIsType(token, Account.TYPE_LECTURER)) {
			return AddSessionToCourse.addSessionToDatabase(da, session);
		} else {
			System.out.println("Access denied");
			return false;
		}
	}

	@Override
	public synchronized boolean assignRoom(int token, int sessionId, int roomId) {
		if (accountIsType(token, Account.TYPE_ADMIN)) {
			return AssignRoomToTimetableSlot.assignRoom(da, sessionId, roomId);
		} else {
			System.out.println("Access denied");
			return false;
		}
	}

	@Override
	public synchronized boolean bookSlot(int token, int slotId) {
		if (accountIsType(token, Account.TYPE_STUDENT)) {
			Account acc = logins.get(token);
			return BookTimetableSlot.bookSlot(da, slotId, acc.getUsername());
		} else {
			System.out.println("Access denied");
			return false;
		}
	}

	@Override
	public synchronized boolean checkIfFullyRegistered(int token) {
		if (accountIsType(token, Account.TYPE_STUDENT)) {
			Account acc = logins.get(token);
			return CheckCompulsoryCourses.checkIfFullyRegistered(da, acc);
		} else {
			System.out.println("Access denied");
			return false;
		}
	}

	@Override
	public synchronized boolean checkSessionDetails(int token, int sessionID) {
		if (accountIsType(token, Account.TYPE_LECTURER)) {
			return CheckSessionDetails.checkSessionDetails(da, sessionID);
		} else {
			System.out.println("Access denied");
			return false;
		}
	}

	@Override
	public synchronized boolean createTimetableSlot(int token, TimetableSlot timetableSlot) {
		if (accountIsType(token, Account.TYPE_ADMIN)) {
			return CreateTimetableSlotForSession.createTimetableSlot(da, timetableSlot);
		} else {
			System.out.println("Access denied");
			return false;
		}
	}
	
	@Override
	public synchronized boolean importCourse(int token, String courseTitle) {
		if (accountIsType(token, Account.TYPE_LECTURER)) {
			return ImportMyCampusCourses.importCourse(da, mc, courseTitle);
		} else {
			System.out.println("Access denied");
			return false;
		}
	}

	@Override
	public synchronized boolean changeFrequency(int token, int sessionId, int frequency) {
		if (accountIsType(token, Account.TYPE_LECTURER)) {
			return SpecifySessionFrequency.changeFrequency(da, sessionId, frequency);
		} else {
			System.out.println("Access denied");
			return false;
		}
	}

	@Override
	public synchronized List<Session> checkForClashes(int token) {
		if (accountIsType(token, Account.TYPE_ADMIN)) {
			return CheckForClashes.checkForClashes(da);
		} else {
			System.out.println("Access denied");
			return null;
		}
	}

}
