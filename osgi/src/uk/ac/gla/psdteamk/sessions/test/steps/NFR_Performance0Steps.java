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
    boolean result = true;
    private int lecturerToken;
    private SessionManagerService sms;
    
    @Given("a course name $courseName")
    public void givenADatabase(String courseName) {   
        sms= SetupFramework.getSessionManagerService();  
        lecturerToken = sms.authenticate("2222222A", "2222222A");
        this.courseName = courseName;
    }
    
    @When("I try to add it a $numOfAdded times to the database")
    public void queryDatabase(int numOfAdded) {
        for (int i = 0; i < numOfAdded; i++) {
            result = (result && sms.addSessionToCourse(lecturerToken, new Session(1,1,1,courseName)));
        }
    }
    
    @Then("the result of performance test 0 should be $output")
    public void theOutputIs(String output) {
    	boolean boolOutput = Boolean.parseBoolean(output);
        assertEquals(boolOutput, result);
    }    
}
