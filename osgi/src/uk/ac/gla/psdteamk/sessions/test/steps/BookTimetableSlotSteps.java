package uk.ac.gla.psdteamk.sessions.test.steps;


import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import uk.ac.gla.psdteamk.objects.Account;
import uk.ac.gla.psdteamk.sessions.service.SessionManagerService;

import static org.junit.Assert.assertEquals;


public class BookTimetableSlotSteps {
		private SessionManagerService service;
		int sessionID;
		String username;		
		boolean output;
		
		@Given("a student username $username and sessionID $sessionID")
		public void aStudentUsername(String username, int sessionID) {			
			this.sessionID = sessionID;
			this.username = username;
		}
		
		@When("the database request is made")
		public void queryDatabase() {
			output = service.bookSession(sessionID, username);
		}
		
		@Then("the output is $output")
		public void theOutputIs(boolean output) {
			assertEquals(this.output, output);
		}	

}
