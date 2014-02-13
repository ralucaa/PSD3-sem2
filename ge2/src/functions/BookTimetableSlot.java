package functions;

import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import database_adapter.DatabaseAdapter;
import objects.Account;
import objects.Session;

public class BookTimetableSlot {

	/**
	 * Book timetable slot for each session of a course
	 * 
	 */
	public static void scheduler(BufferedReader reader, Account account) {
		
		ArrayList<Session> sessions = DatabaseAdapter.getAllSessions();
		
		while(true) {

			System.out.println("Please select a timetable slot:");
			
			for (Session session : sessions) {
				System.out.println(session.getId() + " - " + DatabaseAdapter.getCourse(session.getCourse()).getTitle() + " " + session.getType());
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

				// Look for the specified course in the list of courses.


				bookSession(sessionId, account.getUsername());

			} catch (Exception ex){
				System.out.println(ex.getMessage());
			}
		}		
		
	}

	/**
	 * Assign a room to a session
	 * @param sessionId - a session reference in the database
	 * @param roomId - a room reference in the Central Room Booking database
	 */
	private static void bookSession(int sessionId, String username) {
			String sql = "INSERT INTO Registration(session, student) VALUES(?,?)"; 
			Connection con = null;
			PreparedStatement preparedStatement = null;

			try {
				//Get the database connection.
				con = DatabaseAdapter.getConnection();
				//Prepare the SQL statement.
				preparedStatement = con.prepareStatement(sql);
				//Add the parameters.
				preparedStatement.setInt(1, sessionId);
				preparedStatement.setString(2, username);
				//Execute the statement and get the result.
				preparedStatement.execute();
				
				System.out.println("A user (id=" + username + ") has been assigned to session (id=" + sessionId + ")");
				
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