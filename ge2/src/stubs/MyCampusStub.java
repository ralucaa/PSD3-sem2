package stubs;

import helpers.DatabaseAdapter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import objects.Account;

public class MyCampusStub {
	/**
	 * Authenticates the user through MyCampus' single sign-on service.
	 * THIS IS A STUB! Will validate if username and password match and the user exists in the database.
	 * @param username - The username.
	 * @param password - The password.
	 * @return an Account object that contains the user's details or null if invalid credentials
	 */
	public static Account authenticate(String username, String password){
		//Require username and password to match.
		if (!username.equals(password)){
			return null;
		}

		//Attempt to get the user's type.
		String sql = "SELECT name, type FROM User WHERE guid = ?";
		Connection con = null;
		PreparedStatement preparedStatement = null;

		try {
			//Get the database connection.
			con = DatabaseAdapter.getConnection();
			//Prepare the SQL statement.
			preparedStatement = con.prepareStatement(sql);
			//Add the parameters.
			preparedStatement.setString(1, username);
			//Execute the statement and get the result.
			ResultSet rs = preparedStatement.executeQuery();
			//Advance to first element.
			rs.next();
			//Return the value.
			return new Account(username, password, rs.getString(1), rs.getString(2));
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
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
		
		//Will only reach this if coming from the catch block.
		return null;
	}
}
