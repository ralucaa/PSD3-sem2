package uk.ac.gla.psdteamk.sessions;

import java.util.ArrayList;
import java.util.HashMap;

//import org.joda.time.DateTime;


import uk.ac.gla.psdteamk.database.service.DatabaseAdapterService;
import uk.ac.gla.psdteamk.objects.Session;
import uk.ac.gla.psdteamk.objects.TimetableSlot;

class CheckForClashes {
	/**
	 * return list of any clashing sessions, or null on failure
	 * @param courseId - the id of the course we need to find clashes for
	 * @return HashMap containing clashes
	 */
	
	static HashMap<Integer, Integer> checkForClashes(DatabaseAdapterService da, int courseId) {
	
		HashMap<Integer, Integer> output = new HashMap<Integer, Integer>();
		
		// Query the database
		ArrayList<TimetableSlot> timetableSlots = da.getAllTimetableSlots();
		ArrayList<Session> sessions = da.getAllSessions();
		
		// Map session IDs to the number of timetable slots
		int key;
		HashMap<Integer, Integer> slotCounts = new HashMap<Integer, Integer>();		
		for (TimetableSlot slot : timetableSlots) {
			key = slot.getSession_id();
			
			if (!slotCounts.containsKey(key)) slotCounts.put(key, 1);
			else slotCounts.put(key, slotCounts.get(key) + 1);
		}		
		
		System.out.println(slotCounts.toString());
				
		// Split sessions into two groups - for course courseId and the rest		
		int sessionId, sessionCourse;
		ArrayList<TimetableSlot> timetableSlotsThisCourse = new ArrayList<TimetableSlot>();
		ArrayList<TimetableSlot> timetableSlotsOthers = new ArrayList<TimetableSlot>();		

		for (Session session : sessions) {
			
			sessionId = session.getId();			
			sessionCourse = session.getCourse();			
			
			System.out.println("Session id: " + sessionId + " sessionCourse: " + sessionCourse);
			
			Integer value = slotCounts.get(sessionId);
			
			// Discard sessions that have multiple timetable slots
			if (value != null) {
				if (slotCounts.get(sessionId) == 1) {		
					
					// Categorise
					for (TimetableSlot t : timetableSlots) {					
						if (t.getSession_id() == sessionId) {											
							if (sessionCourse == courseId) timetableSlotsThisCourse.add(t); 
							else timetableSlotsOthers.add(t);
						}
					}
					
				}
			} else {
				System.out.println("KEY NOT FOUND!");
			}
		}
		System.out.println("======REACHED CHECKFORCLASHES 5=======");
		// Compare		
		for (TimetableSlot t1 : timetableSlotsThisCourse) {
			for (TimetableSlot t2 : timetableSlotsOthers) {
				output.put(t1.getId(), t2.getId());
				/*
				if (timesOverlap(t1.getDate(), t1.getStart_time(), t1.getEnd_time(), t2.getDate(), t2.getStart_time(), t2.getEnd_time())) {
					output.put(t1.getId(), t2.getId());
				}
				*/
				
			}
		}
				
		return output;
	}
	
	/**
	 * Check if 2 given timetable slots overlap
	 * @param d1 - date
	 * @param s1 - start_time
	 * @param e1 - end_time
	 * @param d2 - date 2
	 * @param s2 - start_time 2
	 * @param e2 - end_time 2
	 * @return true if times overlap
	 */
	/*
	private static boolean timesOverlap(DateTime d1, DateTime s1, DateTime e1, DateTime d2, DateTime s2, DateTime e2) {	
		boolean output = true;
		
		output = output && d1.equals(d2);
		output = output && ((s1.equals(s2)) || ((s1.isAfter(s2)) && (s1.isBefore(e2))));
		output = output && ((e1.equals(e2)) || ((e1.isAfter(s2)) && (e1.isBefore(e2))));
		
		return output;
	}
*/

}
