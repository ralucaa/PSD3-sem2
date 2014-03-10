package uk.ac.gla.psdteamk.sessions.test.steps;


import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import uk.ac.gla.psdteamk.objects.Session;
import uk.ac.gla.psdteamk.sessions.service.SessionManagerService;
import uk.ac.gla.psdteamk.sessions.test.SetupFramework;
import static org.junit.Assert.assertEquals;


public class NFR_Performance0Steps {
    String courseName;
    boolean output = true;
    private SessionManagerService sms;
    
    @Given("a course name $courseName")
    public void aStudentUsername(String courseName) {   
        sms= SetupFramework.getSessionManagerService();         
        this.courseName = courseName;
    }
    
    @When("I try to add it a $numOfAdded times to the database")
    public void queryDatabase(int numOfAdded) {
        for (int i = 0; i < numOfAdded; i++) {
            //output = (output && dbs.addCourseToDatabase(new Course((i*i + 1337), courseName + i)));
            output= (output && sms.addSessionToCourse(i, new Session(1,1,1,courseName)));
            //output =(output && sms.addSessionToCourse(i, new Session(1,1,1,1,"")));
        }
    }
    
    @Then("the output is true")
    public void theOutputIs(boolean output) {
        assertEquals(this.output, output);
    }    
}
