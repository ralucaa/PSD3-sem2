package uk.ac.gla.psdteamk.sessions.test.steps;


import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import uk.ac.gla.psdteamk.database.service.DatabaseAdapterService;
import uk.ac.gla.psdteamk.objects.Course;
import uk.ac.gla.psdteamk.sessions.test.SetupFramework;
import static org.junit.Assert.assertEquals;


public class NFR_Performance0Steps {
	private DatabaseAdapterService dbs;
	String courseName;
	boolean output = true;
	
	@BeforeScenario
	public void beforeScenario() throws Exception {
		dbs = SetupFramework.getDatabaseAdapterService();
	}
	
	@AfterScenario
	public void afterScenario() throws Exception {
	}
	
	@Given("a course name $courseName")
	public void aStudentUsername(String courseName) {			
		this.courseName = courseName;
	}
	
	@When("I try to add it a $numOfAdded times to the database")
	public void queryDatabase(int numOfAdded) {
		for (int i = 0; i < numOfAdded; i++) {
			output = (output && dbs.addCourseToDatabase(new Course((i*i + 1337), courseName + i)));
		}
	}
	
	@Then("the output is $output")
	public void theOutputIs(boolean output) {
		assertEquals(this.output, output);
	}	
}
