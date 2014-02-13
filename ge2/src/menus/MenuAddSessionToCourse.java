package menus;

import helpers.DateTimeOps;

import java.io.BufferedReader;
import java.util.ArrayList;

import objects.Course;
import objects.Session;
import database_adapter.DatabaseAdapter;

public class MenuAddSessionToCourse {
	/**
	 * Shows the session adding menu.
	 * @param reader
	 */
	public static void beginSessionAdding(BufferedReader reader){
		//Get the list of courses available.
		ArrayList<Course> courses = DatabaseAdapter.getAllCourses();

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
						addSessionToCourse(course, reader);
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

	/**
	 * Adds a session to the specified course.
	 * @param course - The course for which to add the session.
	 * @param reader - Your current BufferedReader.
	 */
	private static void addSessionToCourse(Course course, BufferedReader reader){
		Session session = new Session();
		session.setCourse(course.getId());

		//Read date.
		while(true) {
			try {
				System.out.println("Use q to abort. Session date in dd/MM/yyyy format:");
				String input = reader.readLine();
				//See if abort requested.
				if (input.equals("q")){
					return;
				}
				session.setDate(DateTimeOps.parseDateStringToJodaTime(input));
				break;
			} catch (Exception ex){
				System.out.println(ex.getMessage());
			}
		}

		//Read capacity.
		while(true) {
			try {
				System.out.println("Use q to abort. Capacity:");
				String input = reader.readLine();
				//See if abort requested.
				if (input.equals("q")){
					return;
				}
				int intput = Integer.valueOf(input);
				if (intput < 0){
					throw new Exception("Capacity cannot be negative!");
				}
				session.setCapacity(intput);
				break;
			} catch (Exception ex){
				System.out.println(ex.getMessage());
			}
		}

		//Read type.
		while(true) {
			try {
				System.out.println("Use q to abort. Session type:");
				String input = reader.readLine();
				//See if abort requested.
				if (input.equals("q")){
					return;
				}
				session.setType(input);
				break;
			} catch (Exception ex){
				System.out.println(ex.getMessage());
			}
		}
	}
}