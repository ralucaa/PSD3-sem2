package uk.ac.gla.psdteamk.sessions.test.steps;


import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import uk.ac.gla.psdteamk.sessions.service.SessionManagerService;
import uk.ac.gla.psdteamk.sessions.test.SetupFramework;
import static org.junit.Assert.assertEquals;


public class BookTimetableSlotSteps {
		private SessionManagerService service;		
		int slotId;
		boolean output;
		private int studentToken;
		
		@Given("a slotId $slotId")
		public void aStudentUsername(int slotId) throws Exception {
			SetupFramework.defaultPopulate();
			service = SetupFramework.getSessionManagerService();
			studentToken = service.authenticate("3333333C", "3333333C");		
			this.slotId = slotId;
		}
		
		@When("the database request is made")
		public void queryDatabase() {
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
			System.out.println("queryDabase() - before booking the slot");
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
			
			output = service.bookSlot(studentToken, slotId);
			
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
			System.out.println("queryDabase() - after booking the slot");
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		}
		
		@Then("the booking function's output is true")
		public void theOutputIsTrue() {
			assertEquals(true, output);
		}	
		
		@Then("the booking function's output is false")
		public void theOutputIsFalse() {
			assertEquals(false, output);
		}	

}
