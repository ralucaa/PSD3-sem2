package uk.ac.gla.psdteamk.sessions.test.steps;

import uk.ac.gla.psdteamk.sessions.service.SessionManagerService;
import uk.ac.gla.psdteamk.sessions.test.SetupFramework;

import org.jbehave.core.annotations.Alias;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;

import static org.junit.Assert.assertEquals;

public class SpecifySessionFrequencySteps extends Steps {
	private SessionManagerService service;
	private int session, frequency;
	private boolean result;
	private int lecturerToken;
	
	@Given("a username $username, a password $password, a session $session and a frequency $frequency")
	public void givenASessionAndAFrequency(String username, String password, int session, int frequency) throws Exception {
		//SetupFramework.defaultPopulate();
		service = SetupFramework.getSessionManagerService();
		this.session = session;
		this.frequency = frequency;
		lecturerToken = service.authenticate(username, password);
	}
	
	@When("the user updates the session frequency")
	public void userUpdatesTheSession() {
		result = service.changeFrequency(lecturerToken, session, frequency);
	}
	
	@Then("the session frequency should be updated in the database and the function should return $expectedResult")
	@Alias("the session frequency should not be updated in the database and the function should return $expectedResult")
	public void shouldBeAddedToTheDatabaseAndReturnTrue(String expectedResult) {
		boolean boolExpectedResult = Boolean.parseBoolean(expectedResult);
		assertEquals(boolExpectedResult, result);
	}
}
