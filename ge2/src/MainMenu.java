import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import objects.Account;
import stub_mycampus.MyCampusStub;
import functions.AssignRoomToTimetableSlot;
import functions.BookTimetableSlot;
import functions.CheckCompulsoryCourses;
import functions.CreateTimetableSlotForSession;
import functions.ImportMyCampusCourses;
import functions.SpecifySessionFrequency;

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
					AssignRoomToTimetableSlot.allocator(reader);
				} else if (option.equals("2")){
					CreateTimetableSlotForSession.startCreate(reader);
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
					//TODO Tomasz
				} else if (option.equals("2")){
					SpecifySessionFrequency.scheduler(reader);
				} else if (option.equals("3")){
					//TODO Helen
				} else if (option.equals("4")){
					ImportMyCampusCourses.startImport(reader);
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
	
	private static void displayTutorMenu(){
		while (true) {
			try {
				System.out.println(
						"No options available!\n" +
						"q. Quit"
				);
				String option = reader.readLine();
				if (option.equals("q")){
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
					BookTimetableSlot.scheduler(reader, account);
				} else if (option.equals("2")){
					CheckCompulsoryCourses.CheckIfFullyRegistered(account);
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
