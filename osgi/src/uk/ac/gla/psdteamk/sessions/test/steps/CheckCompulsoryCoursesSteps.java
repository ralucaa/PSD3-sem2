package uk.ac.gla.psdteamk.sessions.test.steps;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import uk.ac.gla.psdteamk.objects.Account;
import uk.ac.gla.psdteamk.sessions.service.SessionManagerService;
import uk.ac.gla.psdteamk.sessions.test.SetupFramework;
import static org.junit.Assert.*;

public class CheckCompulsoryCoursesSteps {	
	private SessionManagerService service;
	boolean output;
	private int studentToken;
	private Account student;
	
	@BeforeScenario
	public void beforeScenario() throws Exception {
		SetupFramework.defaultPopulate();
		service = SetupFramework.getSessionManagerService();
		//studentToken = service.authenticate("3333333B", "3333333B");
	}
	
	@AfterScenario
	public void afterScenario() throws Exception {
	}
	
	@Given("a student username $username")
	public void aStudentUsername(String username) {
		studentToken = service.authenticate("33bogus33B", "bogus333B");
		student = new Account(username, "", "", "", 1);
	}
	
	@When("I check the student in database")
	public void isFalseStudentRegistered() {
		output = service.checkIfFullyRegistered(studentToken);
	}
	
	@Then("the output of the check is $output")
	public void thenCheck(boolean output) {
		if(output == false){
			assertEquals(this.output, false);
		}else{
			assertEquals(this.output, true);
		}
		
	}	
}
