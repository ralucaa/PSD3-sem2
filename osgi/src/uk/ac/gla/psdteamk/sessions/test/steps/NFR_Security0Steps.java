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

public class NFR_Security0Steps extends Steps {
	private SessionManagerService service;
	private String username, password;
	private int token;
	
	@BeforeScenario
	public void beforeScenario() throws Exception {
		SetupFramework.defaultPopulate();
		service = SetupFramework.getSessionManagerService();
	}
	
	@AfterScenario
	public void afterScenario() throws Exception {
	}
	
	@Given("a username $username and a password $password")
	public void givenASessionAndAFrequency(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	@When("the user tries to authenticate")
	public void userTriesToAuthenticate() {
		token = service.authenticate(username, password);
	}
	
	@Then("the user should be authenticated and a positive integer should be returned")
	public void shouldBeAuthenticatedAndAccountNotNull() {
		assertEquals((token >= 0), true);
	}
	
	@Then("the user should not be authenticated and -1 should be returned")
	public void shouldNotBeAuthenticatedAndAccountNull() {
		assertEquals(token, -1);
	}
}
