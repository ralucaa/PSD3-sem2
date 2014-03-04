package uk.ac.gla.psdteamk.sessions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import uk.ac.gla.psdteamk.database.service.DatabaseAdapterService;

class BookTimetableSlot {
	/**
	 * Assign a room to a session
	 * @param sessionId - a session reference in the database
	 * @param roomId - a room reference in the Central Room Booking database
	 */
	static boolean bookSession(DatabaseAdapterService da, int sessionId, String username) {
		String sql = "INSERT INTO \"Registration\"(\"session\", \"student\") VALUES(?,?)"; 
		Connection con = null;
		PreparedStatement preparedStatement = null;

		try {
			//Get the database connection.
			con = da.getConnection();
			//Prepare the SQL statement.
			preparedStatement = con.prepareStatement(sql);
			//Add the parameters.
			preparedStatement.setInt(1, sessionId);
			preparedStatement.setString(2, username);
			//Execute the statement and get the result.
			preparedStatement.execute();

			System.out.println("A user (id=" + username + ") has been assigned to session (id=" + sessionId + ")");
			return true;
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
		return false;
	}
}