package stub_central_room_booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.Account;
import objects.Course;
import objects.Room;
import stub_mycampus.MyCampusDatabaseAdapter;

public class CentralRoomBookingStub {
	
	/**
	 * Get details about a room from the Central Room Booking System
	 * @param id - room reference in the database
	 * @return Room object that corresponds to the provided id in the database
	 */
	public static Room getRoom(int id) {	
		
		// Retrieve room details from the database
		String query = "SELECT building, room FROM Room WHERE id = ?";
		Connection con = null;
		PreparedStatement preparedStatement = null;
		
		try {
			// Get the database connection.
			con = MyCampusDatabaseAdapter.getConnection();
			// Prepare the SQL statement.
			preparedStatement = con.prepareStatement(query);
			// Add the parameters.
			preparedStatement.setInt(1, id);
			// Execute the statement and get the result.
			ResultSet rs = preparedStatement.executeQuery();
			// Advance to first element.
			rs.next();
			// Return the value.
			return new Room(id, rs.getString(1), rs.getString(2));
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
		
		// Will only reach this if coming from the catch block.
		return null;			
	}
	
	/**
	 * Retrieve all rooms listed in Central Room Booking system
	 * @return an ArrayList of Room objects
	 */
	public static ArrayList<Room> getRooms() {		
		
		ArrayList<Room> r = null;
		
		// Retrieve room details from the database
		String query = "SELECT * FROM Room";
		Connection con = null;
		PreparedStatement preparedStatement = null;		

		try {
			// Get the database connection.
			con = MyCampusDatabaseAdapter.getConnection();
			// Prepare the SQL statement.
			preparedStatement = con.prepareStatement(query);
			// Execute the statement and get the result.			
			ResultSet rs = preparedStatement.executeQuery();			
			
			// Iterate through the result set
			while (rs.next()) {
				r.add(new Room(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}		
			
			return r;
			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
			// Close the connections.
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
		
		// Will only reach this if coming from the catch block.
		return null;			
			
	}	
}
