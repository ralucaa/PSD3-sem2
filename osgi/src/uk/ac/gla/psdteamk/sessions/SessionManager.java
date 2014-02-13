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
	public void addSessionToDatabase(Session session) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void assignRoom(int sessionId, int roomId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bookSession(int sessionId, String username) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkIfFullyRegistered(Account student) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkSessionDetails(String sessionID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void importCourse(Course course) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeFrequency(int sessionId, int frequency) {
		// TODO Auto-generated method stub
		
	}

}
