package edu.pitt.cs;

public class RoomImpl implements Room {

	private String furnishing;
	private String adjective;
	private Item item;
	private String northDoor;
	private String southDoor;
	
	static final String newline = System.lineSeparator(); // Platform independent newline ("\n" or "\r\n")

	/**
	 * Constructor. The northDoor or the southDoor can be null if there no doors leading north or south.
	 * 
	 * @param furnishing Furnishing in the room
	 * @param adjective Adjective describing the room
	 * @param item Item present in the room
	 * @param northDoor Description of north door (null if there is no north door)
	 * @param southDoor Description of south door (null if there is no south door)
	 */
	public RoomImpl(String furnishing, String adjective, Item item, String northDoor, String southDoor) {
		// TODO: Fill in
		this.furnishing = furnishing;
		this.adjective = adjective;
		this.item = item;
		this.northDoor = northDoor;
		this.southDoor = southDoor;
	}
	
	public String getFurnishing() {
		// TODO: Fill in
		return furnishing;
	}

	public String getAdjective() {
		// TODO: Fill in
		return adjective;
	}

	public Item getItem() {
		// TODO: Fill in

		return item;
	}

	public String getNorthDoor() {
		// TODO: Fill in
		return northDoor;
	}

	public String getSouthDoor() {
		// TODO: Fill in
		return southDoor;
	}
	
	public String getDescription() {
		// TODO: Fill in
		StringBuilder description = new StringBuilder();
		description.append("You see a " + adjective + " room." + newline);
		description.append("It has a " + furnishing + "." + newline);
		
		if (northDoor != null) {
			description.append("A " + northDoor + " door leads North." + newline);
		}
		if (southDoor != null) {
			description.append("A " + southDoor + " door leads South." + newline);
		}
		
		return description.toString();
	}
}
