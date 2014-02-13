package functions;

import helpers.DateTimeOps;

import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import database_adapter.DatabaseAdapter;
import objects.Session;

public class CreateTimetableSlotForSession {

	public static void startCreate(BufferedReader reader){	
		ArrayList<Session> sessions = DatabaseAdapter.getAllSessions();

		while(true) {
			//List the starting message.
			System.out.println("Please choose the session for which you wish to add the timetable slot. Available sessions: ");
			//List the available sessions and quit option.
			for (Session session : sessions){
				System.out.println(session);
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
				int sessionId = Integer.parseInt(option);
				//Look for the specified session in the list of courses.
				for (Session session : sessions){
					if (session.getId() == sessionId){
						//Session found. Ask for timetable and return.
						createTimetableSlot(session, reader);
						return;
					}
				}
				//Session not found, raise exception to print message and continue.
				throw new Exception();
			} catch (Exception ex){
				System.out.println("Invalid course!");
			}
		}
	}

	/**
	 * Reads in the start and end time and adds the timetable slot to the session.
	 * @param session - The session you want to update.
	 * @param reader - Your BufferedReader object.
	 */
	private static void createTimetableSlot(Session session, BufferedReader reader){
		while (true) {
			//Read the start time.
			try {
				System.out.println("Use q to abort. Start time in HH:mm format:");
				String input = reader.readLine();
				//See if abort requested.
				if (input.equals("q")){
					return;
				}
				session.setStart_time(DateTimeOps.parseTimeStringToJodaTime(input));
			} catch (Exception ex){
				System.out.println(ex.getMessage());
				continue;
			}

			//Read the start time.
			try {
				System.out.println("Use q to abort. End time in HH:mm format:");
				String input = reader.readLine();
				//See if abort requested.
				if (input.equals("q")){
					return;
				}
				session.setEnd_time(DateTimeOps.parseTimeStringToJodaTime(input));
				break;
			} catch (Exception ex){
				System.out.println(ex.getMessage());
			}
		}
		
		//Execute the query.
		String sql = "UPDATE Session SET start_time = ?, end_time = ? WHERE id = ?";
		Connection con = null;
		PreparedStatement preparedStatement = null;

		try {
			//Get the database connection.
			con = DatabaseAdapter.getConnection();
			//Prepare the SQL statement.
			preparedStatement = con.prepareStatement(sql);
			//Add the parameters.
			preparedStatement.setString(1, session.getStart_timeString());
			preparedStatement.setString(2, session.getEnd_timeString());
			preparedStatement.setInt(3, session.getId());
			//Execute the statement and get the result.
			preparedStatement.executeUpdate();
			System.out.println("The timetable has been successfully stored!");
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
