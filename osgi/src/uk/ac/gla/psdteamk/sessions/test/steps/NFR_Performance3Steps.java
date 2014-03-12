package uk.ac.gla.psdteamk.sessions.test.steps;

import uk.ac.gla.psdteamk.objects.TimetableSlot;
import uk.ac.gla.psdteamk.sessions.service.SessionManagerService;
import uk.ac.gla.psdteamk.sessions.test.SetupFramework;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;
import org.joda.time.DateTime;

import static org.junit.Assert.assertEquals;

public class NFR_Performance3Steps extends Steps {
	private int addedSessions = 0;
	private SessionManagerService service;
	private int adminToken;
	private int id, numberOfSlots;
	private static final int D_ID = 2500, D_CAPACITY = 100, D_ROOM = 1;
	private static final DateTime D_DATE = DateTime.now(), D_START_TIME = DateTime.now(), D_END_TIME = DateTime.now().plusHours(2);
	
	
	@Given("an admin tries to add more than $number timetable slots to the session $id")
	public void assignSession(int number, int id) throws Exception {
		//SetupFramework.defaultPopulate();
		service = SetupFramework.getSessionManagerService();
		adminToken = service.authenticate("1111111A", "1111111A");
		this.id = id;
		this.numberOfSlots = number;
	}
	
	@When("a database operation is performed")
	public void addsSessions() {
		for(int i=0;i<numberOfSlots;i++){
			TimetableSlot timetableSlot = new TimetableSlot(D_ID, id, D_DATE, D_START_TIME, D_END_TIME, D_ROOM, D_CAPACITY);
			service.createTimetableSlot(this.adminToken, timetableSlot);
			this.addedSessions++;
		}
		
	}
	
	@Then("it should do more than $expectedNumberOfSlots insertions correctly")
	public void shouldBeGreaterThanTwenty(int expectedNumberOfSlots) {
		assertEquals(true, (this.addedSessions > expectedNumberOfSlots));
	}
}
