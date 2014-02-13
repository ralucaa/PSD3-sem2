package functions;

import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import database_adapter.DatabaseAdapter;
import objects.Room;
import objects.Session;
import stub_central_room_booking.CentralRoomBookingStub;

public class AssignRoomToTimetableSlot {

	private static ArrayList<Room> rooms;
	private static ArrayList<Session> sessions;

	private AssignRoomToTimetableSlot() {
		rooms = CentralRoomBookingStub.getRooms();
		sessions = DatabaseAdapter.getAllSessions();
	}

	public static void allocator(BufferedReader reader) {

		rooms = CentralRoomBookingStub.getRooms();
		sessions = DatabaseAdapter.getAllSessions();
		
		while(true) {

			System.out.println("Please select a timetable slot:");
			
			for (Session session : sessions) {				
				System.out.println(session.getId() + " - " + DatabaseAdapter.getCourse(session.getCourse()).getTitle() + " " + session.getType());
			}

			System.out.println("q: Quit");

			try {

				// Process the input.
				String option = reader.readLine();

				// Quit if q.
				if (option.equals("q")){
					break;
				}

				// Try to parse the input to an integer.
				int sessionId = Integer.parseInt(option);

				// Look for the specified course in the list of courses.

				System.out.println("Please enter the id of the room that you would like to assign to the session:");

				for (Room room : rooms) {
					System.out.println(room.getId() + " - " + room.getBuilding() + " - " + room.getRoom());
				}

				// Process the input.
				int roomId = Integer.parseInt(reader.readLine());

				assignRoom(sessionId, roomId);

			} catch (Exception ex){
				System.out.println(ex.getMessage());
			}
		}		
	}

	/**
	 * Assign a room to a session
	 * @param sessionId - a session reference in the database
	 * @param roomId - a room reference in the Central Room Booking database
	 */
	private static void assignRoom(int sessionId, int roomId) {
			String sql = "UPDATE Session SET room=? WHERE id=?"; 
			Connection con = null;
			PreparedStatement preparedStatement = null;

			try {
				//Get the database connection.
				con = DatabaseAdapter.getConnection();
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
