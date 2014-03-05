package uk.ac.gla.psdteamk.mycampus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class MyCampusDatabaseAdapter {
	private static final String DB_DRIVER = "org.sqlite.JDBC";
	private static final String DB_CONNECTION = "jdbc:sqlite:res/stub_mycampus/MyCampusDB.s3db";

	/**
	 * Attempts to create a connection and returns it.
	 * Remember to close the connection after using it!!!
	 * @return the connection or null if something failed
	 */
	public static Connection getConnection(){
		//Attempt to load the driver.
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}

		//Attempt to create the connection and return it.
		try {
			return DriverManager.getConnection(DB_CONNECTION);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
 
		//Will return null if anything failed.
		return null;
	}
}
