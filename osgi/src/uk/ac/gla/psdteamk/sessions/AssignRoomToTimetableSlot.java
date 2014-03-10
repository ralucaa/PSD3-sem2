package uk.ac.gla.psdteamk.sessions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import uk.ac.gla.psdteamk.database.service.DatabaseAdapterService;

class AssignRoomToTimetableSlot {
	/**
	 * Assign a room to a session
	 * @param slotId - a slot reference in the database
	 * @param roomId - a room reference in the Central Room Booking database
	 */
	static boolean assignRoom(DatabaseAdapterService da, int slotId, int roomId) {
			String sql = "UPDATE \"TimetableSlot\" SET \"room\"=? WHERE \"id\"=?"; 
			Connection con = null;
			PreparedStatement preparedStatement = null;

			try {
				//Get the database connection.
				con = da.getConnection();
				//Prepare the SQL statement.
				preparedStatement = con.prepareStatement(sql);
				//Add the parameters.
				preparedStatement.setInt(1, roomId);
				preparedStatement.setInt(2, slotId);
				//Execute the statement and get the result.
				preparedStatement.execute();
				
				System.out.println("A room (id=" + roomId + ") has been assigned to slot (id=" + slotId + ")");
				return true;
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			} 
			return false;
	}

}
