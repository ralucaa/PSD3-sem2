package uk.ac.gla.psdteamk.sessions.test.steps;

import static org.junit.Assert.assertEquals;

import org.jbehave.core.annotations.Given;  
import org.jbehave.core.annotations.Named;  
import org.jbehave.core.annotations.Then;  
import org.jbehave.core.annotations.When;  

import uk.ac.gla.psdteamk.database.service.DatabaseAdapterService;
import uk.ac.gla.psdteamk.objects.Course;
import uk.ac.gla.psdteamk.sessions.*;
import org.jbehave.core.steps.Steps;

public class ImportMyCampusCourseSteps extends Steps{
	private boolean importCourses;
	private DatabaseAdapterService da;
	private Course myCourse;
		
	  @Given("a valid MyCampus course $co")  
	  public void givenAMyCampusID(String co) {  
	    this.myCourse =new Course(0, co);
	  }  
	
	  @When("I try to retrieve the course information ")  
	  public void whenIRetrieveCourseInfo() {  
	     importCourses = ImportMyCampusCourses.importCourse(da, myCourse);
	  }  
	
	  @Then("a function should accept this course ID and the function should return the sessions for this course")  
	  public void thenTheOutcomeShould() {  
		  assertEquals(true, importCourses);
	  }  
}
