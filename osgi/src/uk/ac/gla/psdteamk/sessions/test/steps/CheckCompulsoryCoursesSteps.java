package uk.ac.gla.psdteamk.sessions.test.steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import uk.ac.gla.psdteamk.objects.Account;
import uk.ac.gla.psdteamk.sessions.service.SessionManagerService;

public class CheckCompulsoryCoursesSteps {	
	private SessionManagerService service;	
	private Account student;
	boolean output;
	
	@Given("a student username $username")
	public void aStudentUsername(String username) {
		student = new Account(username, "", "", "");
	}
	
	@When("the database request is made")
	public void theUsernameIsNotInTheDatabase() {
		output = service.checkIfFullyRegistered(student);
	}
	
	@Then("the output is $output")
	public void theOutputIs(boolean output) {
		//assertThat(this.output, output);
	}	
}
