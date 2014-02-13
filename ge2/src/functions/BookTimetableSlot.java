package functions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database_adapter.DatabaseAdapter;

public class BookTimetableSlot {
	/**
	 * Assign a room to a session
	 * @param sessionId - a session reference in the database
	 * @param roomId - a room reference in the Central Room Booking database
	 */
	public static void bookSession(int sessionId, String username) {
		String sql = "INSERT INTO Registration(session, student) VALUES(?,?)"; 
		Connection con = null;
		PreparedStatement preparedStatement = null;

		try {
			//Get the database connection.
			con = DatabaseAdapter.getConnection();
			//Prepare the SQL statement.
			preparedStatement = con.prepareStatement(sql);
			//Add the parameters.
			preparedStatement.setInt(1, sessionId);
			preparedStatement.setString(2, username);
			//Execute the statement and get the result.
			preparedStatement.execute();

			System.out.println("A user (id=" + username + ") has been assigned to session (id=" + sessionId + ")");

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