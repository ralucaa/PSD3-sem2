package uk.ac.gla.psdteamk.sessions;

import uk.ac.gla.psdteamk.database.service.DatabaseAdapterService;
import uk.ac.gla.psdteamk.objects.*;

class CreateTimetableSlotForSession {
	/**
	 * Reads in the start and end time and adds the timetable slot to the session.
	 * @param session - The session you want to update.
	 * @param reader - Your BufferedReader object.
	 */
	static boolean createTimetableSlot(DatabaseAdapterService da, TimetableSlot timetableSlot){
		return da.addTimetableSlotToDatabase(timetableSlot);
	}
}
