package functions;

import database_adapter.DatabaseAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.Account;
import stub_mycampus.MyCampusDatabaseAdapter;

public class CheckSessionDetails {

	public static void Check(BufferedReader reader){
		String sessionID = "";
		while(true){
			System.out.println(
					"1. Input the session ID.\n" +
					"q. Quit"
			);
			String option;
			try {
				option = reader.readLine();
				if (option.equals("q")){
				break;
			} else{
				sessionID = option;
				break;
			}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Quit if q.
			
		}
		
		String registeredCoursesQuery = "SELECT * "+
										"FROM Session LEFT OUTER JOIN Tutoring ON  Session.id = Tutoring.session "+
										"WHERE Session.id = ?";
		
		Connection con = null;
		PreparedStatement statement = null;
		
		ResultSet resultSet = null;

		try {
			//Get the database connection.
			con = DatabaseAdapter.getConnection();
			//Prepare the SQL statement.
			statement = con.prepareStatement(registeredCoursesQuery);
			//Add the parameters.
			statement.setInt(1, Integer.parseInt(sessionID));
			//Execute the statement and get the result.
			resultSet = statement.executeQuery();
			//crunch
			System.out.println("Courses you are registered in:");
			ArrayList<String> tutors = new ArrayList<String>();
			boolean toggle = true;
			boolean isLab = false;
			while(resultSet.next()){
				//got a lecture
				if(resultSet.getString(8) == null){
					System.out.println(resultSet.getString(1)+resultSet.getDate(2)+resultSet.getTime(3)+resultSet.getTime(4)+resultSet.getInt(5)+
						resultSet.getInt(6)+resultSet.getString(7));
				}
				//got a lab with tutors
				else{
					isLab = true;
					//enter here only once to display lab session info
					if(toggle == true){
						System.out.println(resultSet.getString(1)+resultSet.getDate(2)+resultSet.getTime(3)+resultSet.getTime(4)+resultSet.getInt(5)+
								resultSet.getInt(6)+resultSet.getString(7));
					}
					toggle = false;
					tutors.add(resultSet.getString(8));
				}	
			}
			//session info displayed. if it was a lab session then tutor matric numbers added into the tutor ArrayList
			//If it was a lab session, print the tutors
			if(isLab){
				System.out.println("Tutors in this session:");
				String tutorQuery = "SELECT name FROM User WHERE name =?";
				Connection myCampusCon = MyCampusDatabaseAdapter.getConnection();
				//Prepare the SQL statement.
				PreparedStatement tutorStatement = myCampusCon.prepareStatement(tutorQuery);
				ResultSet tutorResultSet = null;
				for(String matric: tutors){
					tutorStatement.setInt(1, Integer.parseInt(matric));
					tutorResultSet = statement.executeQuery();
					tutorResultSet.next();
					System.out.println(tutorResultSet.getString(1));
				}
				
				try {
					if (tutorStatement != null){
						statement.close();
					}
					if (myCampusCon != null){
						con.close();
					}
				}
				catch (SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			//////////////////////////////////////////////////////////////////
			//print all the students
			String studentQuery = "";
			
					
			System.out.println("\nQuery successfull!");
		} catch (SQLException ex) {
			System.out.println("Probably student number not present in the database");
			ex.printStackTrace();
		} finally {
			//Close the connections.
			try {
				if (statement != null){
					statement.close();
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
