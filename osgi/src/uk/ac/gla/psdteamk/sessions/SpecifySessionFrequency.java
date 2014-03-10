package uk.ac.gla.psdteamk.sessions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import uk.ac.gla.psdteamk.database.service.DatabaseAdapterService;

class SpecifySessionFrequency {
	/**
	 * Change session frequency
	 * @param sessionId - a session reference in the database
	 * @param frequency - number of days that indicates how often the session occurs
	 */
	protected static boolean changeFrequency(DatabaseAdapterService da, int sessionId, int frequency) {
		// Check that the frequency and sessionId are valid.
		if (frequency < 0 || da.getSession(sessionId) == null) {
			return false;
		}
		
		String sql = "UPDATE \"Session\" SET \"frequency\"=? WHERE \"id\"=?"; 
		Connection con = null;
		PreparedStatement preparedStatement = null;

		try {
			//Get the database connection.
			con = da.getConnection();
			//Prepare the SQL statement.
			preparedStatement = con.prepareStatement(sql);
			//Add the parameters.
			preparedStatement.setInt(1, frequency);
			preparedStatement.setInt(2, sessionId);
			//Execute the statement and get the result.
			preparedStatement.execute();

			return true;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}
}