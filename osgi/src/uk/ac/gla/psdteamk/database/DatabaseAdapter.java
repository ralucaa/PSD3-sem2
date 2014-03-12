package uk.ac.gla.psdteamk.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import uk.ac.gla.psdteamk.objects.*;
import uk.ac.gla.psdteamk.database.service.DatabaseAdapterService;

public class DatabaseAdapter implements DatabaseAdapterService {

	private static final String DB_CONNECTION = "jdbc:derby:derby/sessions;create=true";

	private boolean doneInit = false;

	/**
	 * Attempts to create a connection and returns it. Remember to close the
	 * connection after using it!!!
	 * 
	 * @return the connection or null if something failed
	 */
	public synchronized Connection getConnection() {
		// Attempt to create the connection and return it.
		try {
			Connection conn = DriverManager.getConnection(DB_CONNECTION);
			//System.out.println("getConnection");
			if  (!doneInit) {
				System.out.println("Could not initialise tables! Retrying.");
				doneInit = createTables(conn);
			}
			return conn;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		// Will return null if anything failed.
		return null;
	}

	//derby doesn't support CREATE TABLE IF NOT EXISTS:
	//doing some bad things to get around it
	private synchronized boolean createTables(Connection conn) {
		Statement stmt;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			System.out.println("createTables can't create statement: " + e.getMessage());
			return false;
		}
		try {
			stmt.executeUpdate(
					"CREATE TABLE \"Registration\" ("
							+ "\"slot\" INTEGER NOT NULL, "
							+ "\"student\" VARCHAR(8) NOT NULL, "
							+ "PRIMARY KEY (\"slot\", \"student\"))"
					);
		} catch (SQLException e) {
			System.out.println("Table not created: " + e.getMessage());
			return false;
		}
		try {
			stmt.executeUpdate(
					"CREATE TABLE \"MandatoryCourses\" ("
							+ "\"year\" INTEGER,"
							+ "\"course\" INTEGER,"
							+ "PRIMARY KEY (\"year\", \"course\"))"
					);
		} catch (SQLException e) {
			System.out.println("Table not created: " + e.getMessage());
			return false;
		}
		try {
			stmt.executeUpdate(
					"CREATE TABLE \"Course\" ("
							+ "\"id\" INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
							+ "\"title\" VARCHAR(128) NOT NULL)"
					);
		} catch (SQLException e) {
			System.out.println("Table not created: " + e.getMessage());
			return false;
		}
		try {
			stmt.executeUpdate(
					"CREATE TABLE \"Tutoring\" ("
							+ "\"tutor\" VARCHAR(8) NOT NULL,"
							+ "\"session\" INTEGER NOT NULL,"
							+ "PRIMARY KEY (\"tutor\",\"session\"))"
					);
		} catch (SQLException e) {
			System.out.println("Table not created: " + e.getMessage());
			return false;
		}
		try {
			stmt.executeUpdate(
					"CREATE TABLE \"Session\" ("
							+ "\"id\" INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
							+ "\"course\" INTEGER NOT NULL,"
							+ "\"compulsory\" INTEGER DEFAULT 0 NOT NULL,"
							+ "\"frequency\" INTEGER DEFAULT 0 NOT NULL,"
							+ "\"type\" VARCHAR(16)  NOT NULL)"
					);
		} catch (SQLException e) {
			System.out.println("Table not created: " + e.getMessage());
			return false;
		}
		try {
			stmt.executeUpdate(
					"CREATE TABLE \"TimetableSlot\" ("
							+ "\"id\" INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
							+ "\"session\" INTEGER NOT NULL,"
							+ "\"date\" VARCHAR(10),"
							+ "\"start_time\" VARCHAR(8),"
							+ "\"end_time\" VARCHAR(8),"
							+ "\"room\" INTEGER,"
							+ "\"capacity\" INTEGER DEFAULT 0 NOT NULL)"
					);
		} catch (SQLException e) {
			System.out.println("Table not created: " + e.getMessage());
			return false;
		}
		return true;
	}

	public synchronized boolean resetTables() {
		/*
		Connection conn = getConnection();
		if (conn == null) {
			System.out.println(">>> Connection is null in deleteEverything");
		}
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("DROP TABLE \"Registration\"");
			stmt.executeUpdate("DROP TABLE \"MandatoryCourses\"");
			stmt.executeUpdate("DROP TABLE \"Course\"");
			stmt.executeUpdate("DROP TABLE \"Tutoring\"");
			stmt.executeUpdate("DROP TABLE \"Session\"");
			stmt.executeUpdate("DROP TABLE \"TimetableSlot\"");
			stmt.close();
		} catch (SQLException e) {
			System.out.println(">>> deleteEverything : " + e.getMessage());
			return false;
		}
		if (!createTables(conn)) {
			return false;
		}
		*/
		return true;
	}

