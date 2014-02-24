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
	private Framework framework;
	private BundleContext bundleContext;
	private Bundle databaseServiceBundle, databaseBundle;
	private Bundle mycampusServiceBundle, mycampusBundle;
	private Bundle sessionServiceBundle, sessionBundle;
	
	private SessionManagerService sessionManagerService;

	@Before
	public void setUp() throws Exception {
		String extraPackages = "uk.ac.gla.psdteamk.sessions.service";
		
		//sometimes this doesn't seem to work in the tearDown, so do it again
		recursiveDelete(new File("felix-cache"));
		
		framework = 
				ConfiguredFrameworkFactory.createFelixFramework(
					extraPackages);

		bundleContext = framework.getBundleContext();
		
		Integer actualNumberOfBundles = bundleContext.getBundles().length;
		Integer expectedNumberOfBundles = 1;
		
		String message = 
			"If cleanly initialised, the framework should "
			+ "only contain 1 bundle (the framework).";
		
		assertEquals(
			message,
			expectedNumberOfBundles,
			actualNumberOfBundles);

		databaseServiceBundle = bundleContext.installBundle("file:jars/database-service.jar");
		databaseServiceBundle.start();
		
		databaseBundle = bundleContext.installBundle("file:jars/database.jar");
		databaseBundle.start();

		mycampusServiceBundle = bundleContext.installBundle("file:jars/mycampus-service.jar");
		mycampusServiceBundle.start();

		mycampusBundle = bundleContext.installBundle("file:jars/mycampus.jar");
		mycampusBundle.start();
		
		sessionServiceBundle = bundleContext.installBundle("file:jars/sessions-service.jar");
		sessionServiceBundle.start();
		
		sessionBundle = bundleContext.installBundle("file:jars/sessions.jar");
		sessionBundle.start();
		
		ServiceReference<SessionManagerService> sessionManagerServiceReference = 
				bundleContext.getServiceReference(SessionManagerService.class);
		sessionManagerService = bundleContext.getService(sessionManagerServiceReference);
	}
	
	@After
	public void tearDown() throws Exception{
		sessionBundle.stop();
		sessionBundle.uninstall();
		
		databaseBundle.stop();
		databaseBundle.uninstall();
		
		mycampusBundle.stop();
		mycampusBundle.uninstall();
		
		framework.stop();		
		framework.waitForStop(0);
		
		recursiveDelete(new File("felix-cache"));
	}
	
	private void recursiveDelete(File file){
		if (file.isDirectory()){
			for (File subFile : file.listFiles())
				recursiveDelete(subFile);
		}
		file.delete();
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
