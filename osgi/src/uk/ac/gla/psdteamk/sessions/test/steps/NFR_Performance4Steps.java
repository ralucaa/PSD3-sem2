package uk.ac.gla.psdteamk.sessions.test.steps;

import static org.junit.Assert.assertEquals;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;

import uk.ac.gla.psdteamk.mycampus.service.MyCampusService;
import uk.ac.gla.psdteamk.objects.Account;
import uk.ac.gla.psdteamk.sessions.service.SessionManagerService;
import uk.ac.gla.psdteamk.sessions.test.SetupFramework;

public class NFR_Performance4Steps extends Steps {
	private static final String D_NAME = "Ptest Four Usr";
	private MyCampusService mcs;
	private SessionManagerService sms;
	private int result;
	
	@Given("$number concurrently logged in users")
	public void givenANumberOfConcurrentlyLoggedInUsers(int number) {
		mcs = SetupFramework.getMyCampusService();
		sms = SetupFramework.getSessionManagerService();
		int startNo = 3000000;
		for (int i = startNo; i < startNo + number; i++) {
			// Create the new user.
			String username = i + "T";
			Account account = new Account(username, username, D_NAME, Account.TYPE_STUDENT);
			mcs.addUser(account);
			// Authenticate him.
			sms.authenticate(username, username);
		}

	}
	
	@When("the system checks the number of logged in users")
	public void systemChecksNumberOfLoggedInUsers() {
		result = sms.getLoggedInUserCount();
	}
	
	@Then("at least $number users should be logged in")
	public void atLeastNumberUsersShouldBeLoggedIn(int number) {
		assertEquals(true, (result >= number));
	}
}
