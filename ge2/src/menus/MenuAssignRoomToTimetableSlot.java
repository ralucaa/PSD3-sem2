package menus;

import java.io.BufferedReader;
import java.util.ArrayList;

import objects.Room;
import objects.Session;
import stub_central_room_booking.CentralRoomBookingStub;
import database_adapter.DatabaseAdapter;
import functions.AssignRoomToTimetableSlot;

public class MenuAssignRoomToTimetableSlot {
	/**
	 * Shows the room assigning menu.
	 * @param reader - Your current reader.
	 */
	public static void beginRoomAssignment(BufferedReader reader) {
		ArrayList<Room> rooms = CentralRoomBookingStub.getRooms();
		ArrayList<Session> sessions = DatabaseAdapter.getAllSessions();
		
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

				AssignRoomToTimetableSlot.assignRoom(sessionId, roomId);

			} catch (Exception ex){
				System.out.println(ex.getMessage());
			}
		}		
	}
}
