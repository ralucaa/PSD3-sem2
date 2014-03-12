package uk.ac.gla.psdteamk.sessions.test;


import static org.junit.Assert.*;

import java.io.File;
import java.sql.Connection;
import java.sql.Statement;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.launch.Framework;
import org.osgi.framework.ServiceReference;

import uk.ac.gla.psdteamk.sessions.service.SessionManagerService;
import uk.ac.gla.psdteamk.database.service.DatabaseAdapterService;


public class SetupFramework {
	private static Framework framework;
	private static BundleContext bundleContext;
	private static Bundle databaseServiceBundle, databaseBundle, sharedBundle;
	private static Bundle mycampusServiceBundle, mycampusBundle;
	private static Bundle sessionServiceBundle, sessionBundle;
	
	private static DatabaseAdapterService databaseAdapterService = null;
	private static SessionManagerService sessionManagerService = null;
	
	public static SessionManagerService getSessionManagerService() {
		return sessionManagerService;
	}
	
	public static DatabaseAdapterService getDatabaseAdapterService() {
		return databaseAdapterService;
	}
	
	public static void start() throws Exception {
		String extraPackages = "uk.ac.gla.psdteamk.sessions.service,uk.ac.gla.psdteamk.database.service,uk.ac.gla.psdteamk.objects";
		
		System.out.println("framework start");
		
		//sometimes this doesn't seem to work in the tearDown, so do it again
		//recursiveDelete(new File("felix-cache"));
		//recursiveDelete(new File("derby"));

		framework = 
				ConfiguredFrameworkFactory.createFelixFramework(
					extraPackages);

		bundleContext = framework.getBundleContext();
		
		Integer actualNumberOfBundles = bundleContext.getBundles().length;
		Integer expectedNumberOfBundles = 1;
		
		System.out.println("actualNumberOfBundles: " + actualNumberOfBundles);
		
		String message = 
			"If cleanly initialised, the framework should "
			+ "only contain 1 bundle (the framework).";
		
		assertEquals(
			message,
			expectedNumberOfBundles,
			actualNumberOfBundles);

		sharedBundle = bundleContext.installBundle("file:jars/shared.jar");
		sharedBundle.start();
		
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
		
		ServiceReference<DatabaseAdapterService> databaseAdapterServiceReference = 
				bundleContext.getServiceReference(DatabaseAdapterService.class);
		databaseAdapterService = bundleContext.getService(databaseAdapterServiceReference);
	}
	
	public static void deleteDatabaseData() {
		databaseAdapterService.deleteEverything();
	}
	
