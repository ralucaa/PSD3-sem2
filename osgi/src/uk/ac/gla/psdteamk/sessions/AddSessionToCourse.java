package uk.ac.gla.psdteamk.sessions;

import uk.ac.gla.psdteamk.database.service.DatabaseAdapterService;
import uk.ac.gla.psdteamk.objects.*;

class AddSessionToCourse {
	/**
	 * Adds the given object to the database.
	 * @param session - The session object to add.
	 */
	static boolean addSessionToDatabase(DatabaseAdapterService da, Session session) {
		return da.addSessionToDatabase(session);
	}
}