package uk.ac.gla.psdteamk.sessions.test.steps;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;

import uk.ac.gla.psdteamk.sessions.service.SessionManagerService;
import uk.ac.gla.psdteamk.sessions.test.SetupFramework;

public class CheckForClashesSteps extends Steps {
	
	/*
	private SessionManagerService service;
	private boolean result;
	private HashMap<Integer, Integer> resultMap;
	private int courseId;
	private int adminToken;

	@Given("a course with id $courseId")
	public void givenACourseId(int courseId) throws Exception{
		this.courseId = courseId;
		System.out.println("======REACHED GIVEN=======");
		service = SetupFramework.getSessionManagerService();
		resultMap = new HashMap<Integer, Integer>();
		adminToken = service.authenticate("1111111A", "1111111A");
	}

	@When("an administrator checks for clashes")
	public void adminChecksForClashes() {
		resultMap = service.checkForClashes(adminToken, courseId);
		result = resultMap.isEmpty(); // Check if there are any clashes
		//result = true;
	}	
	
	@Then("he should be informed that there are no timetable clashes")
	public void withoutTimetableClashes() {
		assertEquals(true, result);
	}
	
	@Then("he should be informed that there are timetable clashes")
	public void withTimetableClashes() {
		assertEquals(false, result);
	}	
	*/
	
}
