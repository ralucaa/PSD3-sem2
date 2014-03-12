package uk.ac.gla.psdteamk.sessions;

import uk.ac.gla.psdteamk.database.service.DatabaseAdapterService;
import uk.ac.gla.psdteamk.mycampus.service.MyCampusService;
import uk.ac.gla.psdteamk.objects.*;

public class ImportMyCampusCourses {
	/**
	 * Imports the specified course to the database.
	 * @param course - The course to import.
	 */
	public static boolean importCourse(DatabaseAdapterService da, MyCampusService mc, String courseTitle) {
		Course course = null;
		for (Course mcCourse : mc.getAllCourses()) {
			if (mcCourse.getTitle().equals(courseTitle)) {
				course = mcCourse;
			}
		}
		if (course == null) {
			System.out.println("No such course in MyCampus: " + courseTitle);
			return false;
		}
		if (!da.addCourseToDatabase(course)) {
			System.out.println("Error adding course to database: " + courseTitle);
			return false;
		}
		System.out.println("Successfully added course to database: " + courseTitle);
		return true;
	}
}