	/**
	 * Gets all the courses available in the database.
	 * 
	 * @return the courses
	 */
	public synchronized ArrayList<Course> getAllCourses() {
		// Attempt to get the user's type.
		String sql = "SELECT \"id\", \"title\" FROM \"Course\"";
		Connection con = null;
		PreparedStatement preparedStatement = null;

		try {
			// Get the database connection.
			con = getConnection();
			// Prepare the SQL statement.
			preparedStatement = con.prepareStatement(sql);
			// Execute the statement and get the result.
			ResultSet rs = preparedStatement.executeQuery();
			// Read the courses.
			ArrayList<Course> courses = new ArrayList<Course>();
			while (rs.next()) {
				courses.add(new Course(rs.getInt(1), rs.getString(2)));
			}
			// Return the courses.
			return courses;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
			// Close the connections.
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
		return null;
	}

	/**
	 * Retrieve all sessions from the database
	 * 
	 * @return an ArrayList of Session objects
	 */
	public synchronized ArrayList<Session> getAllSessions() {

		ArrayList<Session> r = new ArrayList<Session>();

		// Retrieve room details from the database
		String query = "SELECT * FROM \"Session\"";
		Connection con = null;
		PreparedStatement preparedStatement = null;

		try {
			// Get the database connection
			con = getConnection();
			// Prepare the SQL statement
			preparedStatement = con.prepareStatement(query);
			// Execute the statement and get the result
			ResultSet rs = preparedStatement.executeQuery();

			// Iterate through the result set
			while (rs.next()) {
				r.add(new Session(rs.getInt(1), // id
						rs.getInt(2), // course
						rs.getInt(3), // compulsory
						rs.getInt(4), // frequency
						rs.getString(5) // type
						));
			}

			return r;

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
			// Close the connections.
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}

		// Will only reach this if coming from the catch block.
		return null;
	}

	/**
	 * Get all the information about a course that corresponds to given id
	 * 
	 * @param id
	 *            - course reference in the database
	 * @return Course object
	 */
	public synchronized Course getCourse(int id) {

		// Retrieve room details from the database
		String query = "SELECT \"title\" FROM \"Course\" WHERE \"id\" = ?";
		Connection con = null;
		PreparedStatement preparedStatement = null;

		try {
			// Get the database connection.
			con = getConnection();
			// Prepare the SQL statement.
			preparedStatement = con.prepareStatement(query);
			// Add the parameters.
			preparedStatement.setInt(1, id);
			// Execute the statement and get the result.
			ResultSet rs = preparedStatement.executeQuery();
			// Advance to first element.
			rs.next();
			// Return the value.
			return new Course(id, rs.getString(1));
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
			// Close the connections.
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}

		// Will only reach this if coming from the catch block.
		return null;
	}

	/**
	 * Retrieve all the students from the database
	 * 
	 * @return an ArrayList of Account objects for the students
	 */
	public synchronized ArrayList<String> getAllStudents() {

		ArrayList<String> students = new ArrayList<String>();

		// Retrieve student details from the database
		String query = "SELECT \"student\" FROM \"Registration\"";
		Connection con = null;
		PreparedStatement preparedStatement = null;

		try {
			// Get the database connection
			con = getConnection();
			// Prepare the SQL statement
			preparedStatement = con.prepareStatement(query);
			// Execute the statement and get the result
			ResultSet rs = preparedStatement.executeQuery();

			// Iterate through the result set
			while (rs.next()) {
				students.add(rs.getString(1) // get the student field
						);
			}

			return students;

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
			// Close the connections.
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}

		// Will only reach this if coming from the catch block.
		return null;

	}

	public synchronized boolean addCourseToDatabase(Course course) {
		if (course == null || course.getTitle() == null) {
			System.out.println("You tried to add an null course.");
			return false;
		}
		
		// Add to database.
		String sql = "INSERT INTO \"Course\" (\"title\") VALUES (?)";
		Connection con = null;
		PreparedStatement preparedStatement = null;

		try {
			// Get the database connection.
			con = getConnection();
			// Prepare the SQL statement.
			preparedStatement = con.prepareStatement(sql);
			// Add the parameters.
			preparedStatement.setString(1, course.getTitle());
			// Execute the statement and get the result.
			preparedStatement.execute();

			return true;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
			// Close the connections.
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
		return false;
	}

	public synchronized boolean addSessionToDatabase(Session session) {
		// Ensure the provided session object is valid.
		if (getCourse(session.getCourse()) == null || session.getFrequency() < 0 || (session.getCompulsory() != 0 && session.getCompulsory() != 1)) {
			return false;
		}
		
		// Add to database.
		String sql = "INSERT INTO \"Session\"(\"course\", \"compulsory\", \"frequency\", \"type\") VALUES (?, ?, ?, ?)";
		Connection con = null;
		PreparedStatement preparedStatement = null;

		try {
			// Get the database connection.
			con = getConnection();
			// Prepare the SQL statement.
			preparedStatement = con.prepareStatement(sql);
			// Add the parameters.
			preparedStatement.setInt(1, session.getCourse());
			preparedStatement.setInt(2, session.getCompulsory());
			preparedStatement.setInt(3, session.getFrequency());
			preparedStatement.setString(4, session.getType());
			// Execute the statement and get the result.
			preparedStatement.execute();

			return true;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
			// Close the connections.
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
		return false;
	}

	public synchronized boolean addUserToDatabase(Account account) {
		if (account == null) {
			return false;
		}
		// Add to database.
		String sql = "INSERT INTO \"User\"(\"guid\", \"type\", \"name\", \"year\") VALUES (?, ?, ?, ?)";
		Connection con = null;
		PreparedStatement preparedStatement = null;

		try {
			// Get the database connection.
			con = getConnection();
			// Prepare the SQL statement.
			preparedStatement = con.prepareStatement(sql);
			// Add the parameters.
			preparedStatement.setString(1, account.getUsername());
			preparedStatement.setString(2, account.getType());
			preparedStatement.setString(3, account.getName());
			preparedStatement.setInt(4, 3);
			// Execute the statement and get the result.
			preparedStatement.execute();

			return true;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
			// Close the connections.
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
		return false;
	}

	/**
	 * Retrieve a session from the database based on the session id.
	 * @param session_id - The session id.
	 * @return a Session object matching the requested session or null if could not retrieve
	 */
	public synchronized Session getSession(int session_id) {
		// Retrieve room details from the database
		String query = "SELECT * FROM \"Session\" WHERE \"id\" = ?";
		Connection con = null;
		Session session = null;
		PreparedStatement preparedStatement = null;

		try {
			// Get the database connection
			con = getConnection();
			// Prepare the SQL statement
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, session_id);
			// Execute the statement and get the result
			ResultSet rs = preparedStatement.executeQuery();

			// Iterate through the result set
			if (rs.next()) {
				session = new Session(rs.getInt(1), // id
						rs.getInt(2), // course
						rs.getInt(3), // compulsory
						rs.getInt(4), // frequency
						rs.getString(5) // type
						);
			}

			// Return the session. Will be null if could not read.
			return session;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
			// Close the connections.
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}

		// Will only reach this if coming from the catch block.
		return null;
	}

	/**
	 * Retrieve a slot from the database based on the slot id.
	 * @param slotId - The slot id
	 * @return a TimetableSlot object matching the requested slot or null if could not retrieve
	 */

	public synchronized TimetableSlot getTimetableSlot(int slotId) {
		// Retrieve room details from the database
		String query = "SELECT * FROM \"TimetableSlot\" WHERE \"id\" = ?";
		Connection con = null;
		TimetableSlot slot = null;
		PreparedStatement preparedStatement = null;

		try {
			// Get the database connection
			con = getConnection();
			// Prepare the SQL statement
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, slotId);
			// Execute the statement and get the result
			ResultSet rs = preparedStatement.executeQuery();

			// Iterate through the result set
			if (rs.next()) {

				slot = new TimetableSlot(
						rs.getInt(1), // id
						rs.getInt(2), // slotId
						rs.getString(3), // date
						rs.getString(4), // start_time
						rs.getString(5), // end_time
						rs.getInt(6), // room
						rs.getInt(7) // capacity
						);
			}
			// Return the slot. Will be null if could not read.
			return slot;

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
			// Close the connections.
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}

		// Will only reach this if coming from the catch block.
		return null;
	}	

	/**
	 * Adds the specified TimetableSlot to the database.
	 * @param timetableSlot - The TimetableSlot object you wish to add.
	 * @return true if the operation succeeded, false otherwise
	 */
	public synchronized boolean addTimetableSlotToDatabase(TimetableSlot timetableSlot) {
		// Check that the parameter is not null.
		if (timetableSlot == null) {
			return false;
		}

		// Check that a session with the specified id exists.
		if (getSession(timetableSlot.getSession_id()) == null) {
			return false;
		}

		// Add to database.
		String sql = "INSERT INTO \"TimetableSlot\"(\"session\", \"date\", \"start_time\", \"end_time\", \"room\", \"capacity\") VALUES (?, ?, ?, ?, ?, ?)";
		Connection con = null;
		PreparedStatement preparedStatement = null;

		try {
			// Get the database connection.
			con = getConnection();
			// Prepare the SQL statement.
			preparedStatement = con.prepareStatement(sql);
			// Add the parameters.
			preparedStatement.setInt(1, timetableSlot.getSession_id());
			preparedStatement.setString(2, timetableSlot.getDateString());
			preparedStatement.setString(3, timetableSlot.getStart_timeString());
			preparedStatement.setString(4, timetableSlot.getEnd_timeString());
			preparedStatement.setInt(5, timetableSlot.getRoom());
			preparedStatement.setInt(6, timetableSlot.getCapacity());
			// Execute the statement and get the result.
			preparedStatement.execute();

			return true;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
			// Close the connections.
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
		return false;
	}
}
