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
	private Course myCourse;
	private int lecturerToken;
	
	@Given("a valid MyCampus course $co")
	public void givenAMyCampusID(int co)  throws Exception {
		SetupFramework.defaultPopulate();
		service = SetupFramework.getSessionManagerService();
		lecturerToken = service.authenticate("2222222A", "2222222A");
		this.myCourse = new Course(co, "New course");
	}
	@Given("a false MyCampus course $co")
	public void givenAFalseMyCampusID(int co) throws Exception {
		SetupFramework.defaultPopulate();
		service = SetupFramework.getSessionManagerService();
		lecturerToken = service.authenticate("2222222A", "2222222A");
		this.myCourse = new Course(co, "New Course");
	}

	@When("I try to retrieve the course information")
	public void whenIRetrieveCourseInfo() {
		result = service.importCourse(lecturerToken, myCourse);
	}

	@Then("the result of the import should be $output")
	public void thenTheOutcomeShould(String output) {
		boolean boolOutput = Boolean.getBoolean(output);
		assertEquals(boolOutput, result);
	}
	
}
