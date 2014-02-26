package uk.ac.gla.psdteamk.sessions.test.steps;

import uk.ac.gla.psdteamk.database.service.DatabaseAdapterService;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;



public class NFR_performance2 extends Steps {
	private DatabaseAdapterService service;

	
	@Given("a database $d")
	public void givenADatabase() {
		//no functionality
	}
	
	@When("I try to populate the database with the users")
	public void populateUserDB() {
		//no functionality
	}
	
	@Then("I should be able to add 1000 and more of them")
	public void check() {
		//no functionality
	}

}
