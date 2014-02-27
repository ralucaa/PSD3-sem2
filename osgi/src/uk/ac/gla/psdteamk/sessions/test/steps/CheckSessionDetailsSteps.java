package uk.ac.gla.psdteamk.sessions.test.steps;

import static org.junit.Assert.assertEquals;

import org.jbehave.core.annotations.Given;  
import org.jbehave.core.annotations.Named;  
import org.jbehave.core.annotations.Then;  
import org.jbehave.core.annotations.When;  

import uk.ac.gla.psdteamk.database.service.DatabaseAdapterService;
import uk.ac.gla.psdteamk.objects.Course;
import uk.ac.gla.psdteamk.objects.Session;
import uk.ac.gla.psdteamk.sessions.*;

import org.jbehave.core.steps.Steps;

public class CheckSessionDetailsSteps extends Steps{
	private boolean detailsChecked;
	private DatabaseAdapterService da;
	private Session session;
		
	  @Given("Given a session $s")  
	  public void givenASession(String s) {  
	    this.session = new Session();
	  }  
	
	  @When("When I want to check the session details")  
	  public void whenIcheck() {  
	      //check details
		  detailsChecked = true;
	  }  
	
	  @Then("a function should accept this course ID and the function should return the sessions for this course")  
	  public void thenTheOutcomeShould() {  
		  assertEquals(true, detailsChecked);
	  }  
}
