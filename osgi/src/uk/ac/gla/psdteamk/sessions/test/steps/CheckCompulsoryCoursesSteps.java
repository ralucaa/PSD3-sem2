package uk.ac.gla.psdteamk.sessions.test.steps;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import uk.ac.gla.psdteamk.objects.Account;
import uk.ac.gla.psdteamk.sessions.service.SessionManagerService;
import uk.ac.gla.psdteamk.sessions.test.SetupFramework;
import static org.junit.Assert.assertEquals;

public class CheckCompulsoryCoursesSteps {	
	private SessionManagerService service;	
	private Account student;
	boolean output;
	private int studentToken;
	
	@BeforeScenario
	public void beforeScenario() throws Exception {
		SetupFramework.defaultPopulate();
		service = SetupFramework.getSessionManagerService();
		studentToken = service.authenticate("3333333B", "3333333B");
	}
	
	@AfterScenario
	public void afterScenario() throws Exception {
	}
	
	@Given("a student username $username")
	public void aStudentUsername(String username) {
		student = new Account(username, "", "", "", 1);
	}
	
	@When("the database request is made")
	public void queryDatabase() {
		output = service.checkIfFullyRegistered(student);
	}
	
	@Then("the output is $output")
	public void theOutputIs(boolean output) {
		assertEquals(this.output, output);
	}	
}
