package uk.ac.gla.psdteamk.sessions.test.steps;


import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;
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
	private int adminToken;
	
	@BeforeScenario
	public void beforeScenario() throws Exception {
		SetupFramework.setUp();
		//SetupFramework.defaultPopulate();
		service = SetupFramework.getSessionManagerService();
		adminToken = service.authenticate("admin", "admin");
	}
	
	@AfterScenario
	public void afterScenario() throws Exception {
		SetupFramework.tearDown();
	}
	
	@Given("a sessionId $sessionId and roomId $roomId")
	public void validSessionRoomId(int sessionId, int roomId) {
		this.sessionId=sessionId;
		this.roomId=roomId;
	}
	
	@When("the assignRoom method is executed")
	public void assignRoom(){
		result=service.assignRoom(adminToken, sessionId, roomId);
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
