package menus;

import java.io.BufferedReader;
import java.util.ArrayList;

import objects.Session;
import database_adapter.DatabaseAdapter;
import functions.CreateTimetableSlotForSession;

public class MenuCreateTimetableSlotForSession {
	
	public static void beginTimetableAllocation(BufferedReader reader){	
		ArrayList<Session> sessions = DatabaseAdapter.getAllSessions();

		while(true) {
			//List the starting message.
			System.out.println("Please choose the session for which you wish to add the timetable slot. Available sessions: ");
			//List the available sessions and quit option.
			for (Session session : sessions){
				System.out.println(session);
			}
			System.out.println("q: Quit");

			try {
				//Process the input.
				String option = reader.readLine();
				//Quit if q.
				if (option.equals("q")){
					break;
				}
				//Try to parse the input to an integer.
				int sessionId = Integer.parseInt(option);
				//Look for the specified session in the list of courses.
				for (Session session : sessions){
					if (session.getId() == sessionId){
						//Session found. Ask for timetable and return.
						CreateTimetableSlotForSession.createTimetableSlot(session, reader);
						return;
					}
				}
				//Session not found, raise exception to print message and continue.
				throw new Exception();
			} catch (Exception ex){
				System.out.println("Invalid course!");
			}
		}
	}
}
