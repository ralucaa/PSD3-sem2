package uk.ac.gla.psdteamk.sessions.test.steps;

import uk.ac.gla.psdteamk.mycampus.service.MyCampusService;
import uk.ac.gla.psdteamk.objects.Account;
import uk.ac.gla.psdteamk.objects.Session;
import uk.ac.gla.psdteamk.sessions.service.SessionManagerService;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertNotNull;

public class NFR_Performance3 extends Steps {
	private Session mySession;
	private int numOfSessions;

	
	@Given("a session $session")
	public void assignSession(String session) {
		this.mySession = new Session();
	}
	
	@When("I try to retrieve the session timetable slot number")
	public void getSessionsNumber() {
		numOfSessions = mySession.getNumberOfSlots();
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
