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

public class NFR_Security1Steps extends Steps {
	private SessionManagerService service;
	private String username, password;
	private int token;

	@Given("a username $username with password $password")
	public void givenASessionAndAFrequency(String username, String password) throws Exception {
		SetupFramework.defaultPopulate();
		service = SetupFramework.getSessionManagerService();
		this.username = username;
		this.password = password;
	}
	
	@When("the user authenticates")
	public void userAuthenticates() {
		token = service.authenticate(username, password);
	}
	
	@Then("the user should be authenticated as a $type")
	public void shouldBeAuthenticatedAsAType(String type) {
		assertEquals(type, service.accountGetType(token));
	}
}
