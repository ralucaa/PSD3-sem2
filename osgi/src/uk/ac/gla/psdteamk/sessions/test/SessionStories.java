package uk.ac.gla.psdteamk.sessions.test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.io.StoryFinder;
import org.junit.Test;

public class SessionStories {

	@Test
	public void runURLLoadedStoriesAsJUnit() {
		Embedder embedder = new SessionEmbedder();
		
		ArrayList<String> storyPaths = new ArrayList<String>();
		
		storyPaths.add("uk/ac/gla/psdteamk/sessions/test/stories/AddSessionToCourse.story");
		storyPaths.add("uk/ac/gla/psdteamk/sessions/test/stories/AssignRoomToTimetableSlot.story");
		storyPaths.add("uk/ac/gla/psdteamk/sessions/test/stories/BookTimetableSlot.story");
		storyPaths.add("uk/ac/gla/psdteamk/sessions/test/stories/CheckCompulsoryCourses.story"); // Broken
		storyPaths.add("uk/ac/gla/psdteamk/sessions/test/stories/CheckForClashes.story");
		storyPaths.add("uk/ac/gla/psdteamk/sessions/test/stories/CheckSessionDetails.story"); // Broken
		storyPaths.add("uk/ac/gla/psdteamk/sessions/test/stories/CreateTimetableSlotForSession.story"); // Broken
		storyPaths.add("uk/ac/gla/psdteamk/sessions/test/stories/ImportMyCampusCourse.story"); // Broken
		storyPaths.add("uk/ac/gla/psdteamk/sessions/test/stories/NFR_Performance0.story");
		storyPaths.add("uk/ac/gla/psdteamk/sessions/test/stories/NFR_Performance1.story");
		storyPaths.add("uk/ac/gla/psdteamk/sessions/test/stories/NFR_Performance2.story"); // Broken
		storyPaths.add("uk/ac/gla/psdteamk/sessions/test/stories/NFR_Performance3.story"); // Broken
		storyPaths.add("uk/ac/gla/psdteamk/sessions/test/stories/NFR_Performance4.story");
		storyPaths.add("uk/ac/gla/psdteamk/sessions/test/stories/NFR_Security0.story");
		storyPaths.add("uk/ac/gla/psdteamk/sessions/test/stories/NFR_Security1.story");
		storyPaths.add("uk/ac/gla/psdteamk/sessions/test/stories/SpecifySessionFrequency.story");
		
		Collections.reverse(storyPaths);
		
	    embedder.runStoriesAsPaths(storyPaths);
	}
}
