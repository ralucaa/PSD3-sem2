package uk.ac.gla.psdteamk.objects;

public class Session {

	private int id;
	private int course;
	private int compulsory;
	private int frequency;
	private String type;

	public Session(int id, int course, int compulsory, int frequency, String type) {
		super();
		this.id = id;
		this.course = course;
		this.compulsory = compulsory;
		this.frequency = frequency;
		this.type = type;
	}
	
	public Session(int course, int compulsory, int frequency, String type) {
		super();
		this.id = -1;
		this.course = course;
		this.compulsory = compulsory;
		this.frequency = frequency;
		this.type = type;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCourse() {
		return course;
	}
	public void setCourse(int course) {
		this.course = course;
	}
	public boolean isCompulsory(){
		return compulsory == 0;
	}
	public int getCompulsory() {
		return compulsory;
	}
	public void setCompulsory(int compulsory) {
		this.compulsory = compulsory;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "Session [id=" + id + ", course=" + course + ", optional="
				+ compulsory + ", frequency=" + frequency + ", type=" + type
				+ "]";
	}
}
