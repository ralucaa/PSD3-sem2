package uk.ac.gla.psdteamk.sessions.test.steps;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import uk.ac.gla.psdteamk.database.service.DatabaseAdapterService;
import uk.ac.gla.psdteamk.sessions.test.SetupFramework;
import static org.junit.Assert.assertEquals;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;



public class NFR_Performance2Steps extends Steps {
	private DatabaseAdapterService dbs;
	private int users;
	private boolean imported;
	
	@BeforeScenario
	public void beforeScenario() throws Exception {
		dbs = SetupFramework.getDatabaseAdapterService();
	}
	
	@AfterScenario
	public void afterScenario() throws Exception {
	}
	
	@Given("Given a number of users $u")
	public void givenADatabase(int u) {
		this.users = u;
	}
	
	@When("I try to populate the database with the users")
	public void populateUserDB() {
		String sql = "INSERT INTO Crap VALUES (?)"; 
		Connection con = null;
		PreparedStatement preparedStatement = null;

		try {
			//Get the database connection.
			con = dbs.getConnection();
			//Prepare the SQL statement.
			preparedStatement = con.prepareStatement(sql);
			//Execute the statement and get the result.
			preparedStatement.execute();
			for (int i = 0;i<=users;i++){
				preparedStatement.setDouble(1, Math.random());
				preparedStatement.execute();
			}
			System.out.println("The course has been successfully imported!");
			imported = true;
		} catch (SQLException ex) {
			System.out.println("This course is already imported!");
		} finally {
			//Close the connections.
			try {
				if (preparedStatement != null){
					preparedStatement.close();
				}
				if (con != null){
					con.close();
				}
			}
			catch (SQLException ex){
				System.out.println(ex.getMessage());
			}
		}
		imported =  false;
	}
	
	@Then("it should return true")
	public void check() {
		assertEquals(true,imported);
	}

}
