package uk.ac.gla.psdteamk.sessions;

import uk.ac.gla.psdteamk.objects.Account;
import uk.ac.gla.psdteamk.objects.Course;
import uk.ac.gla.psdteamk.objects.Session;
import uk.ac.gla.psdteamk.sessions.service.SessionManagerService;
import uk.ac.gla.psdteamk.database.service.DatabaseAdapterService;
import uk.ac.gla.psdteamk.mycampus.service.MyCampusService;

public class SessionManager implements SessionManagerService {
	private DatabaseAdapterService da;
	private MyCampusService mc;
	
	public SessionManager(DatabaseAdapterService da, MyCampusService mc) {
		this.da = da;
		this.mc = mc;
	}
	
	@Override
	public void addSessionToCourse(Session session) {
		AddSessionToCourse.addSessionToDatabase(da, session);
	}

	@Override
	public void assignRoom(int sessionId, int roomId) {
		AssignRoomToTimetableSlot.assignRoom(da, sessionId, roomId);
	}

	@Override
	public void bookSession(int sessionId, String username) {
		BookTimetableSlot.bookSession(da, sessionId, username);
	}

	@Override
	public void checkIfFullyRegistered(Account student) {
		CheckCompulsoryCourses.checkIfFullyRegistered(da, student);
	}

	@Override
	public void checkSessionDetails(String sessionID) {
		CheckSessionDetails.checkSessionDetails(da, sessionID);
	}

	@Override
	public void importCourse(Course course) {
		ImportMyCampusCourses.importCourse(da, course);
	}

	@Override
	public void changeFrequency(int sessionId, int frequency) {
		SpecifySessionFrequency.changeFrequency(da, sessionId, frequency);
	}

}
