package uk.ac.gla.psdteamk.sessions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import uk.ac.gla.psdteamk.database.service.DatabaseAdapterService;
import uk.ac.gla.psdteamk.objects.*;

public class ImportMyCampusCourses {
	/**
	 * Imports the specified course to the database.
	 * @param course - The course to import.
	 */
	public static boolean importCourse(DatabaseAdapterService da, Course course) {
		String sql = "INSERT INTO \"Course\"(\"title\") VALUES (?)"; 
		Connection con = null;
		PreparedStatement preparedStatement = null;

		try {
			//Get the database connection.
			con = da.getConnection();
			//Prepare the SQL statement.
			preparedStatement = con.prepareStatement(sql);
			//Add the parameters.
			preparedStatement.setString(1, course.getTitle());
			//Execute the statement and get the result.
			preparedStatement.execute();

			return true;
		} catch (SQLException ex) {
			System.out.println("This course is already imported!");
			return false;
		} 
	}
}
