package edu.pitt.cs;

import java.util.ArrayList;
import java.util.List;

class PlayerImpl implements Player {

	// TODO: Add more member variables and methods as needed.
	private List<Item> inventory;
	
	static final String newline = System.lineSeparator(); // Platform independent newline ("\n" or "\r\n")

	public PlayerImpl() {
		this.inventory = new ArrayList<>();
	}

	@Override
	public void addItem(Item item) {
		inventory.add(item);
	}

	@Override
	public boolean hasItem(Item item) {
		return inventory.contains(item);
	}

	@Override
	public String getInventoryString() {
		StringBuilder inventoryString = new StringBuilder();
		
		//check for coffee
		if (hasItem(Item.COFFEE)) {
			inventoryString.append("You have a cup of delicious coffee.").append(newline);
		} else {
			inventoryString.append("YOU HAVE NO COFFEE!").append(newline);
		}
		
		//check for cream
		if (hasItem(Item.CREAM)) {
			inventoryString.append("You have some fresh cream.").append(newline);
		} else {
			inventoryString.append("YOU HAVE NO CREAM!").append(newline);
		}
		
		//check for sugar
		if (hasItem(Item.SUGAR)) {
			inventoryString.append("You have some tasty sugar.").append(newline);
		} else {
			inventoryString.append("YOU HAVE NO SUGAR!").append(newline);
		}
		
		return inventoryString.toString();
	}
}