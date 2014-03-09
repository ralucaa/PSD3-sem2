package uk.ac.gla.psdteamk.sessions.test.steps;


import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import uk.ac.gla.psdteamk.database.service.DatabaseAdapterService;
import uk.ac.gla.psdteamk.objects.Session;
import uk.ac.gla.psdteamk.sessions.test.SetupFramework;
import static org.junit.Assert.assertEquals;


public class NFR_Performance1Steps {
	private DatabaseAdapterService dbs;
	int courseId;
	boolean output = true;
	
	@BeforeScenario
	public void beforeScenario() throws Exception {
		dbs = SetupFramework.getDatabaseAdapterService();
	}
	
	@AfterScenario
	public void afterScenario() throws Exception {
	}
	
	@Given("a course id $courseName")
	public void aStudentUsername(int courseId) {			
		this.courseId = courseId;
	}
	
	@When("I try to add 10 sessions to this course")
	public void queryDatabase() {
		for (int i = 0; i < 10; i++) {
			output = (output && dbs.addSessionToDatabase(new Session((i*i + 1337), courseId, 1, 7, "testType")));
		}		 
	}
	
	@Then("the database adding indicator has to be $output")
	public void theOutputIs(boolean output) {
		assertEquals(this.output, output);
	}	
}



