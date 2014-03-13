package uk.ac.gla.psdteamk.objects;

import java.util.Random;
import java.util.Date;

public class Account {
	public static final String TYPE_ADMIN = "admin", TYPE_LECTURER = "lecturer", TYPE_TUTOR = "tutor", TYPE_STUDENT = "student";
	private final String username, password, name, type;
	private final int token;
	private final Date loginTime;
	private int year;
	
	public Account(String username, String password, String name, String type, int year){
		this.username = username;
		this.password = password;
		this.name = name;
		this.type = type;
		this.token = new Random().nextInt(Integer.MAX_VALUE);
		this.loginTime = new Date();
		this.year = year;
	}
	
	public String getUsername(){
		return username;
	}
	
	public boolean checkPassword(String password){
		return this.password.equals(password);
	}
	
	public String getName(){
		return name;
	}
	
	public String getType(){
		return type;
	}
	
	public int getToken() {
		return token;
	}
	
	public Date getLoginTime() {
		return loginTime;
	}

	public int getYear() {
		return year;
	}
}
