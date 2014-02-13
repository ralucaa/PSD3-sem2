package functions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database_adapter.DatabaseAdapter;
import objects.Course;

public class ImportMyCampusCourses {
	/**
	 * Imports the specified course to the database.
	 * @param course - The course to import.
	 */
	public static void importCourse(Course course) {
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
