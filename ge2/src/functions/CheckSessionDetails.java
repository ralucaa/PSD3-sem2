package functions;

import database_adapter.DatabaseAdapter;

import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import stub_mycampus.MyCampusDatabaseAdapter;

public class CheckSessionDetails {

	public static void checkSessionDetails(BufferedReader reader, String sessionID){

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
			System.out.println("Session info:");
			ArrayList<String> tutors = new ArrayList<String>();
			boolean toggle = true;
			boolean isLab = false;
			while(resultSet.next()){
				//got a lecture
				if(resultSet.getString(8) == null){
					System.out.println("First session on "+ resultSet.getString(3)+
							"\nfrom "+resultSet.getString(4)+" to "+resultSet.getString(5)+"\nfrequency "+resultSet.getString(6)+
							"\nroom "+resultSet.getInt(7)+ "\ncapacity "+resultSet.getInt(8)+"\ntype: "+resultSet.getString(9));
				}
				//got a lab with tutors
				else{
					isLab = true;
					//enter here only once to display lab session info
					if(toggle == true){
						System.out.println("First session on "+resultSet.getString(3)+
								"\nfrom "+resultSet.getString(4)+" to "+resultSet.getString(5)+"\nfrequency "+resultSet.getString(6)+
								"\nroom "+resultSet.getInt(7)+"\ncapacity "+resultSet.getInt(8)+"\ntype: "+resultSet.getString(9));
					}
					toggle = false;
					tutors.add(resultSet.getString(10));
				}	
			}
			statement.close();
			//session info displayed. if it was a lab session then tutor matric numbers added into the tutor ArrayList
			//If it was a lab session, print the tutors
			if(isLab){
				System.out.println("\nTutors in this session:");
				String tutorQuery = "SELECT name FROM User WHERE guid = ?";
				Connection myCampusCon = MyCampusDatabaseAdapter.getConnection();
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

				try {
					if (tutorStatement != null){
						tutorStatement.close();
					}
					if (myCampusCon != null){
						myCampusCon.close();
					}
				}
				catch (SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			//////////////////////////////////////////////////////////////////
			//print all the students
			String studentsQuery = "SELECT student "+
					"FROM Session,Registration "+
					"WHERE Session.id = Registration.session AND Session.id =?";
			statement = con.prepareStatement(studentsQuery);
			statement.setInt(1, Integer.parseInt(sessionID));
			resultSet = statement.executeQuery();
			ArrayList<String> students = new ArrayList<String>();
			while(resultSet.next()){
				students.add(resultSet.getString(1));
			}
			statement.close();
			System.out.println("\nStudents in this session:");
			studentsQuery = "SELECT name FROM User WHERE guid = ?";
			Connection myCampusCon = MyCampusDatabaseAdapter.getConnection();
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
			myCampusCon.close();


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