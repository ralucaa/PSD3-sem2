package uk.ac.gla.psdteamk.mycampus;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import uk.ac.gla.psdteamk.objects.Account;
import uk.ac.gla.psdteamk.objects.Course;
import uk.ac.gla.psdteamk.mycampus.service.MyCampusService;

public class MyCampusStub implements MyCampusService {
	ArrayList<Account> accounts;
	ArrayList<Course> courses;
	
	public MyCampusStub() {
		accounts = new ArrayList<Account>();
		accounts.add(new Account("1111111A","1111111A","Joe Sventek","admin",0));
		accounts.add(new Account("2222222A","2222222A","Tim Storer","lecturer",0));
		accounts.add(new Account("2222222B","2222222B","Jeremy Singer","lecturer",0));
		accounts.add(new Account("3333333A","3333333A","Martynas Buivys","student",3));
		accounts.add(new Account("3333333B","3333333B","Raluca Criste","student",3));
		accounts.add(new Account("3333333C","3333333C","Helen Foster","student",3));
		accounts.add(new Account("3333333D","3333333D","Tomasz Sadowski","student",3));
		accounts.add(new Account("3333333E","3333333E","Vlad Schnakovszki","student",3));
		accounts.add(new Account("2222222C","2222222C","Gethin Norman","lecturer",0));
		accounts.add(new Account("2222222D","2222222D","David Watt","lecturer",0));
		accounts.add(new Account("2222222E","2222222E","Matthew Chalmers","lecturer",0));
		accounts.add(new Account("2222222F","2222222F","Stephen Brewster","lecturer",0));
		accounts.add(new Account("2222222G","2222222G","Leif Azzopardi","lecturer",0));
		accounts.add(new Account("2222222H","2222222H","Colin Perkins","lecturer",0));
		accounts.add(new Account("2222222I","2222222I","Wim Vanderbauwhede","lecturer",0));
		accounts.add(new Account("2222222J","2222222J","Iadh Ounis","lecturer",0));
		
		courses = new ArrayList<Course>();
		courses.add(new Course(1,"Professional Software Development"));
		courses.add(new Course(2,"Algorithmics"));
		courses.add(new Course(3,"Advanced Programming"));
		courses.add(new Course(4,"Programming Languages"));
		courses.add(new Course(5,"Interactive Systems"));
		courses.add(new Course(6,"Distributed Information Management"));
		courses.add(new Course(7,"Network Systems"));
		courses.add(new Course(8,"Operating Systems"));
		courses.add(new Course(9,"Database Systems"));
	}
	
	/**
	 * Authenticates the user through MyCampus' single sign-on service.
	 * THIS IS A STUB! Will validate if username and password match and the user exists in the database.
	 * @param username - The username.
	 * @param password - The password.
	 * @return an Account object that contains the user's details or null if invalid credentials
	 */
	public Account authenticate(String username, String password) {
		for (Account account : accounts) {
			if (account.getUsername().equals(username) && account.checkPassword(password)) {
				return account;
			}
		}
		return null;
	}
	
	/**
	 * Gets all the courses available in MyCampus.
	 * @return the courses
	 */
	public ArrayList<Course> getAllCourses(){
		return new ArrayList<Course>(courses);
	}
}
