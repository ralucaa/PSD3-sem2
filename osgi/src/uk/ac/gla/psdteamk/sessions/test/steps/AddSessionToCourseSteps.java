package uk.ac.gla.psdteamk.sessions.test.steps;

import uk.ac.gla.psdteamk.objects.Session;
import uk.ac.gla.psdteamk.sessions.service.SessionManagerService;
import uk.ac.gla.psdteamk.sessions.test.SetupFramework;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;

import static org.junit.Assert.assertEquals;

public class AddSessionToCourseSteps extends Steps {
	// Default valid values. To be used when creating sessions for which only specific parameters need to be tested.
	private static final String D_TYPE = "lecture";
	private static final int D_FREQ = 7, D_COMPULSORY = 1;
	
	private SessionManagerService service;
	private Session session;
	private boolean result;
	private int lecturerToken;
	
	@Given("a new session for course $course")
	public void givenASessionForAValidCourse(int course) throws Exception{
		SetupFramework.defaultPopulate();
		service = SetupFramework.getSessionManagerService();
		lecturerToken = service.authenticate("2222222A", "2222222A");
		this.session = new Session(course, D_COMPULSORY, D_FREQ, D_TYPE);
	}
	
	@When("a user tries to add it to the database")
	public void userTriesToAddToTheDatabase() {
		result = service.addSessionToCourse(lecturerToken, session);
	}
	
	@Then("the result of the addition should be $output")
	public void shouldBeAddedToTheDatabaseAndReturnTrue(String output) {
		boolean boolOutput = Boolean.parseBoolean(output);
		assertEquals(boolOutput, result);
	}
}
