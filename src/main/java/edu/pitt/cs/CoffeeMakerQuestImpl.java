package edu.pitt.cs;

import java.util.ArrayList;
import java.util.HashSet;

public class CoffeeMakerQuestImpl implements CoffeeMakerQuest {

	static final String newline = System.lineSeparator(); // Platform independent newline ("\n" or "\r\n")

	// TODO: Add more member variables and methods as needed.
	private Player player;
	private ArrayList<Room> rooms;
	private int currentRoomIndex;
	private boolean gameOver;


	/**
	 * Constructor. Rooms are laid out from south to north, such that the
	 * first room in rooms becomes the southernmost room and the last room becomes
	 * the northernmost room. Initially, the player is at the southernmost room.
	 * 
	 * @param player Player for this game
	 * @param rooms  List of rooms in this game
	 */
	CoffeeMakerQuestImpl(Player player, ArrayList<Room> rooms) {
		// TODO: Fill in
		this.player = player;
		this.rooms = rooms;
		this.currentRoomIndex = 0;
	}

	/**
	 * Whether the game is over. The game ends when the player drinks the coffee.
	 * 
	 * @return true if the game is over, false otherwise
	 */
	public boolean isGameOver() {
		// TODO: Fill in
		if(gameOver==true){
			return true;
		}
		return false;
	}

	/**
	 * The method returns success if and only if: 1) Th southernmost room has a
	 * north door only, 2) The northernmost room has a south door only, and 3) The
	 * rooms in the middle have both north and south doors. If there is only one
	 * room, there should be no doors.
	 * 
	 * @return true if check successful, false otherwise
	 */
	public boolean areDoorsPlacedCorrectly() {
		// TODO: Fill in
		if (rooms.isEmpty()) return false;

        for (int i = 0; i < rooms.size(); i++) {
            Room room = rooms.get(i);
            if (i == 0) {
                //southernmost room should only have a north door
                if (room.getSouthDoor() != null || room.getNorthDoor() == null) return false;
            } else if (i == rooms.size() - 1) {
                //northernmost room should only have a south door
                if (room.getNorthDoor() != null || room.getSouthDoor() == null) return false;
            } else {
                //middle room should have both doors
                if (room.getNorthDoor() == null || room.getSouthDoor() == null) return false;
            }
        }
        return true;
	}

	/**
	 * Checks whether each room has a unique adjective and furnishing.
	 * 
	 * @return true if check successful, false otherwise
	 */

	public boolean areRoomsUnique() {
		// TODO: Fill in
		//HashSet for no duplicate
		HashSet<String> adjectives = new HashSet<>();
        HashSet<String> furnishings = new HashSet<>();

		//check for duplicate
        for (Room room : rooms) {
            if (!adjectives.add(room.getAdjective()) || !furnishings.add(room.getFurnishing())) {
                return false; 
            }
        }
        return true;
	}

	/**
	 * Returns the room the player is currently in. If location of player has not
	 * yet been initialized with setCurrentRoom, returns null.
	 * 
	 * @return room player is in, or null if not yet initialized
	 */
	public Room getCurrentRoom() {
		// TODO: Fill in
		//not initialized
		if(rooms.isEmpty()){
			return null;
		}
		return rooms.get(currentRoomIndex);
	}

	/**
	 * Set the current location of the player. If room does not exist in the game,
	 * then the location of the player does not change and false is returned.
	 * 
	 * @param room the room to set as the player location
	 * @return true if successful, false otherwise
	 */
	public boolean setCurrentRoom(Room room) {
		// TODO: Fill in
		int index = rooms.indexOf(room);
		if(index != -1){
			currentRoomIndex = index;
			return true;
		}
		return false;
	}

	/**
	 * Get the instructions string command prompt. It returns the following prompt:
	 * " INSTRUCTIONS (N,S,L,I,D,H) > ".
	 * 
	 * @return comamnd prompt string
	 */
	public String getInstructionsString() {
		// TODO: Fill in
		return " INSTRUCTIONS (N,S,L,I,D,H) > " + newline;
	}

	/**
	 * A helper method for the "H" command. It returns the following help string:
	 * "N - Go north" + newline + "S - Go south" + newline + "L - Look and collect
	 * any items in the room" + newline +
	 * "I - Show inventory of items collected" + newline + "D - Drink coffee made
	 * from items in inventory" + newline.
	 * 
	 * @return help string
	 */
	private String getHelpString() {
		// TODO: Fill in
		return "N - Go north" + newline + 
			   "S - Go south" + newline + 
			   "L - Look and collect any items in the room" + newline +
			   "I - Show inventory of items collected" + newline + 
			   "D - Drink coffee made from items in inventory" + newline;
	}

	/**
	 * Processes the user command given in String cmd and returns the response
	 * string. For the list of commands, please try giving the "H" command in the
	 * solution jar. Make sure you use Player.getInventoryString() whenever you need
	 * to display the inventory.
	 * 
	 * @param cmd the user command
	 * @return response string for the command
	 */
	public String processCommand(String cmd) {
		// TODO: Fill in
		cmd = cmd.toUpperCase();

		switch (cmd) {
            case "N":
                if (currentRoomIndex < rooms.size() - 1) {
                    currentRoomIndex++;
					return "";
                } else {
                    return "A door in that direction does not exist." + newline;
                }
            case "S":
                if (currentRoomIndex > 0) {
                    currentRoomIndex--;
					return "";
                } else {
                    return "A door in that direction does not exist." + newline;
                }
            case "L":
                Room currentRoom = getCurrentRoom();
                if (currentRoom != null && currentRoom.getItem() != null) {
                    player.addItem(currentRoom.getItem());
                    if(currentRoom.getItem()==Item.COFFEE){
						return "There might be something here..." + newline + "You found some caffeinated coffee!" + newline;
					}
					else if(currentRoom.getItem()==Item.CREAM){
						return "There might be something here..." + newline + "You found some creamy cream!" + newline;
					}
					else{
						return "There might be something here..." + newline + "You found some sweet sugar!" + newline;
					}
                } else {
                    return "You don't see anything out of the ordinary.";
                }
            case "I":
                return player.getInventoryString();
            case "D":
				gameOver = true;
                if (player.hasItem(Item.COFFEE)&&player.hasItem(Item.CREAM)&&player.hasItem(Item.SUGAR)) {
                    return player.getInventoryString() + newline +"You drink the beverage and are ready to study!" + newline + "You win!" + newline;
                } else if(player.hasItem(Item.COFFEE) || player.hasItem(Item.CREAM) || player.hasItem(Item.SUGAR)){
                    return player.getInventoryString() + newline + "You refuse to drink this half-made sludge. You cannot study." + newline + "You lose!" + newline;
                }
				else{
					return player.getInventoryString() + newline + "You drink thin air and can only dream of coffee. You cannot study." + newline + "You lose!" + newline;
				}
            case "H":
                return getHelpString();
            default:
                return "What?";
        }
	}

}
