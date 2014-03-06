package uk.ac.gla.psdteamk.sessions.test.steps;

import uk.ac.gla.psdteamk.sessions.service.SessionManagerService;
import uk.ac.gla.psdteamk.sessions.test.SetupFramework;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;
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
	
	@BeforeScenario
	public void beforeScenario() throws Exception {
		SetupFramework.defaultPopulate();
		service = SetupFramework.getSessionManagerService();
		lecturerToken = service.authenticate("2222222A", "2222222A");
	}
	
	@AfterScenario
	public void afterScenario() throws Exception {
	}
	
	@Given("a session $session and a frequency $frequency")
	public void givenASessionAndAFrequency(int session, int frequency) {
		this.session = session;
		this.frequency = frequency;
	}
	
	@When("the user updates the session")
	public void userUpdatesTheSession() {
		result = service.changeFrequency(lecturerToken, session, frequency);
	}
	
	@Then("the session frequency should be updated in the database and the function should return true")
	public void shouldBeAddedToTheDatabaseAndReturnTrue() {
		assertEquals(true, result);
	}
	
	@Then("the session frequency should not be updated in the database and the function should return false")
	public void shouldNotBeAddedToTheDatabaseAndReturnFalse() {
		assertEquals(false, result);
	}
}
