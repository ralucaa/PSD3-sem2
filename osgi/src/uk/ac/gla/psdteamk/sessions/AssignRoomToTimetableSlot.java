package uk.ac.gla.psdteamk.sessions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import uk.ac.gla.psdteamk.database.service.DatabaseAdapterService;

public class AssignRoomToTimetableSlot {
	/**
	 * Assign a room to a session
	 * @param sessionId - a session reference in the database
	 * @param roomId - a room reference in the Central Room Booking database
	 */
	static void assignRoom(DatabaseAdapterService da, int sessionId, int roomId) {
			String sql = "UPDATE Session SET room=? WHERE id=?"; 
			Connection con = null;
			PreparedStatement preparedStatement = null;

			try {
				//Get the database connection.
				con = da.getConnection();
				//Prepare the SQL statement.
				preparedStatement = con.prepareStatement(sql);
				//Add the parameters.
				preparedStatement.setInt(1, roomId);
				preparedStatement.setInt(2, sessionId);
				//Execute the statement and get the result.
				preparedStatement.execute();
				
				System.out.println("A room (id=" + roomId + ") has been assigned to session (id=" + sessionId + ")");
				
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
