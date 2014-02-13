package menus;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import objects.Account;
import stub_mycampus.MyCampusStub;
import functions.CheckCompulsoryCourses;

public class MainMenu {
	private static final String INVALID_OPTION = "Invalid input. Your options are:\n";
	private static Account account;
	private static BufferedReader reader;

	public static void main(String args[]){
		reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Welcome!\nPlease input your credentials: \n");
		try {
			attemptLogin();
		}
		catch(IOException ex){
			System.out.println(ex.getMessage());
			System.exit(0);
		}

		System.out.println("Welcome " + account.getType() + " " + account.getName() + "!");
		displayCustomMenu();
	}

	private static void attemptLogin() throws IOException{
		while(true){
			System.out.print("Username: ");
			String username = reader.readLine();
			System.out.print("Password: ");
			String password = reader.readLine();
			account = MyCampusStub.authenticate(username, password);
			if (account != null){
				break;
			}
		}
	}

	private static void displayCustomMenu(){
		if (account.getType().equals(Account.TYPE_ADMIN)){
			displayAdminMenu();
		}

		if (account.getType().equals(Account.TYPE_LECTURER)){
			displayLecturerMenu();
		}
		
		if (account.getType().equals(Account.TYPE_TUTOR)){
			displayTutorMenu();
		}

		if (account.getType().equals(Account.TYPE_STUDENT)){
			displayStudentMenu();
		}
	}

	private static void displayAdminMenu(){
		
		while (true) {
			try {
				System.out.println(
						"1. Assign room to timetable slot.\n" +
						"2. Create timetable slot for session.\n" + 
						"q. Quit"
				);
				String option = reader.readLine();
				if (option.equals("1")){
					MenuAssignRoomToTimetableSlot.beginRoomAssignment(reader);
				} else if (option.equals("2")){
					MenuCreateTimetableSlotForSession.beginTimetableAllocation(reader);
				} else if (option.equals("q")){
					break;
				} else {
					System.out.println(INVALID_OPTION);
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
				break;
			}
		}
	}

	private static void displayLecturerMenu(){
		while (true) {
			try {
				System.out.println(
						"1. View session details.\n" +
						"2. Specify session frequency.\n" +
						"3. Add session to course.\n" +
						"4. Import MyCampus courses.\n" +
						"q. Quit"
				);
				String option = reader.readLine();
				if (option.equals("1")){
					MenuCheckSessionDetails.beginSessionDetailsCheck(reader);
				} else if (option.equals("2")){
					MenuSpecifySessionFrequency.beginSpecifyingSessionFrequency(reader);
				} else if (option.equals("3")){
					MenuAddSessionToCourse.beginSessionAdding(reader);
				} else if (option.equals("4")){
					MenuImportMyCampusCourses.startImport(reader);
				} else if (option.equals("q")){
					break;
				} else {
					System.out.println(INVALID_OPTION);
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
				break;
			}
		}
	}
	
	/**
	 * A tutor is also a student so show the student menu as well.
	 */
	private static void displayTutorMenu(){
		while (true) {
			try {
				System.out.println(
						"1. Book timetable slots.\n" +
						"2. Check if fully registered.\n" +
						"q. Quit"
				);
				String option = reader.readLine();
				if (option.equals("1")){
					MenuBookTimetableSlot.beginSessionScheduling(reader, account);
				} else if (option.equals("2")){
					CheckCompulsoryCourses.checkIfFullyRegistered(account);
				} else if (option.equals("q")){
					break;
				} else {
					System.out.println(INVALID_OPTION);
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
				break;
			}
		}
	}
	
	private static void displayStudentMenu(){
		while (true) {
			try {
				System.out.println(
						"1. Book timetable slots.\n" +
						"2. Check if fully registered.\n" +
						"q. Quit"
				);
				String option = reader.readLine();
				if (option.equals("1")){
					MenuBookTimetableSlot.beginSessionScheduling(reader, account);
				} else if (option.equals("2")){
					CheckCompulsoryCourses.checkIfFullyRegistered(account);
				} else if (option.equals("q")){
					break;
				} else {
					System.out.println(INVALID_OPTION);
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
				break;
			}
		}
	}
}