package uk.ac.gla.psdteamk.sessions;

import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import uk.ac.gla.psdteamk.database.service.DatabaseAdapterService;
import uk.ac.gla.psdteamk.objects.*;
import uk.ac.gla.psdteamk.helpers.*;

public class CreateTimetableSlotForSession {
	/**
	 * Reads in the start and end time and adds the timetable slot to the session.
	 * @param session - The session you want to update.
	 * @param reader - Your BufferedReader object.
	 */
	static void createTimetableSlot(DatabaseAdapterService da, Session session, BufferedReader reader){
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
			con = da.getConnection();
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
