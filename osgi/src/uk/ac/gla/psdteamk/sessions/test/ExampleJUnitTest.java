package uk.ac.gla.psdteamk.sessions.test;

import static org.junit.Assert.*;

import java.io.File;

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

	@Before
	public void setUp() throws Exception {
		SetupFramework.setUp();
		sessionManagerService = SetupFramework.getSessionManagerService();
	}
	
	@After
	public void tearDown() throws Exception{
		SetupFramework.tearDown();
	}
	
	@Test
	public void testSMSNotNull() {
		assertFalse(sessionManagerService == null);
	}
	
	@Test
	public void testAssignRoom() {
		sessionManagerService.assignRoom(3, 5);
		
	}
	
	@Test
	public void testAddSession() {
		//Session(int id, int course, DateTime date, DateTime start_time, DateTime end_time, int frequency, String room, int capacity, String type)
		//Session session = new Session(...);
		fail("not implemented");
	}
	

}
