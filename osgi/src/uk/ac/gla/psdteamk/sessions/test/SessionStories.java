package uk.ac.gla.psdteamk.sessions.test;

import java.io.File;
import java.util.Collections;
import java.util.List;

import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.io.StoryFinder;
import org.junit.Test;

public class SessionStories {

	@Test
	public void runURLLoadedStoriesAsJUnit() {
		Embedder embedder = new SessionEmbedder();
		
		List<String> storyPaths = 
			getStoryPathsFromProjectBinDir();
		
		Collections.reverse(storyPaths);
		
		for (String path: storyPaths) {
			System.out.println("Story file: " + path);
		}
		
	    embedder.runStoriesAsPaths(storyPaths);
	}

	private List<String> getStoryPathsFromProjectBinDir() {
		
		StoryFinder finder = 
			new StoryFinder();
		
		File projectBinDir = 
			new File("./bin").getAbsoluteFile();
		
		String projectBinDirPath =
			projectBinDir.getPath();
					
		List<String> storyPaths =
			finder.findPaths(
				projectBinDirPath, "**/*.story", "");
		
		return storyPaths;
	}
}
