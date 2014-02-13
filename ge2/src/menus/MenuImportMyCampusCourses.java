package menus;

import java.io.BufferedReader;
import java.util.ArrayList;

import functions.ImportMyCampusCourses;
import objects.Course;
import stub_mycampus.MyCampusStub;

public class MenuImportMyCampusCourses {
	/**
	 * Shows the course importing menu.
	 * @param reader - Your current BufferedReader object.
	 */
	public static void startImport(BufferedReader reader){
		//Get the list of courses available.
		ArrayList<Course> courses = MyCampusStub.getAllCourses();

		while(true) {
			//List the starting message.
			System.out.println("Please choose the course you wish to import. Available courses: ");
			//List the available courses and quit option.
			for (Course course : courses){
				System.out.println(course);
			}
			System.out.println("q: Quit");
			
			try {
				//Process the input.
				String option = reader.readLine();
				//Quit if q.
				if (option.equals("q")){
					break;
				}
				//Try to parse the input to an integer.
				int courseId = Integer.parseInt(option);
				//Look for the specified course in the list of courses.
				for (Course course : courses){
					if (course.getId() == courseId){
						//Course found. Import and break.
						ImportMyCampusCourses.importCourse(course);
						return;
					}
				}
				//Course not found, raise exception to print message and continue.
				throw new Exception();
			} catch (Exception ex){
				System.out.println("Invalid course!");
			}
		}
	}
}
