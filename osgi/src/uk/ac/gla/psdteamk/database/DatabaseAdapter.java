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
	public Connection getConnection() {
		// Attempt to create the connection and return it.
		try {
			Connection conn = DriverManager.getConnection(DB_CONNECTION);
			//System.out.println("getConnection");
			if (!doneInit) {
				System.out.println("initTables");
				initTables(conn);
				doneInit = true;
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
	private void initTables(Connection conn) throws SQLException {
		Statement stmt = conn.createStatement();
		try {
			stmt.executeUpdate(
				"CREATE TABLE \"Registration\" ("
				+ "\"session\" INTEGER NOT NULL, "
				+ "\"student\" VARCHAR(8) NOT NULL, "
				+ "PRIMARY KEY (\"session\", \"student\"))"
			);
		} catch (SQLException e) {
			System.out.println("Table not created: " + e.getMessage());
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
		}
		try {
			stmt.executeUpdate(
				"CREATE TABLE \"Course\" ("
				+ "  \"id\" INTEGER PRIMARY KEY, "
				+ "  \"title\" VARCHAR(128) NOT NULL)"
			);
		} catch (SQLException e) {
			System.out.println("Table not created: " + e.getMessage());
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
		}
		try {
			stmt.executeUpdate(
				"CREATE TABLE \"Session\" ("
				+ "\"id\" INTEGER PRIMARY KEY,"
				+ "\"course\" INTEGER NOT NULL,"
				+ "\"compulsory\" INTEGER DEFAULT 0 NOT NULL,"
				+ "\"frequency\" INTEGER DEFAULT 0 NOT NULL,"
				+ "\"type\" VARCHAR(16)  NOT NULL)"
			);
		} catch (SQLException e) {
			System.out.println("Table not created: " + e.getMessage());
		}
		try {
			stmt.executeUpdate(
				"CREATE TABLE \"TimetableSlots\" ("
				+ "\"id\" INTEGER PRIMARY KEY,"
				+ "\"session\" INTEGER NOT NULL,"
				+ "\"date\" VARCHAR(10),"
				+ "\"start_time\" VARCHAR(8),"
				+ "\"end_time\" VARCHAR(8),"
				+ "\"room\" INTEGER,"
				+ "\"capacity\" INTEGER DEFAULT 0 NOT NULL)"
			);
		} catch (SQLException e) {
			System.out.println("Table not created: " + e.getMessage());
		}
		stmt.close();
	}
	
	public boolean deleteEverything() {
		Connection conn = getConnection();
		if (conn == null) {
			return false;
		}
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM \"Registration\"");
			stmt.executeUpdate("DELETE FROM \"MandatoryCourses\"");
			stmt.executeUpdate("DELETE FROM \"Course\"");
			stmt.executeUpdate("DELETE FROM \"Tutoring\"");
			stmt.executeUpdate("DELETE FROM \"Session\"");
			stmt.executeUpdate("DELETE FROM \"TimetableSlots\"");
			stmt.close();
			conn.close();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	/**
	 * Gets all the courses available in the database.
	 * 
	 * @return the courses
	 */
	public ArrayList<Course> getAllCourses() {
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
	public ArrayList<Session> getAllSessions() {

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
						rs.getString(3), // date
						rs.getString(4), // start_time
						rs.getString(5), // end_time
						rs.getInt(6), // frequency
						rs.getString(7), // room
						rs.getInt(8), // capacity
						rs.getString(9) // time
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
	public Course getCourse(int id) {

		// Retrieve room details from the database
		String query = "SELECT \"title\"  FROM \"Course\" WHERE \"id\" = ?";
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
	public ArrayList<String> getAllStudents() {

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

	public boolean addCourseToDatabase(Course course) {
		// Add to database.
		String sql = "INSERT INTO \"Course\"(\"id\", \"title\") VALUES (?, ?)";
		Connection con = null;
		PreparedStatement preparedStatement = null;

		try {
			// Get the database connection.
			con = getConnection();
			// Prepare the SQL statement.
			preparedStatement = con.prepareStatement(sql);
			// Add the parameters.
			preparedStatement.setInt(1, course.getId());
			preparedStatement.setString(2, course.getTitle());
			// Execute the statement and get the result.
			preparedStatement.execute();
			System.out.println("The course has been successfully imported!");
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

	public boolean addSessionToDatabase(Session session) {
		// Add to database.
		String sql = "INSERT INTO \"Session\"(\"course\", \"date\", \"capacity\", \"type\") VALUES (?, ?, ?, ?)";
		Connection con = null;
		PreparedStatement preparedStatement = null;

		try {
			// Get the database connection.
			con = getConnection();
			// Prepare the SQL statement.
			preparedStatement = con.prepareStatement(sql);
			// Add the parameters.
			preparedStatement.setInt(1, session.getCourse());
			preparedStatement.setString(2, session.getDateString());
			preparedStatement.setInt(3, session.getCapacity());
			preparedStatement.setString(4, session.getType());
			// Execute the statement and get the result.
			preparedStatement.execute();
			System.out.println("The session has been successfully imported!");
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

	public boolean addUserToDatabase(Account account) {
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
			System.out.println("The users have been successfully imported!");
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
