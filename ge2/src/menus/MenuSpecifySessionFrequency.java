package menus;

import java.io.BufferedReader;
import java.util.ArrayList;

import objects.Session;
import database_adapter.DatabaseAdapter;
import functions.SpecifySessionFrequency;

public class MenuSpecifySessionFrequency {
	/**
	 * Shows the session frequency specification menu.
	 * @param reader - Your current BufferedReader object.
	 */
	public static void beginSpecifyingSessionFrequency(BufferedReader reader) {

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

				System.out.println("Please specify the frequency:");

				int frequency = Integer.parseInt(reader.readLine());

				SpecifySessionFrequency.changeFrequency(sessionId, frequency);

			} catch (Exception ex){
				System.out.println(ex.getMessage());
			}
		}		
	}
}
