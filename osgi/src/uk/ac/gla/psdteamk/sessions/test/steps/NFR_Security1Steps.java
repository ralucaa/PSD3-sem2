package uk.ac.gla.psdteamk.sessions.test.steps;

import uk.ac.gla.psdteamk.mycampus.service.MyCampusService;
import uk.ac.gla.psdteamk.objects.Account;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;

import static org.junit.Assert.assertEquals;

public class NFR_Security1Steps extends Steps {
	private MyCampusService service;
	private String username;
	private Account account;
	
	
	@Given("a username $username")
	public void givenASessionAndAFrequency(String username, String password) {
		this.username = username;
	}
	
	@When("the user authenticates")
	public void userAuthenticates() {
		account = service.authenticate(username, username);
	}
	
	@Then("he should be authenticated as a $type")
	public void shouldBeAuthenticatedAsAType(String type) {
		assertEquals(type, account.getType());
	}
}
