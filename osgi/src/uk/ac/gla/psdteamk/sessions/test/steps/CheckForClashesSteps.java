package uk.ac.gla.psdteamk.sessions.test.steps;

import static org.junit.Assert.assertEquals;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;

import uk.ac.gla.psdteamk.sessions.service.SessionManagerService;
import uk.ac.gla.psdteamk.sessions.test.SetupFramework;

public class CheckForClashesSteps extends Steps {
	private SessionManagerService service;
	private boolean result;
	private int adminToken;
/*
	@Given("a course id $courseId")
	public void givenACourseId(int courseId) throws Exception{
		service = SetupFramework.getSessionManagerService();
		adminToken = service.authenticate("1111111A", "1111111A");
	}

	@When("an administrator checks for clashes")
	public void adminChecksForClashes() {
		//result = service.addSessionToCourse(adminToken, session);
	}	
	
	@Then("he should be informed that there are no timetable clashes")
	public void withoutTimetableClashes() {
		assertEquals(false, result);
	}
	
	@Then("he should be informed that there are timetable clashes")
	public void withTimetableClashes() {
		assertEquals(true, result);
	}	*/
}
