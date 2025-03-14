package edu.pitt.cs;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.runners.MethodSorters;
import org.mockito.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Method;
import java.util.ArrayList;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CoffeeMakerQuestUnitTest {

	static final String newline = System.lineSeparator(); // Platform independent newline ("\n" or "\r\n")

	CoffeeMakerQuest cmq;
	Player player;
	ArrayList<Room> rooms;

	@Before
	public void setup() {
		// 1. Create a Player with no items (no coffee, no cream, no sugar) and assign to player.
		// Make sure you use the Player.createInstance method to create the player, or GradeScope autograder will not function properly.
		// TODO: Fill in
		player = Player.createInstance(InstanceType.IMPL);

		// 2. Create 6 rooms exactly as specified in rooms.config and add to rooms list.
		// You are expected to hard-code the room configurations. The test cases in this
		// class depend on those hard-coded values, so it would be pointless to allow
		// them to be changed.
		// Make sure you use the Room.createInstance method to create the rooms, or GradeScope autograder will not function properly.
		// TODO: Fill in
		rooms = new ArrayList<>();
		rooms.add(Room.createInstance(InstanceType.MOCK, "Quaint sofa", "Small", Item.CREAM, "Magenta", null));
		rooms.add(Room.createInstance(InstanceType.MOCK, "Sad record player", "Funny", null, "Beige", "Massive"));
		rooms.add(Room.createInstance(InstanceType.MOCK, "Tight pizza", "Refinanced", Item.COFFEE, "Dead", "Smart"));
		rooms.add(Room.createInstance(InstanceType.MOCK, "Flat energy drink", "Dumb", null, "Vivacious", "Slim"));
		rooms.add(Room.createInstance(InstanceType.MOCK, "Beautiful bag of money",  "Bloodthirsty", null, "Purple", "Sandy"));
		rooms.add(Room.createInstance(InstanceType.MOCK, "Perfect air hockey table", "Rough", Item.SUGAR, null, "Minimalist"));


		// 3. Create a CoffeeMakerQuest object using player and rooms and assign to cmq.
		// Make sure you use the CoffeeMakerQuest.createInstance method to create cmq, or GradeScope autograder will not function properly.
		// TODO: Fill in
		cmq = CoffeeMakerQuest.createInstance(InstanceType.IMPL, player, rooms);
	}

	@After
	public void tearDown() {
		player = null;
		rooms = null;
		cmq = null;
	}

	/**
	 * Test case for String getHelpString().
	 * 
	 * <pre>
	 * Preconditions: Player, rooms, and cmq test fixture has been created.
	 * Execution steps: Call cmq.getHelpString().
	 * Postconditions: Return value is:
	 *                 "N - Go north" + newline + "S - Go south" + newline + "L - Look and collect any items in the room" + newline +
	 *                 "I - Show inventory of items collected" + newline + "D - Drink coffee made from items in inventory" + newline.
	 * </pre>
	 */
	@Test
	public void testGetHelpString() throws Exception {
		// TODO: Fill in

		//access the private getHelpString method using Java reflection
		Method getHelpStringMethod = cmq.getClass().getDeclaredMethod("getHelpString");
		getHelpStringMethod.setAccessible(true); 
	
		String result = (String) getHelpStringMethod.invoke(cmq);

		String expected = "N - Go north" + newline + 
						  "S - Go south" + newline + 
						  "L - Look and collect any items in the room" + newline + 
						  "I - Show inventory of items collected" + newline + 
						  "D - Drink coffee made from items in inventory" + newline;
	 
		assertEquals(expected, result);
	}

	/**
	 * Test case for Room getCurrentRoom().
	 * 
	 * <pre>
	 * Preconditions: Player, rooms, and cmq test fixture has been created.
	 * Execution steps: Call cmq.getCurrentRoom().
	 * Postconditions: Return value is rooms.get(0).
	 * </pre>
	 */
	@Test
	public void testGetCurrentRoom() {
		// TODO: Fill in 
		Room ret = cmq.getCurrentRoom();

		assertEquals(rooms.get(0), ret);
	}

	/**
	 * Test case for void setCurrentRoom(Room room) and Room getCurrentRoom().
	 * 
	 * <pre>
	 * Preconditions: Player, rooms, and cmq test fixture has been created.
	 * Execution steps: Call cmq.setCurrentRoom(rooms.get(2)).
	 *                  Call cmq.getCurrentRoom().
	 * Postconditions: Return value of cmq.setCurrentRoom(rooms.get(2)) is true. 
	 *                 Return value of cmq.getCurrentRoom() is rooms.get(2).
	 * </pre>
	 */
	@Test
	public void testSetCurrentRoom() {
		// TODO: Fill in 
		boolean ret1 = cmq.setCurrentRoom(rooms.get(2));
		Room ret2 = cmq.getCurrentRoom();

		assertTrue(ret1);
		assertEquals(rooms.get(2), ret2);
	}

	/**
	 * Test case for boolean areDoorsPlacedCorrectly() when check succeeds.
	 * 
	 * <pre>
	 * Preconditions: Player, rooms, and cmq test fixture has been created.
	 * Execution steps: Call cmq.areDoorsPlacedCorrectly().
	 * Postconditions: Return value of cmq.areDoorsPlacedCorrectly() is true.
	 * </pre>
	 */
	@Test
	public void testAreDoorsPlacedCorrectly() {
		// TODO: Fill in 
		boolean ret = cmq.areDoorsPlacedCorrectly();

		assertTrue(ret);
	}

	/**
	 * Test case for boolean areDoorsPlacedCorrectly() when check fails.
	 * 
	 * <pre>
	 * Preconditions: Player, rooms, and cmq test fixture has been created.
	 *                rooms.get(3) is modified so that it has no south door.
	 * Execution steps: Call cmq.areDoorsPlacedCorrectly().
	 * Postconditions: Return value of cmq.areDoorsPlacedCorrectly() is false.
	 * </pre>
	 */
	@Test
	public void testAreDoorsPlacedCorrectlyMissingSouthDoor() {
		// TODO: Fill in 
		Room setRoom3 = Room.createInstance(InstanceType.MOCK, "Tight pizza", "Refinanced", Item.COFFEE, "Dead", null);
		rooms.set(3, setRoom3);

		boolean ret = cmq.areDoorsPlacedCorrectly();

		assertFalse(ret);
	}

	/**
	 * Test case for boolean areRoomsUnique() when check fails.
	 * 
	 * <pre>
	 * Preconditions: Player, rooms, and cmq test fixture has been created.
	 *                rooms.get(2) is modified so that its adjective is modified to "Small".
	 * Execution steps: Call cmq.areRoomsUnique().
	 * Postconditions: Return value of cmq.areRoomsUnique() is false.
	 * </pre>
	 */
	@Test
	public void testAreRoomsUniqueDuplicateAdjective() {
		// TODO: Fill in 
		Room setRoom2 = Room.createInstance(InstanceType.MOCK, "Sad record player", "Small", null, "Beige", "Massive");
		rooms.set(2,setRoom2);

		boolean ret = cmq.areRoomsUnique();

		assertFalse(ret);
	}	

	/**
	 * Test case for String processCommand("I").
	 * 
	 * <pre>
	 * Preconditions: Player, rooms, and cmq test fixture has been created.
	 * Execution steps: Call cmq.processCommand("I").
	 * Postconditions: Return value is: "YOU HAVE NO COFFEE!" + newline + "YOU HAVE NO CREAM!" + newline + "YOU HAVE NO SUGAR!" + newline.
	 * </pre>
	 */
	@Test
	public void testProcessCommandI() {
		// TODO: Fill in 
		String commandRet = cmq.processCommand("I");

		assertEquals("YOU HAVE NO COFFEE!" + newline + "YOU HAVE NO CREAM!" + newline + "YOU HAVE NO SUGAR!" + newline, commandRet);
	}

	/**
	 * Test case for String processCommand("l").
	 * 
	 * <pre>
	 * Preconditions: Player, rooms, and cmq test fixture has been created.
	 * Execution steps: Call cmq.processCommand("l").
	 * Postconditions: Return value is: "There might be something here..." + newline + "You found some creamy cream!" + newline.
	 *                 Cream is added to player.
	 * </pre>
	 */
	@Test
	public void testProcessCommandLCream() {
		// TODO: Fill in 
		String commandRet = cmq.processCommand("L");

		assertEquals("There might be something here..." + newline + "You found some creamy cream!" + newline, commandRet);
		assertTrue(player.hasItem(Item.CREAM));

	}

	/**
	 * Test case for String processCommand("n").
	 * 
	 * <pre>
	 * Preconditions: Player, rooms, and cmq test fixture has been created.
	 *                cmq.setCurrentRoom(rooms.get(3)) has been called.
	 * Execution steps: Call cmq.processCommand("n").
	 *                  Call cmq.getCurrentRoom().
	 * Postconditions: Return value of cmq.processCommand("n") is "".
	 *                 Return value of cmq.getCurrentRoom() is rooms.get(4).
	 * </pre>
	 */
	@Test
	public void testProcessCommandN() {
		// TODO: Fill in
		cmq.setCurrentRoom(rooms.get(3));

		String commandRet = cmq.processCommand("N");
		Room currentRoom = cmq.getCurrentRoom();

		assertEquals("", commandRet);
		assertEquals(rooms.get(4), currentRoom);
	}

	/**
	 * Test case for String processCommand("s").
	 * 
	 * <pre>
	 * Preconditions: Player, rooms, and cmq test fixture has been created.
	 * Execution steps: Call cmq.processCommand("s").
	 *                  Call cmq.getCurrentRoom().
	 * Postconditions: Return value of cmq.processCommand("s") is: "A door in that direction does not exist." + newline.
	 *                 Return value of cmq.getCurrentRoom() is rooms.get(0).
	 * </pre>
	 */
	@Test
	public void testProcessCommandS() {
		// TODO: Fill in
		String commandRet = cmq.processCommand("S");
		Room currentRoom = cmq.getCurrentRoom();

		assertEquals("A door in that direction does not exist."+ newline, commandRet);
		assertEquals(rooms.get(0), currentRoom);
	}

	/**
	 * Test case for String processCommand("D").
	 * 
	 * <pre>
	 * Preconditions: Player, rooms, and cmq test fixture has been created.
	 * Execution steps: Call cmq.processCommand("D").
	 *                  Call cmq.isGameOver().
	 * Postconditions: Return value of cmq.processCommand("D") is: "YOU HAVE NO COFFEE!" + newline + "YOU HAVE NO CREAM!" + newline + 
	 *                    "YOU HAVE NO SUGAR!" + newline + newline + "You drink thin air and can only dream of coffee. You cannot study." + newline + "You lose!" + newline.
	 *                 Return value of cmq.isGameOver() is true.
	 * </pre>
	 */
	@Test
	public void testProcessCommandDLose() {
		// TODO: Fill in
		String commandRet = cmq.processCommand("D");
		boolean gameRet = cmq.isGameOver();

		assertEquals("YOU HAVE NO COFFEE!" + newline + "YOU HAVE NO CREAM!" + newline + "YOU HAVE NO SUGAR!" + newline + newline + "You drink thin air and can only dream of coffee. You cannot study." + newline + "You lose!" + newline, commandRet);
		assertTrue(gameRet);
	}

	/**
	 * Test case for String processCommand("D").
	 * 
	 * <pre>
	 * Preconditions: Player, rooms, and cmq test fixture has been created.
	 *                Player has all 3 items (coffee, cream, sugar).
	 * Execution steps: Call cmq.processCommand("D").
	 *                  Call cmq.isGameOver().
	 * Postconditions: Return value of cmq.processCommand("D") is: "You have a cup of delicious coffee." + newline + "You have some fresh cream." + newline + 
	 *                    "You have some tasty sugar." + newline + newline + "You drink the beverage and are ready to study!" + newline + "You win!" + newline.
	 *                 Return value of cmq.isGameOver() is true.
	 * </pre>
	 */
	@Test
	public void testProcessCommandDWin() {
		// TODO: Fill in
		player.addItem(Item.COFFEE);
		player.addItem(Item.CREAM);
		player.addItem(Item.SUGAR);

		String commandRet = cmq.processCommand("D");
		boolean gameRet = cmq.isGameOver();

		assertEquals("You have a cup of delicious coffee." + newline + "You have some fresh cream." + newline + "You have some tasty sugar." + newline + newline + "You drink the beverage and are ready to study!" + newline + "You win!" + newline, commandRet);
		assertTrue(gameRet);
	}

	// TODO: Put in more unit tests of your own making if you need to improve coverage!

}