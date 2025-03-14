package edu.pitt.cs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RoomUnitTest {

	static final String newline = System.lineSeparator(); // Platform independent newline ("\n" or "\r\n")

	Room isolatedRoom;
	Room connectedRoom;

	@Before
	public void setup() {
		// 1. Create a "Small" Room with a "Quaint sofa" furnishing, no item, no north door (null), no south door (null), and assign to isolatedRoom.
		// Make sure you use the Room.createInstance method to create the room, or GradeScope autograder will not function properly.
		// TODO: Fill in
		isolatedRoom = Room.createInstance(InstanceType.IMPL, "Quaint sofa", "Small", null, null, null);
		// 2. Create a "Funny" Room with a "Sad record player" furnishing, cream item, "Beige" north door, "Massive" south door, and assign to connectedRoom.
		// Make sure you use the Room.createInstance method to create the room, or GradeScope autograder will not function properly.
		// TODO: Fill in
		connectedRoom = Room.createInstance(InstanceType.IMPL,  "Sad record player", "Funny", Item.CREAM, "Beige", "Massive");
	}

	@After
	public void tearDown() {
		isolatedRoom = null;
		connectedRoom = null;
	}

	/**
	 * Test creating an isolated room with no north or south doors and no item.
	 * 
	 * <pre>
	 * Preconditions: isolatedRoom has been created.
	 * Execution steps: None.
	 * Postconditions: isolatedRoom furnishing is "Quaint sofa"
	 *                 isolatedRoom adjective is "Small"
	 *                 isolatedRoom has no items.
	 *                 isolatedRoom has no north door or south door
	 *                 isolatedRoom has a description of: "You see a Small room." + newline + "It has a Quaint sofa." + newline
	 */
	@Test
	public void testIsolatedRoom() {
		// TODO: Fill in
		assertEquals("Quaint sofa", isolatedRoom.getFurnishing());
		assertEquals("Small", isolatedRoom.getAdjective());
		assertNull(isolatedRoom.getItem());
		assertNull(isolatedRoom.getNorthDoor());
		assertNull(isolatedRoom.getSouthDoor());
		assertEquals("You see a Small room." + newline + "It has a Quaint sofa." + newline, isolatedRoom.getDescription());
	}

	/**
	 * Test creating an connected room with north and south doors and cream inside.
	 * 
	 * <pre>
	 * Preconditions: connectedRoom has been created.
	 * Execution steps: None.
	 * Postconditions: connectedRoom furnishing is "Sad record player"
	 *                 connectedRoom adjective is "Funny"
	 *                 connectedRoom has the cream item inside.
	 *                 connectedRoom has a "Beige" north door.
	 *                 connectedRoom has a "Massive" south door.
	 *                 connectedRoom has a description of: "You see a Funny room." + newline + "It has a Sad record player." + newline +
	 *                    "A Beige door leads North." + newline + "A Massive door leads South." + newline
	 */
	@Test
	public void testConnectedRoom() {
		// TODO: Fill in
		assertEquals("Sad record player", connectedRoom.getFurnishing());
		assertEquals("Funny", connectedRoom.getAdjective());
		assertEquals(Item.CREAM, connectedRoom.getItem());
		assertEquals("Beige", connectedRoom.getNorthDoor());
		assertEquals("Massive", connectedRoom.getSouthDoor());
		assertEquals("You see a Funny room." + newline + "It has a Sad record player." + newline +"A Beige door leads North." + newline + "A Massive door leads South." + newline, connectedRoom.getDescription());
	}

	// TODO: Put in more unit tests of your own making if you need to improve coverage!
	
}