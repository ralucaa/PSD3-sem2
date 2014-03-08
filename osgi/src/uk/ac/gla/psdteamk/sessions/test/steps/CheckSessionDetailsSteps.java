package uk.ac.gla.psdteamk.sessions.test.steps;

import static org.junit.Assert.assertEquals;

import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;  
import org.jbehave.core.annotations.When;  

import uk.ac.gla.psdteamk.sessions.service.SessionManagerService;
import uk.ac.gla.psdteamk.sessions.test.SetupFramework;

import org.jbehave.core.steps.Steps;

public class CheckSessionDetailsSteps extends Steps{
	private static final String D_TYPE = "lecture";
	private static final int D_FREQ = 7, D_COMPULSORY = 1;
	
	private boolean detailsChecked;
	private SessionManagerService service;
	private int lecturerToken;
	private int id;

	@BeforeScenario
	public void beforeScenario() throws Exception {
		SetupFramework.defaultPopulate();
		service = SetupFramework.getSessionManagerService();
		lecturerToken = service.authenticate("2222222A", "2222222A");
		
	}
	
	  @Given("some nice session $id")  
	  public void givenASession(int id) {  
		  this.id = id;
	  }  
	
	  @When("I want to check the session details")  
	  public void whenIcheck() {  
	      //check details
		  detailsChecked = service.checkSessionDetails(this.lecturerToken, id);
	  }  
	
	  @Then("the function should fetch the session information from the database and the function should return time, location, students, tutors for that session")  
	  public void thenTheOutcomeShould() {  
		  assertEquals(true, detailsChecked);
	  }
	  @Then("the function should get no records from the database and the function should return an error")  
	  public void thenFail() {  
		  assertEquals(false, detailsChecked);
	  } 
}
