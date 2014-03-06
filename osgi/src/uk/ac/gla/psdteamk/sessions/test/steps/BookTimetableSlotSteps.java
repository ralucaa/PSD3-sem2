package uk.ac.gla.psdteamk.sessions.test.steps;


import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import uk.ac.gla.psdteamk.objects.Account;
import uk.ac.gla.psdteamk.sessions.service.SessionManagerService;
import uk.ac.gla.psdteamk.sessions.test.SetupFramework;
import static org.junit.Assert.assertEquals;


public class BookTimetableSlotSteps {
		private SessionManagerService service;
		int sessionID;
		String username;		
		boolean output;
		private int studentToken;
		
		@BeforeScenario
		public void beforeScenario() throws Exception {
			SetupFramework.defaultPopulate();
			service = SetupFramework.getSessionManagerService();
			studentToken = service.authenticate("3333333B", "3333333B");
		}
		
		@AfterScenario
		public void afterScenario() throws Exception {
		}
		
		@Given("a student username $username and sessionID $sessionID")
		public void aStudentUsername(String username, int sessionID) {			
			this.sessionID = sessionID;
			this.username = username;
		}
		
		@When("the database request is made")
		public void queryDatabase() {
			output = service.bookSession(sessionID, username);
		}
		
		@Then("the output is true")
		public void theOutputIsTrue() {
			assertEquals(true, output);
		}	
		
		@Then("the output is false")
		public void theOutputIsFalse() {
			assertEquals(false, output);
		}	

}
