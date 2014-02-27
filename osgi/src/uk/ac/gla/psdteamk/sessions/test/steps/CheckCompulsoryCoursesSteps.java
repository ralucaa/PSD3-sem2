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
	
	@BeforeScenario
	public void beforeScenario() throws Exception {
		SetupFramework.setUp();
		service = SetupFramework.getSessionManagerService();
	}
	
	@AfterScenario
	public void afterScenario() throws Exception {
		SetupFramework.tearDown();
	}
	
	@Given("a student username $username")
	public void aStudentUsername(String username) {
		student = new Account(username, "", "", "");
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
