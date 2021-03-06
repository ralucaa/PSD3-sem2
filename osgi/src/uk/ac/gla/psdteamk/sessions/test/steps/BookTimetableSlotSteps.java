package uk.ac.gla.psdteamk.sessions.test.steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import uk.ac.gla.psdteamk.sessions.service.SessionManagerService;
import uk.ac.gla.psdteamk.sessions.test.SetupFramework;
import static org.junit.Assert.assertEquals;


public class BookTimetableSlotSteps {
		private SessionManagerService service;		
		int slotId;
		boolean result;
		private int studentToken;
		
		@Given("a slotId $slotId")
		public void aStudentUsername(int slotId) throws Exception {
			//SetupFramework.defaultPopulate();
			service = SetupFramework.getSessionManagerService();
			studentToken = service.authenticate("3333333C", "3333333C");		
			this.slotId = slotId;
		}
		
		@When("the student tries to book the timetable slot")
		public void queryDatabase() {
			result = service.bookSlot(studentToken, slotId);
		}
		
		@Then("the booking function's output is $output")
		public void theOutputIsTrue(String output) {
			boolean boolOutput = Boolean.parseBoolean(output);
			assertEquals(result, boolOutput);
		}

}
