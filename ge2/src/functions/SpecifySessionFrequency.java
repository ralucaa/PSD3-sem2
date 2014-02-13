package functions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database_adapter.DatabaseAdapter;

public class SpecifySessionFrequency {
	/**
	 * Change session frequency
	 * @param sessionId - a session reference in the database
	 * @param frequency - number of days that indicates how often the session occurs
	 */
	public static void changeFrequency(int sessionId, int frequency) {
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