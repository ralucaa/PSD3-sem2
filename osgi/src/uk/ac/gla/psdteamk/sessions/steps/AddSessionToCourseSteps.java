package uk.ac.gla.psdteamk.sessions.steps;

import uk.ac.gla.psdteamk.objects.Session;
import uk.ac.gla.psdteamk.sessions.service.SessionManagerService;

import org.jbehave.core.annotations.Alias;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;

import static org.junit.Assert.assertEquals;

public class AddSessionToCourseSteps extends Steps {
	private SessionManagerService service;
	private Session session;
	private boolean result;
	
	@Given("a session $session for a valid course")
	@Alias("a session $session for a missing course")
	public void givenASessionForAValidCourse(Session session) {
		this.session = session;
	}
	
	@Given("a session $session for a course and a negative capacity $capacity")
	public void givenASessionForAValidCourse(Session session, int capacity) {
		this.session = session;
		this.session.setCapacity(capacity);
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
