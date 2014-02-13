package functions;

import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import database_adapter.DatabaseAdapter;
import objects.Session;

public class SpecifySessionFrequency {

	public static void scheduler(BufferedReader reader) {
		
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
}