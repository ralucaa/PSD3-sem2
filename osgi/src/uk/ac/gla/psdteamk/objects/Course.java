package uk.ac.gla.psdteamk.objects;

public class Course {
	private int id;
	private String title;
	
	/**
	 * Wrapper class for course details.
	 * @param id - The id of the course.
	 * @param title - The title of the course.
	 */
	public Course(int id, String title){
		this.id = id;
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}
	
	@Override
	public String toString(){
		return getId() + ": " + getTitle();
	}
}
