package uk.ac.gla.psdteamk.sessions.test.steps;

import static org.junit.Assert.assertEquals;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import uk.ac.gla.psdteamk.objects.Course;
import uk.ac.gla.psdteamk.sessions.service.SessionManagerService;
import uk.ac.gla.psdteamk.sessions.test.SetupFramework;

import org.jbehave.core.steps.Steps;

public class ImportMyCampusCourseSteps extends Steps {
	private boolean importCourses;
	private SessionManagerService service;
	private Course myCourse;
	private int lecturerToken;
	
	@Given("a valid MyCampus course $co")
	public void givenAMyCampusID(String co)  throws Exception {
		SetupFramework.defaultPopulate();
		service = SetupFramework.getSessionManagerService();
		lecturerToken = service.authenticate("2222222A", "2222222A");
		this.myCourse = new Course(0, co);
	}
	@Given("a false MyCampus course $co")
	public void givenAFalseMyCampusID(String co) throws Exception {
		SetupFramework.defaultPopulate();
		service = SetupFramework.getSessionManagerService();
		lecturerToken = service.authenticate("2222222A", "2222222A");
		this.myCourse = new Course(0, co);
	}

	@When("I try to retrieve the course information ")
	public void whenIRetrieveCourseInfo() {
		importCourses = service.importCourse(lecturerToken, myCourse);
	}

	@Then("a function should accept this course and the function should return the sessions for this course")
	public void thenTheOutcomeShould() {
		assertEquals(true, importCourses);
	}
	@Then("a function should accept this course and the function should return false")
	public void thenTheOutcomeShouldBeFalse() {
		assertEquals(false, importCourses);
	}
	
}
