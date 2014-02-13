package uk.ac.gla.psdteamk.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import uk.ac.gla.psdteamk.objects.*;
import uk.ac.gla.psdteamk.database.service.DatabaseAdapterService;

public class DatabaseAdapter implements DatabaseAdapterService {
	private static final String DB_DRIVER = "org.sqlite.JDBC";
	private static final String DB_CONNECTION = "jdbc:sqlite:res/PSD3.s3db";

	/**
	 * Attempts to create a connection and returns it.
	 * Remember to close the connection after using it!!!
	 * @return the connection or null if something failed
	 */
	public Connection getConnection(){
		//Attempt to load the driver.
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}

		//Attempt to create the connection and return it.
		try {
			return DriverManager.getConnection(DB_CONNECTION);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
 
		//Will return null if anything failed.
		return null;
	}
	
	/**
	 * Gets all the courses available in the database.
	 * @return the courses
	 */
	public ArrayList<Course> getAllCourses(){
		//Attempt to get the user's type.
		String sql = "SELECT id, title FROM Course";
		Connection con = null;
		PreparedStatement preparedStatement = null;

		try {
			//Get the database connection.
			con = getConnection();
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
		return null;
	}
	
	/**
	 * Retrieve all sessions from the database
	 * @return an ArrayList of Session objects
	 */
	public ArrayList<Session> getAllSessions() {		

		ArrayList<Session> r = new ArrayList<Session>();

		// Retrieve room details from the database
		String query = "SELECT * FROM Session";
		Connection con = null;
		PreparedStatement preparedStatement = null;		

		try {
			// Get the database connection
			con = getConnection();
			// Prepare the SQL statement
			preparedStatement = con.prepareStatement(query);
			// Execute the statement and get the result			
			ResultSet rs = preparedStatement.executeQuery();			

			// Iterate through the result set
			while (rs.next()) {
				r.add(new Session(
						rs.getInt(1), 		// id
						rs.getInt(2), 		// course
						rs.getString(3), 		// date
						rs.getString(4), 		// start_time
						rs.getString(5), 		// end_time
						rs.getInt(6), 		// frequency
						rs.getString(7),	// room
						rs.getInt(8), 		// capacity
						rs.getString(9)		// time
						) 	
						); 
			}		

			return r;

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
			// Close the connections.
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

		// Will only reach this if coming from the catch block.
		return null;			

	}	

	/**
	 * Get all the information about a course that corresponds to given id
	 * @param id - course reference in the database
	 * @return Course object
	 */
	public Course getCourse(int id) {	

		// Retrieve room details from the database
		String query = "SELECT title  FROM Course WHERE id = ?";
		Connection con = null;
		PreparedStatement preparedStatement = null;

		try {
			// Get the database connection.
			con = getConnection();
			// Prepare the SQL statement.
			preparedStatement = con.prepareStatement(query);
			// Add the parameters.
			preparedStatement.setInt(1, id);
			// Execute the statement and get the result.
			ResultSet rs = preparedStatement.executeQuery();
			// Advance to first element.
			rs.next();
			// Return the value.
			return new Course(id, rs.getString(1));
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

		// Will only reach this if coming from the catch block.
		return null;			
	}
}
