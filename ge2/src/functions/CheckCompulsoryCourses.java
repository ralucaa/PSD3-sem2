package functions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database_adapter.DatabaseAdapter;
import objects.Account;

public class CheckCompulsoryCourses {

	public static void CheckIfFullyRegistered(Account student){
		String registeredCoursesQuery = "SELECT CoursesRegistered.*, Course.title "+
										"FROM Course, "+
										"(SELECT DISTINCT Session.course AS cr "+
										"FROM Registration, Session "+
										"WHERE Registration.session = Session.id  AND Registration.student = ?) AS CoursesRegistered "+
										"WHERE Course.id = CoursesRegistered.cr";
		
		String notRegisteredMandatoryCoursesQuery = "SELECT Course.id,Course.title "+
													"FROM Course, "+
													"(SELECT MandatoryCourses.course AS NotRegistered FROM MandatoryCourses "+
													"EXCEPT "+
													"SELECT DISTINCT Session.course "+
													"FROM Registration, Session "+
													"WHERE Registration.session = Session.id  AND Registration.student = ? )AS NR "+
													"WHERE Course.id = NR.NotRegistered";
		
		Connection con = null;
		PreparedStatement registered = null;
		PreparedStatement notRegistered = null;
		
		ResultSet registeredResultSet = null;
		ResultSet notRegisteredResultSet =null;

		try {
			//Get the database connection.
			con = DatabaseAdapter.getConnection();
			//Prepare the SQL statement.
			registered = con.prepareStatement(registeredCoursesQuery);
			notRegistered = con.prepareStatement(notRegisteredMandatoryCoursesQuery);
			//Add the parameters.
			registered.setString(1, student.getUsername());
			notRegistered.setString(1, student.getUsername());
			//Execute the statement and get the result.
			registeredResultSet = registered.executeQuery();
			notRegisteredResultSet = notRegistered.executeQuery();
			//crunch
			System.out.println("Courses you are registered in:");
			while(registeredResultSet.next()){
				System.out.println("ID "+registeredResultSet.getInt(1)+"  Title: "+registeredResultSet.getString(2));
			}

			System.out.println("\nCompulsory courses that you haven't enrolled into yet:");
			while(notRegisteredResultSet.next()){
				System.out.println("ID "+notRegisteredResultSet.getInt(1)+"  Title: "+notRegisteredResultSet.getString(2));
			}
			
					
			System.out.println("\nQuery successfull!");
		} catch (SQLException ex) {
			System.out.println("Probably student number not present in the database");
			ex.printStackTrace();
		} finally {
			//Close the connections.
			try {
				if (registered != null){
					registered.close();
				}
				if(notRegistered != null){
					notRegistered.close();
				}
				if (con != null){
					con.close();
				}
			}
			catch (SQLException ex){
				System.out.println(ex.getMessage());
			}
		}
	}
}