package uk.ac.gla.psdteamk.sessions.test.steps;

import static org.junit.Assert.assertEquals;

import org.jbehave.core.annotations.Alias;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;  
import org.jbehave.core.annotations.When;  

import uk.ac.gla.psdteamk.sessions.service.SessionManagerService;
import uk.ac.gla.psdteamk.sessions.test.SetupFramework;

import org.jbehave.core.steps.Steps;

public class CheckSessionDetailsSteps extends Steps{
	private boolean detailsChecked;
	private SessionManagerService service;
	private int lecturerToken;
	private int id;

	@Given("some nice session $id")
	@Alias("an invalid session id with characters $id")
	public void givenASession(int id) throws Exception {
		//SetupFramework.defaultPopulate();
		service = SetupFramework.getSessionManagerService();
		lecturerToken = service.authenticate("2222222A", "2222222A");

		this.id = id;
	}  

	@When("I want to check the session details")  
	public void whenIcheck() {  
		//check details
		detailsChecked = service.checkSessionDetails(this.lecturerToken, id);
	}

	@When("I want to check the timetable slots for this session")  
	public void whenIcheckTimetableSlots() {  
		//check details
		detailsChecked = service.checkSessionDetails(this.lecturerToken, id);
	}  

	@Then("the function should fetch the session information from the database and the function should return time, location, students, tutors for that session")  
	public void thenTheOutcomeShould() {  
		assertEquals(true, detailsChecked);
	}
	@Then("the function should get no records from the database and the function should return an error")  
	public void thenFail() {  
		assertEquals(false, detailsChecked);
	}
	@Then("the program return all the timetable slots for this session")  
	public void thenReturnTimetableSlots() {  
		assertEquals(true, detailsChecked);
	} 
}
