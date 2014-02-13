package functions;

import helpers.DateTimeOps;

import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import database_adapter.DatabaseAdapter;
import objects.Course;
import objects.Session;

public class AddSessionToCourse {
	public static void startAdding(BufferedReader reader){
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

		//Add to database.
		String sql = "INSERT INTO Session(course, date, capacity, type) VALUES (?, ?, ?, ?)"; 
		Connection con = null;
		PreparedStatement preparedStatement = null;

		try {
			//Get the database connection.
			con = DatabaseAdapter.getConnection();
			//Prepare the SQL statement.
			preparedStatement = con.prepareStatement(sql);
			//Add the parameters.
			preparedStatement.setInt(1, session.getCourse());
			preparedStatement.setString(2, session.getDateString());
			preparedStatement.setInt(3, session.getCapacity());
			preparedStatement.setString(4, session.getType());
			//Execute the statement and get the result.
			preparedStatement.execute();
			System.out.println("The session has been successfully imported!");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
			//Close the connections.
			try {
				if (preparedStatement != null){
					preparedStatement.close();
				}
				if (con != null){
					con.close();
				}
			}
			catch (SQLException ex){
				System.out.println(ex.getMessage());
			}
		}
	}
}