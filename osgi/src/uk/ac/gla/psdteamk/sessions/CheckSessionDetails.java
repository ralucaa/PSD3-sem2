package uk.ac.gla.psdteamk.sessions;

import uk.ac.gla.psdteamk.database.service.DatabaseAdapterService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

class CheckSessionDetails {

	static boolean checkSessionDetails(DatabaseAdapterService da, int sessionID){

		
		

		String registeredCoursesQuery = "SELECT * "+
				"FROM \"Session\" LEFT OUTER JOIN \"Tutoring\" ON  \"Session\".\"id\" = \"Tutoring\".\"session\" "+
				"WHERE \"Session\".\"id\" = ?";

		Connection con = null;
		PreparedStatement statement = null;

		ResultSet resultSet = null;

		try {
			//Get the database connection.
			con = da.getConnection();
			//Prepare the SQL statement.
			statement = con.prepareStatement(registeredCoursesQuery);
			//Add the parameters.
			statement.setInt(1, sessionID);
			//Execute the statement and get the result.
			resultSet = statement.executeQuery();
			//crunch
			System.out.println("Session info:");
			ArrayList<String> tutors = new ArrayList<String>();
			boolean toggle = false;
			boolean isLab = false;
			while(resultSet.next()){
				//got a lecture
					isLab = true;
					toggle = true;
			}
			if (toggle == false) return false;

			//session info displayed. if it was a lab session then tutor matric numbers added into the tutor ArrayList
			//If it was a lab session, print the tutors
			if(isLab){
				System.out.println("\nTutors in this session:");
				String tutorQuery = "SELECT \"student\" FROM \"Registration\" WHERE \"student\" = ?";
				Connection myCampusCon = da.getConnection();
				//Prepare the SQL statement.
				PreparedStatement tutorStatement = myCampusCon.prepareStatement(tutorQuery);
				ResultSet tutorResultSet = null;
				for(String matric: tutors){
					tutorStatement.setString(1, matric);
					tutorResultSet = tutorStatement.executeQuery();
					tutorResultSet.next();
					try {
						System.out.println(tutorResultSet.getString(1));
					} catch (SQLException e) {
						System.out.println("no tutors");
						break;
					}
				}
			}
			//////////////////////////////////////////////////////////////////
			//print all the students
			String studentsQuery = "SELECT \"student\" "+
					"FROM \"Session\",\"Registration\" "+
					"WHERE \"Session\".\"id\" = \"Registration\".\"slot\" AND \"Session\".\"id\" =?";
			statement = con.prepareStatement(studentsQuery);
			statement.setInt(1, sessionID);
			resultSet = statement.executeQuery();
			ArrayList<String> students = new ArrayList<String>();
			while(resultSet.next()){
				students.add(resultSet.getString(1));
			}
			System.out.println("\nStudents in this session:");
			studentsQuery = "SELECT \"student\" FROM \"Registration\" WHERE \"student\" = ?";
			Connection myCampusCon = da.getConnection();
			//Prepare the SQL statement.
			statement = myCampusCon.prepareStatement(studentsQuery);
			resultSet = null;
			for(String matric: students){
				statement.setString(1, matric);
				resultSet = statement.executeQuery();
				resultSet.next();
				try {
					System.out.println(resultSet.getString(1) +" "+matric);
				} catch (SQLException e) {
					System.out.println("no students in this session");
				}
			}


			System.out.println("\nQuery successfull!");
			return true;
		} catch (SQLException ex) {
			System.out.println("Probably student number not present in the database");
			ex.printStackTrace();
		} 
		return false;
	}
}
