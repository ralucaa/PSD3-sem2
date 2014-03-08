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
import static org.junit.Assert.fail;

public class NFR_Performance3Steps extends Steps {
	private Session mySession;
	private int numOfSessions = 0;
	private SessionManagerService service;
	private int adminToken;
	private int id;
	
	@BeforeScenario
	public void beforeScenario() throws Exception {
		SetupFramework.defaultPopulate();
		service = SetupFramework.getSessionManagerService();
		adminToken = service.authenticate("1111111A", "11111111A");
	}
	
	@AfterScenario
	public void afterScenario() throws Exception {
	}
	
	@Given("to an admin tries to get a session $id")
	public void assignSession(int id) {
		this.id = id;
	}
	
	@When("I try to retrieve the session timetable slot number")
	public void getSessionsNumber() {
		//no such functionality
	}
	
	@Then("a session slot number should be greater or equal to 20 ")
	public void shouldBeGreaterThanTwenty() {
		if(numOfSessions < 0){
			fail("feature not implemented yet");
		}else{
			assertEquals(true, (numOfSessions>=20));
		}
	}

}
