package functions;

import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import database_adapter.DatabaseAdapter;
import objects.Course;
import stub_mycampus.MyCampusStub;

public class ImportMyCampusCourses {
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
						importCourse(course);
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
	
	private static void importCourse(Course course) {
		String sql = "INSERT INTO Course(id, title) VALUES (?, ?)"; 
		Connection con = null;
		PreparedStatement preparedStatement = null;

		try {
			//Get the database connection.
			con = DatabaseAdapter.getConnection();
			//Prepare the SQL statement.
			preparedStatement = con.prepareStatement(sql);
			//Add the parameters.
			preparedStatement.setInt(1, course.getId());
			preparedStatement.setString(2, course.getTitle());
			//Execute the statement and get the result.
			preparedStatement.execute();
			System.out.println("The course has been successfully imported!");
		} catch (SQLException ex) {
			System.out.println("This course is already imported!");
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
