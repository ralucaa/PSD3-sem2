package functions;

import helpers.DatabaseAdapter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.Course;

public class AddSessionToCourse {
	
	
	/**
	 * Gets all the courses available in MyCampus.
	 * @return the courses
	 */
	public static ArrayList<Course> getAllCourses(){
		//Attempt to get the user's type.
		String sql = "SELECT id, title FROM Course";
		Connection con = null;
		PreparedStatement preparedStatement = null;
		
		try {
			//Get the database connection.
			con = DatabaseAdapter.getConnection();
			//Prepare the SQL statement.
			preparedStatement = con.prepareStatement(sql);
			//Execute the statement and get the result.
			ResultSet rs = preparedStatement.executeQuery();
			//Read the courses.
			ArrayList<Course> courses = new ArrayList<Course>();
			while (rs.next()){
				courses.add(new Course(rs.getInt(1), rs.getString(2)));
			}
			//Return the courses.
			return courses;
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
