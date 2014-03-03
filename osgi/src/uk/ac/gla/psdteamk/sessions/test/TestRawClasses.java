package uk.ac.gla.psdteamk.sessions.test;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uk.ac.gla.psdteamk.sessions.SessionManager;
import uk.ac.gla.psdteamk.sessions.service.SessionManagerService;
import uk.ac.gla.psdteamk.mycampus.MyCampusStub;
import uk.ac.gla.psdteamk.mycampus.service.MyCampusService;
import uk.ac.gla.psdteamk.database.DatabaseAdapter;
import uk.ac.gla.psdteamk.database.service.DatabaseAdapterService;


public class TestRawClasses {
	private SessionManagerService sessionManagerService;
	private DatabaseAdapterService databaseAdapterService;
	private MyCampusService myCampusService;

	@Before
	public void setUp() throws Exception {
		databaseAdapterService = new DatabaseAdapter();
		myCampusService = new MyCampusStub();
		sessionManagerService = new SessionManager(databaseAdapterService, myCampusService);
	}
	
	@After
	public void tearDown() throws Exception{
		
	}
	
	@Test
	public void canGetConnection() {
		Connection conn = databaseAdapterService.getConnection();
		assertTrue(conn != null);
	}
}
