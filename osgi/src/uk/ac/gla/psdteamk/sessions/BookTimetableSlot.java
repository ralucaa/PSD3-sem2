package uk.ac.gla.psdteamk.sessions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import uk.ac.gla.psdteamk.database.service.DatabaseAdapterService;

class BookTimetableSlot {	

	/**
	 * Assign a student to timetable slot
	 * @param slotId - a session reference in the database
	 * @param roomId - a room reference in the Central Room Booking database
	 */
	static boolean bookSlot(DatabaseAdapterService da, int slotId, String username) {
		// Check if slotId exists in the database		
		if (da.getTimetableSlot(slotId) == null) {
			System.out.println("getTimetableSlot for slotId " + slotId + " was null");
			return false;
		}
		
		String sql = "INSERT INTO \"Registration\"(\"slot\", \"student\") VALUES(?,?)"; 
		Connection con = null;
		PreparedStatement preparedStatement = null;		
		
		try {
			//Get the database connection.
			con = da.getConnection();
			//Prepare the SQL statement.
			preparedStatement = con.prepareStatement(sql);
			//Add the parameters.
			preparedStatement.setInt(1, slotId);
			preparedStatement.setString(2, username);
			//Execute the statement and get the result.
			preparedStatement.execute();

			System.out.println("A user (id=" + username + ") has been assigned to slot (id=" + slotId + ")");
			return true;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}
}