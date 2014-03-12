package uk.ac.gla.psdteamk.sessions.test.steps;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import uk.ac.gla.psdteamk.sessions.service.SessionManagerService;
import uk.ac.gla.psdteamk.sessions.test.SetupFramework;
import static org.junit.Assert.*;

public class CheckCompulsoryCoursesSteps {	
	private SessionManagerService service;
	boolean output;
	private int studentToken;
	
	@Given("a student with username $username and password $password")
	public void aStudentUsername(String username, String password) throws Exception {
		//SetupFramework.defaultPopulate();
		service = SetupFramework.getSessionManagerService();
		studentToken = service.authenticate(username, password);
	}
	
	@When("the student checks if he is fully registered for the compulsory courses")
	public void isFalseStudentRegistered() {
		output = service.checkIfFullyRegistered(studentToken);
	}
	
	@Then("the output of the check is $output")
	public void thenCheck(String output) {
		boolean boolOutput = Boolean.parseBoolean(output);
		assertEquals(boolOutput, this.output);
	}	
}
