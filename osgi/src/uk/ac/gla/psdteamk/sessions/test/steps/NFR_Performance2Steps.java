package uk.ac.gla.psdteamk.sessions.test.steps;

import uk.ac.gla.psdteamk.mycampus.service.MyCampusService;
import uk.ac.gla.psdteamk.objects.Account;
import uk.ac.gla.psdteamk.sessions.test.SetupFramework;
import static org.junit.Assert.assertEquals;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;

public class NFR_Performance2Steps extends Steps {
	private MyCampusService mcs;
	private int users;
	private boolean result;
	
	@Given("a number of users $u")
	public void givenADatabase(int u) {
		mcs = SetupFramework.getMyCampusService();
		this.users = u;
		result = true;
	}
	
	@When("I try to populate the database with the users")
	public void populateUserDB() {
		int unum = 4444444;
		for (int i = 0; i < users; i ++) {
			String username = unum + "Z";
			Account account = new Account(username, username, "Bob", "student");
			if (!mcs.addUser(account)) {
				result = false;
			}
			unum ++;
		}
	}
	
	@Then("the result of performance test 2 should be $output")
	public void check(String output) {
		boolean boolOutput = Boolean.parseBoolean(output);
		assertEquals(boolOutput, result);
	}
}
