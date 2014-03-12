package uk.ac.gla.psdteamk.sessions.test.steps;


import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;
import org.joda.time.DateTime;

import uk.ac.gla.psdteamk.objects.Session;
import uk.ac.gla.psdteamk.objects.TimetableSlot;
import uk.ac.gla.psdteamk.sessions.service.SessionManagerService;
import uk.ac.gla.psdteamk.sessions.test.SetupFramework;
import static org.junit.Assert.assertEquals;

/**
 * Narrative:
In order that rooms can be assigned to slots
As an administrator
I want to create a timetable slot for a session
*/

public class CreateTimetableSlotForSessionSteps extends Steps{
	private static final int D_ID = 2500, D_CAPACITY = 100, D_ROOM = 1;
	private static final DateTime D_DATE = DateTime.now(), D_START_TIME = DateTime.now(), D_END_TIME = DateTime.now().plusHours(2);
	
	private SessionManagerService service;
	private boolean result;
	private TimetableSlot timetableSlot;
	private int adminToken;
	
	@Given("a session $session")
	public void aSession(int session)throws Exception {
		//SetupFramework.defaultPopulate();
		service = SetupFramework.getSessionManagerService();
		adminToken = service.authenticate("1111111A", "1111111A");
		timetableSlot = new TimetableSlot(D_ID, session, D_DATE, D_START_TIME, D_END_TIME, D_ROOM, D_CAPACITY);
	}
	
	@When("the administrator tries to create a timetable slot for a session")
	public void createSlot(){
		result=service.createTimetableSlot(adminToken, timetableSlot);
	}
	
	@Then("the result of creating the timetable slot is $output")
	public void returnResultTrue(String output){
		boolean boolOutput = Boolean.getBoolean(output);
		assertEquals(boolOutput, result);
	}
}
