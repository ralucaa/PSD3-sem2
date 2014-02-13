package menus;

import java.io.BufferedReader;
import java.util.ArrayList;

import objects.Account;
import objects.Session;
import database_adapter.DatabaseAdapter;
import functions.BookTimetableSlot;

public class MenuBookTimetableSlot {
	/**
	 * Book timetable slot for each session of a course
	 * 
	 */
	public static void beginSessionScheduling(BufferedReader reader, Account account) {

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


				BookTimetableSlot.bookSession(sessionId, account.getUsername());

			} catch (Exception ex){
				System.out.println(ex.getMessage());
			}
		}
	}
}
