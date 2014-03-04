package uk.ac.gla.psdteamk.sessions.test;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Connection;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.launch.Framework;
import org.osgi.framework.ServiceReference;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uk.ac.gla.psdteamk.objects.Session;
import uk.ac.gla.psdteamk.sessions.service.SessionManagerService;
import uk.ac.gla.psdteamk.mycampus.service.MyCampusService;
import uk.ac.gla.psdteamk.database.service.DatabaseAdapterService;


public class ExampleJUnitTest {
	
	private SessionManagerService sessionManagerService;
	private DatabaseAdapterService databaseAdapterService;

	@Before
	public void setUp() throws Exception {
		SetupFramework.setUp();
		sessionManagerService = SetupFramework.getSessionManagerService();
		databaseAdapterService = SetupFramework.getDatabaseAdapterService();
	}
	
	@After
	public void tearDown() throws Exception{
		SetupFramework.tearDown();
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
		assertTrue(sessionManagerService.bookSession(1, "3333333B"));
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
