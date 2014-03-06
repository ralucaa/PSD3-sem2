package uk.ac.gla.psdteamk.sessions.test;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import uk.ac.gla.psdteamk.sessions.service.SessionManagerService;
import uk.ac.gla.psdteamk.database.service.DatabaseAdapterService;


public class ExampleJUnitTest {
	
	private SessionManagerService sessionManagerService;
	private DatabaseAdapterService databaseAdapterService;

	@BeforeClass
	public static void beforeClass() throws Exception {
		SetupFramework.recursiveDelete(new File("felix-cache"));
		SetupFramework.start();
	}
	
	@AfterClass
	public static void afterClass() throws Exception {
		SetupFramework.stop();
	}
	
	@Before
	public void setUp() throws Exception {
		sessionManagerService = SetupFramework.getSessionManagerService();
		databaseAdapterService = SetupFramework.getDatabaseAdapterService();
	}
	
	@Test
	public void testSMSNotNull() {
		assertTrue(sessionManagerService != null);
	}
	
	@Test
	public void testDBSNotNull() {
		assertTrue(databaseAdapterService != null);
	}
	
	@Test
	public void canGetConnection() {
		Connection conn = databaseAdapterService.getConnection();
		assertTrue(conn != null);
	}
	
	@Test
	public void testGetAllSessionsNotNull() {
		assertTrue(databaseAdapterService.getAllSessions() != null);
	}
	
	@Test
	public void testBookSession() {
		assertTrue(sessionManagerService.bookSession(-1, 1));
	}
	
	@Test
	public void authenticateAdmin() {
		assertTrue(sessionManagerService.authenticate("1111111A", "1111111A") != -1);
	}
	
	@Test
	public void testAssignRoom() {
		int adminToken = sessionManagerService.authenticate("1111111A", "1111111A");
		assertTrue(sessionManagerService.assignRoom(adminToken, 3, 1));
	}

}
