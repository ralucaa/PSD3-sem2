package uk.ac.gla.psdteamk.sessions.test.steps;

import static org.junit.Assert.assertEquals;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import uk.ac.gla.psdteamk.objects.Course;
import uk.ac.gla.psdteamk.sessions.service.SessionManagerService;
import uk.ac.gla.psdteamk.sessions.test.SetupFramework;

import org.jbehave.core.steps.Steps;

public class ImportMyCampusCourseSteps extends Steps {
	private boolean result;
	private SessionManagerService service;
	private String courseTitle;
	private int lecturerToken;
	
	@Given("a MyCampus course with title $title")
	public void givenAMyCampusCourse(String title) throws Exception {
		//SetupFramework.defaultPopulate();
		service = SetupFramework.getSessionManagerService();
		lecturerToken = service.authenticate("2222222A", "2222222A");
		courseTitle = title;
	}

	@When("I try to import the MyCampus course")
	public void whenITryToImportMyCampusCourse() {
		result = service.importCourse(lecturerToken, courseTitle);
	}

	@Then("the result of the MyCampus import should be $output")
	public void thenTheOutcomeShould(String output) {
		boolean boolOutput = Boolean.parseBoolean(output);
		assertEquals(boolOutput, result);
	}
	
}
