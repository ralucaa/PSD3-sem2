package functions;

import helpers.DatabaseAdapter;

import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.Course;
import objects.Session;

public class SpecifySessionFrequency {

	public static void scheduler(BufferedReader reader) {
		
		ArrayList<Session> sessions = getAllSessions();
		
		while(true) {

			System.out.println("Please select a timetable slot:");
			
			for (Session session : sessions) {
				System.out.println(session.getId() + " - " + getCourse(session.getCourse()).getTitle() + " " + session.getType());
			}

			System.out.println("q: Quit");

			try {

				// Process the input.
				String option = reader.readLine();

				// Quit if q.
				if (option.equals("q")){
					break;
				}

				// Try to parse the input to an integer.
				int sessionId = Integer.parseInt(option);

				System.out.println("Please specify the frequency:");
				
				int frequency = Integer.parseInt(reader.readLine());
								
				changeFrequency(sessionId, frequency);

			} catch (Exception ex){
				System.out.println(ex.getMessage());
			}
		}		
	}
	
	/**
	 * Change session frequency
	 * @param sessionId - a session reference in the database
	 * @param frequency - number of days that indicates how often the session occurs
	 */
	private static void changeFrequency(int sessionId, int frequency) {
			String sql = "UPDATE Session SET frequency=? WHERE id=?"; 
			Connection con = null;
			PreparedStatement preparedStatement = null;

			try {
				//Get the database connection.
				con = DatabaseAdapter.getConnection();
				//Prepare the SQL statement.
				preparedStatement = con.prepareStatement(sql);
				//Add the parameters.
				preparedStatement.setInt(1, frequency);
				preparedStatement.setInt(2, sessionId);
				//Execute the statement and get the result.
				preparedStatement.execute();
				
				System.out.println("Frequency of value=" + frequency + " has been assigned to session (id=" + sessionId + ")");
				
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
	
	/**
	 * Retrieve all sessions from the database
	 * @return an ArrayList of Session objects
	 */
	private static ArrayList<Session> getAllSessions() {		

		ArrayList<Session> r = new ArrayList<Session>();

		// Retrieve room details from the database
		String query = "SELECT * FROM Session";
		Connection con = null;
		PreparedStatement preparedStatement = null;		

		try {
			// Get the database connection
			con = DatabaseAdapter.getConnection();
			// Prepare the SQL statement
			preparedStatement = con.prepareStatement(query);
			// Execute the statement and get the result			
			ResultSet rs = preparedStatement.executeQuery();			

			// Iterate through the result set
			while (rs.next()) {
				r.add(new Session(
						rs.getInt(1), 		// id
						rs.getInt(2), 		// course
						rs.getDate(3), 		// date
						rs.getTime(4), 		// start_time
						rs.getTime(5), 		// end_time
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
	private static Course getCourse(int id) {	

		// Retrieve room details from the database
		String query = "SELECT title FROM Course WHERE id = ?";
		Connection con = null;
		PreparedStatement preparedStatement = null;

		try {
			// Get the database connection.
			con = DatabaseAdapter.getConnection();
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