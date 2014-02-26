package uk.ac.gla.psdteamk.sessions.test.steps;

mport static org.junit.Assert.assertEquals;

import org.jbehave.core.annotations.Given;  
import org.jbehave.core.annotations.Named;  
import org.jbehave.core.annotations.Then;  
import org.jbehave.core.annotations.When;  

import uk.ac.gla.psdteamk.database.service.DatabaseAdapterService;
import uk.ac.gla.psdteamk.objects.Course;
import uk.ac.gla.psdteamk.sessions.*;


public class ImportMyCampusCourse {
	private boolean importCourses;
	private DatabaseAdapterService da;
	private Course myCourse;
		
	  @Given("a valid MyCampus course ID$")  
	  public void givenAMyCampusID(Course course) {  
	    this.myCourse = course;
	  }  
	
	  @When("I try to retrieve the course information ")  
	  public void whenIRetrieveCourseInfo() {  
	     importCourses = ImportMyCampusCourses.importCourse(da, myCourse);
	  }  
	
	  @Then("a function should accept this course ID AND the function should return the sessions for this course")  
	  public void thenTheOutcomeShould(@Named("sessions")String sessions) {  
		  assertEquals(true, importCourses);
	  }  
}

