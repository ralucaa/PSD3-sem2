package uk.ac.gla.psdteamk.objects;

public class Account {
	public static final String TYPE_ADMIN = "admin", TYPE_LECTURER = "lecturer", TYPE_TUTOR = "tutor", TYPE_STUDENT = "student";
	private final String username, password, name, type;
	
	public Account(String username, String password, String name, String type){
		this.username = username;
		this.password = password;
		this.name = name;
		this.type = type;
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
}
