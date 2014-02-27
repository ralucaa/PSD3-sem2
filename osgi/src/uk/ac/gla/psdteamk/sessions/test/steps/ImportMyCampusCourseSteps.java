package uk.ac.gla.psdteamk.sessions.test.steps;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import uk.ac.gla.psdteamk.database.service.DatabaseAdapterService;
import static org.junit.Assert.assertEquals;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;



public class NFR_performance2 extends Steps {
	private DatabaseAdapterService service;
	private int users;
	private boolean imported;
	
	@Given("Given a number of users $u")
	public void givenADatabase(int users) {
		this.users = users;
	}
	
	@When("I try to populate the database with the users")
	public void populateUserDB() {
		String sql = "INSERT INTO Crap VALUES (?)"; 
		Connection con = null;
		PreparedStatement preparedStatement = null;

		try {
			//Get the database connection.
			con = service.getConnection();
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
	
	@Then("I should be able to add 1000 and more of them")
	public void check() {
		assertEquals(true,imported);
	}

}
