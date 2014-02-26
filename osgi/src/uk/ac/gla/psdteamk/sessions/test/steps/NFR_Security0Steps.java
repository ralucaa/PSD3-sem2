package uk.ac.gla.psdteamk.sessions.test.steps;

import uk.ac.gla.psdteamk.mycampus.service.MyCampusService;
import uk.ac.gla.psdteamk.objects.Account;
import uk.ac.gla.psdteamk.sessions.service.SessionManagerService;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

public class NFR_Security0Steps extends Steps {
	private MyCampusService service;
	private String username, password;
	private Account account;
	private boolean result;
	
	@Given("a username $username and a password $password")
	public void givenASessionAndAFrequency(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	@When("the user tries to authenticate")
	public void userTriesToAuthenticate() {
		account = service.authenticate(username, password);
	}
	
	@Then("he should be authenticated and an Account object should be returned")
	public void shouldBeAuthenticatedAndAccountNotNull() {
		assertNotNull(account);
	}
	
	@Then("he should not be authenticated and null should be returned")
	public void shouldNotBeAuthenticatedAndAccountNull() {
		assertNull(account);
	}
}
