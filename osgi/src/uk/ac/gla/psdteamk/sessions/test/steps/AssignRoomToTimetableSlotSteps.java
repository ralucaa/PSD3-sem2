package uk.ac.gla.psdteamk.sessions.test.steps;


import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;

import uk.ac.gla.psdteamk.objects.Session;
import uk.ac.gla.psdteamk.sessions.service.SessionManagerService;
import uk.ac.gla.psdteamk.sessions.test.SetupFramework;
import static org.junit.Assert.assertEquals;

/**
 * Narrative: 
 * In order to have room bookings recorded 
 * As an administrator 
 * I want to assign a room to a timetable slot
 *  
*/
public class AssignRoomToTimetableSlotSteps extends Steps {
	private SessionManagerService service;
	private boolean result;
	private int sessionId;
	private int roomId;
	
	@Given("a $sessionId and $roomId")
	public void validSessionRoomId(int sessionId, int roomId) throws Exception {
		this.sessionId=sessionId;
		this.roomId=roomId;	
		this.service = SetupFramework.setup();
	}
	
	@When("the $assignRoom method is executed")
	public void assignRoom(){
		result=service.assignRoom(sessionId, roomId);
	}
	
	@Then("the method returns true")
	public void returnResultTrue(){
		assertEquals(true,result);
	}
	
	@Then("the method returns false")
	public void returnResultFalse(){
		assertEquals(false,result);
	}
	
	
}