	public static void defaultPopulate() throws Exception {
		deleteDatabaseData();
		
		Connection conn = databaseAdapterService.getConnection();
		Statement stmt = conn.createStatement();
		
		//do it roughly for now
		
		stmt.executeUpdate("INSERT INTO \"Registration\" VALUES(1,'3333333E')");
		stmt.executeUpdate("INSERT INTO \"Registration\" VALUES(2,'3333333E')");
		stmt.executeUpdate("INSERT INTO \"Registration\" VALUES(3,'3333333E')");
		stmt.executeUpdate("INSERT INTO \"Registration\" VALUES(4,'3333333E')");
		stmt.executeUpdate("INSERT INTO \"Registration\" VALUES(1,'3333333A')");
		stmt.executeUpdate("INSERT INTO \"Registration\" VALUES(2,'3333333A')");
		stmt.executeUpdate("INSERT INTO \"Registration\" VALUES(3,'3333333B')");
		stmt.executeUpdate("INSERT INTO \"Registration\" VALUES(4,'3333333B')");
		stmt.executeUpdate("INSERT INTO \"Registration\" VALUES(3,'3333333A')");

		stmt.executeUpdate("INSERT INTO \"MandatoryCourses\" VALUES(3,1)");
		stmt.executeUpdate("INSERT INTO \"MandatoryCourses\" VALUES(3,2)");
		stmt.executeUpdate("INSERT INTO \"MandatoryCourses\" VALUES(3,3)");
		stmt.executeUpdate("INSERT INTO \"MandatoryCourses\" VALUES(3,4)");

		stmt.executeUpdate("INSERT INTO \"Course\" (\"title\") VALUES('Professional Software Development')");
		stmt.executeUpdate("INSERT INTO \"Course\" (\"title\") VALUES('Distributed Information Management')");
		stmt.executeUpdate("INSERT INTO \"Course\" (\"title\") VALUES('Network Systems')");
		stmt.executeUpdate("INSERT INTO \"Course\" (\"title\") VALUES('Operating Systems')");
		stmt.executeUpdate("INSERT INTO \"Course\" (\"title\") VALUES('Database Systems')");

		stmt.executeUpdate("INSERT INTO \"Tutoring\" VALUES('4444444A',3)");
		stmt.executeUpdate("INSERT INTO \"Tutoring\" VALUES('4444444C',5)");
		stmt.executeUpdate("INSERT INTO \"Tutoring\" VALUES('4444444C',8)");
		stmt.executeUpdate("INSERT INTO \"Tutoring\" VALUES('4444444B',10)");
		stmt.executeUpdate("INSERT INTO \"Tutoring\" VALUES('4444444A',12)");

		stmt.executeUpdate("INSERT INTO \"Session\" (\"course\", \"compulsory\", \"frequency\", \"type\") VALUES(1,1,7,'lecture')");
		stmt.executeUpdate("INSERT INTO \"Session\" (\"course\", \"compulsory\", \"frequency\", \"type\") VALUES(1,1,7,'lecture')");
		stmt.executeUpdate("INSERT INTO \"Session\" (\"course\", \"compulsory\", \"frequency\", \"type\") VALUES(1,1,7,'laboratory')");
		stmt.executeUpdate("INSERT INTO \"Session\" (\"course\", \"compulsory\", \"frequency\", \"type\") VALUES(2,1,7,'lecture')");
		stmt.executeUpdate("INSERT INTO \"Session\" (\"course\", \"compulsory\", \"frequency\", \"type\") VALUES(3,1,7,'laboratory')");
		stmt.executeUpdate("INSERT INTO \"Session\" (\"course\", \"compulsory\", \"frequency\", \"type\") VALUES(4,0,7,'lecture')");
		stmt.executeUpdate("INSERT INTO \"Session\" (\"course\", \"compulsory\", \"frequency\", \"type\") VALUES(2,1,7,'lecture')");
		stmt.executeUpdate("INSERT INTO \"Session\" (\"course\", \"compulsory\", \"frequency\", \"type\") VALUES(3,0,7,'laboratory')");
		stmt.executeUpdate("INSERT INTO \"Session\" (\"course\", \"compulsory\", \"frequency\", \"type\") VALUES(3,1,7,'lecture')");
		stmt.executeUpdate("INSERT INTO \"Session\" (\"course\", \"compulsory\", \"frequency\", \"type\") VALUES(2,0,7,'laboratory')");
		stmt.executeUpdate("INSERT INTO \"Session\" (\"course\", \"compulsory\", \"frequency\", \"type\") VALUES(4,0,7,'lecture')");
		stmt.executeUpdate("INSERT INTO \"Session\" (\"course\", \"compulsory\", \"frequency\", \"type\") VALUES(3,1,7,'laboratory')");
		stmt.executeUpdate("INSERT INTO \"Session\" (\"course\", \"compulsory\", \"frequency\", \"type\") VALUES(2,1,0,'lecture')");

		stmt.executeUpdate("INSERT INTO \"TimetableSlot\" (\"session\", \"date\", \"start_time\", \"end_time\", \"room\", \"capacity\") VALUES(1,'13/01/2014','09:00','10:00',1,120)");
		stmt.executeUpdate("INSERT INTO \"TimetableSlot\" (\"session\", \"date\", \"start_time\", \"end_time\", \"room\", \"capacity\") VALUES(2,'13/01/2014','14:00','15:00',2,120)");
		stmt.executeUpdate("INSERT INTO \"TimetableSlot\" (\"session\", \"date\", \"start_time\", \"end_time\", \"room\", \"capacity\") VALUES(1,'14/01/2014','15:00','16:00',3,120)");
		stmt.executeUpdate("INSERT INTO \"TimetableSlot\" (\"session\", \"date\", \"start_time\", \"end_time\", \"room\", \"capacity\") VALUES(3,'14/01/2014','10:00','12:00',1,080)");
		stmt.executeUpdate("INSERT INTO \"TimetableSlot\" (\"session\", \"date\", \"start_time\", \"end_time\", \"room\", \"capacity\") VALUES(4,'14/01/2014','14:00','15:00',3,120)");
		stmt.executeUpdate("INSERT INTO \"TimetableSlot\" (\"session\", \"date\", \"start_time\", \"end_time\", \"room\", \"capacity\") VALUES(5,'15/01/2014','10:00','11:00',1,080)");
		stmt.executeUpdate("INSERT INTO \"TimetableSlot\" (\"session\", \"date\", \"start_time\", \"end_time\", \"room\", \"capacity\") VALUES(6,'15/01/2014','14:00','15:00',3,080)");
		stmt.executeUpdate("INSERT INTO \"TimetableSlot\" (\"session\", \"date\", \"start_time\", \"end_time\", \"room\", \"capacity\") VALUES(7,'15/01/2014','15:00','16:00',3,120)");
		stmt.executeUpdate("INSERT INTO \"TimetableSlot\" (\"session\", \"date\", \"start_time\", \"end_time\", \"room\", \"capacity\") VALUES(8,'16/01/2014','10:00','12:00',1,080)");
		stmt.executeUpdate("INSERT INTO \"TimetableSlot\" (\"session\", \"date\", \"start_time\", \"end_time\", \"room\", \"capacity\") VALUES(8,'16/01/2014','14:00','15:00',3,120)");
		stmt.executeUpdate("INSERT INTO \"TimetableSlot\" (\"session\", \"date\", \"start_time\", \"end_time\", \"room\", \"capacity\") VALUES(9,'17/01/2014','10:00','12:00',2,080)");
		stmt.executeUpdate("INSERT INTO \"TimetableSlot\" (\"session\", \"date\", \"start_time\", \"end_time\", \"room\", \"capacity\") VALUES(9,'17/01/2014','14:00','15:00',5,120)");
		
		stmt.close();
		conn.close();
	}
	
	public static void stop() throws Exception{
		System.out.println("framework stop");
		
		sharedBundle.stop();
		
		sessionBundle.stop();
		//sessionBundle.uninstall();
		
		sessionServiceBundle.stop();
		//sessionServiceBundle.uninstall();
		
		databaseBundle.stop();
		//databaseBundle.uninstall();
		
		databaseServiceBundle.stop();
		//databaseServiceBundle.uninstall();
		
		mycampusBundle.stop();
		//mycampusBundle.uninstall();
		
		mycampusServiceBundle.stop();
		//mycampusServiceBundle.uninstall();
		
		framework.stop();		
		framework.waitForStop(0);
		
		//recursiveDelete(new File("felix-cache"));
		//recursiveDelete(new File("derby"));
	}
	
	public static void recursiveDelete(File file){
		if (file.exists()) {
			if (file.isDirectory()){
				for (File subFile : file.listFiles())
					recursiveDelete(subFile);
			}
			if (file.delete()) {
				//System.out.println("deleted " + file);
			} else {
				System.out.println("can't delete " + file);
			}
		}
	}
}
