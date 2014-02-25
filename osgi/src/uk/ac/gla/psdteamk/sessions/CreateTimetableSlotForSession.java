package uk.ac.gla.psdteamk.sessions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import uk.ac.gla.psdteamk.database.service.DatabaseAdapterService;
import uk.ac.gla.psdteamk.objects.*;

//this one still has menu stuff in it!

class CreateTimetableSlotForSession {
	/**
	 * Reads in the start and end time and adds the timetable slot to the session.
	 * @param session - The session you want to update.
	 * @param reader - Your BufferedReader object.
	 */
	static void createTimetableSlot(DatabaseAdapterService da, Session session){
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
