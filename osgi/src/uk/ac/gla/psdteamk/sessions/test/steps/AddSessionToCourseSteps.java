package uk.ac.gla.psdteamk.sessions.test.steps;

import uk.ac.gla.psdteamk.objects.Session;
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

public class AddSessionToCourseSteps extends Steps {
	// Default valid values. To be used when creating sessions for which only specific parameters need to be tested.
	private static final String D_ROOM = "Boyd Orr 720", D_TYPE = "lecture";
	private static final int D_FREQ = 7, D_CAPACITY = 100;
	private static final DateTime D_DATE = DateTime.now(), D_START_TIME = DateTime.now(), D_END_TIME = DateTime.now().plusHours(2);
	
	private SessionManagerService service;
	private Session session;
	private boolean result;
	
	@BeforeScenario
	public void beforeScenario() throws Exception {
		SetupFramework.setUp();
		service = SetupFramework.getSessionManagerService();
	}
	
	@AfterScenario
	public void afterScenario() throws Exception {
		SetupFramework.tearDown();
	}
	
	@Given("a session $session for course $course")
	public void givenASessionForAValidCourse(int session, int course) {
		this.session = new Session(session, course, D_DATE, D_START_TIME, D_END_TIME, D_FREQ, D_ROOM, D_CAPACITY, D_TYPE);
	}
	
	@Given("a session $session for course $course with a capacity $capacity")
	public void givenASessionForAValidCourse(int session, int course, int capacity) {
		this.session = new Session(session, course, D_DATE, D_START_TIME, D_END_TIME, D_FREQ, D_ROOM, capacity, D_TYPE);
	}
	
	@When("a user tries to add it to the database")
	public void userTriesToAddToTheDatabase() {
		result = service.addSessionToCourse(session);
	}
	
	@Then("it should be added to the database and the function should return true")
	public void shouldBeAddedToTheDatabaseAndReturnTrue() {
		assertEquals(true, result);
	}
	
	@Then("it should not be added to the database and the function should return false")
	public void shouldNotBeAddedToTheDatabaseAndReturnFalse() {
		assertEquals(false, result);
	}
}
