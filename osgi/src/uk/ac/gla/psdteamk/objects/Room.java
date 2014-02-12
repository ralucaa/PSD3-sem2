package uk.ac.gla.psdteamk.objects;

public class Room {
	
	@Override
	public String toString() {
		return id + ": building=" + building + ", room=" + room;
	}
	private int id;
	private String building;
	private String room; // i.e. F161
	
	public Room(int id, String building, String room) {
		this.id = id;
		this.building = building;
		this.room = room;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
}
