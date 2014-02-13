package uk.ac.gla.psdteamk.sessions;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import uk.ac.gla.psdteamk.database.service.DatabaseAdapterService;
import uk.ac.gla.psdteamk.objects.*;

class AddSessionToCourse {
	/**
	 * Adds the given object to the database.
	 * @param session - The session object to add.
	 */
	static void addSessionToDatabase(DatabaseAdapterService da, Session session) {
		//Add to database.
		String sql = "INSERT INTO Session(course, date, capacity, type) VALUES (?, ?, ?, ?)"; 
		Connection con = null;
		PreparedStatement preparedStatement = null;

		try {
			//Get the database connection.
			con = da.getConnection();
			//Prepare the SQL statement.
			preparedStatement = con.prepareStatement(sql);
			//Add the parameters.
			preparedStatement.setInt(1, session.getCourse());
			preparedStatement.setString(2, session.getDateString());
			preparedStatement.setInt(3, session.getCapacity());
			preparedStatement.setString(4, session.getType());
			//Execute the statement and get the result.
			preparedStatement.execute();
			System.out.println("The session has been successfully imported!");
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