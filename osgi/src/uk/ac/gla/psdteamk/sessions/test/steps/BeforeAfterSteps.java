package uk.ac.gla.psdteamk.sessions.test.steps;

import java.io.File;

import org.jbehave.core.annotations.AfterStories;
import org.jbehave.core.annotations.BeforeStories;
import org.jbehave.core.steps.Steps;

import uk.ac.gla.psdteamk.sessions.test.SetupFramework;

public class BeforeAfterSteps extends Steps{
	@BeforeStories
	public void beforeStories() throws Exception {
		SetupFramework.start();
	}
	
	@AfterStories
	public void afterStories() throws Exception {
		SetupFramework.stop();
	}
}