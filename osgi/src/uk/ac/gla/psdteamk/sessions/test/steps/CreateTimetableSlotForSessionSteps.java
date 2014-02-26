package uk.ac.gla.psdteamk.sessions.test.steps;


import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;

import uk.ac.gla.psdteamk.objects.Session;
import uk.ac.gla.psdteamk.sessions.service.SessionManagerService;
import static org.junit.Assert.assertEquals;

/**
 * Narrative:
In order that rooms can be assigned to slots
As an administrator
I want to create a timetable slot for a session
*/

public class CreateTimetableSlotForSessionSteps extends Steps{
	private SessionManagerService service;
	private boolean result;
	private Session session;
	
	@Given("a $session")
	public void aSession(Session session){
		this.session=session;
	}
	
	@When("the administrator tries to create a timetable slot for a session")
	public void createSlot(Session session){
		result=service.createTimetableSlot(session);
	}
	
	@Then("true is returned")
	public void returnResultTrue(){
		assertEquals(true,result);
	}
	
	@Then("false is returned")
	public void returnResultFalse(){
		assertEquals(false,result);
	}
	

	
}
