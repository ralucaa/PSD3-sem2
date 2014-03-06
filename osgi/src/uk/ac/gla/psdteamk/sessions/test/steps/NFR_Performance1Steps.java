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
import uk.ac.gla.psdteamk.helpers.DateTimeOps;


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
	
	@When("the database request is made")
	public void queryDatabase() {
		for (int i = 0; i < 10; i++) {
			output = (output && dbs.addSessionToDatabase(new Session((i*i + 1337), courseId, DateTimeOps.parseDateStringToJodaTime("2014-02-27"), DateTimeOps.parseTimeStringToJodaTime("00:00"), DateTimeOps.parseTimeStringToJodaTime("00:00"), 7, "BO505", 50, "testType")));
		}		 
	}
	
	@Then("the output is $output")
	public void theOutputIs(boolean output) {
		assertEquals(this.output, output);
	}	
}



