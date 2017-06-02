package textGame;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import items.*;

/**
 * FIXME - Line counting is off if a string covers more than one line
 * TODO - add one use items, like bombs and potions
 * TODO - add leveling, with monster spawns based off of level rather than cell values
 * TODO - build a bigger map
 * TODO - make cells that can be closed off in a certain direction
 * TODO - allow linking of different maps
 * TODO - let player save the game
 * TODO - have npc's you can talk to
 * TODO - add shops
 * TODO - implement durability
 * 
 * @author Brandon Elizondo, edited Colt Rogness
 */
public class Main {
	
	/**
	 * tells whether the world has been initialized yet
	 */
	private static boolean playerIsAlive;
	
	
	/**
	 * the PC, stores stats and items
	 */
	
	private static PlayerCharacter player;
	
	
	/**
	 * the map, tells how many enemies and such there are
	 */
	private static World map;
	
	
	/**
	 * the player's x location on the map
	 */
	private static int locationX;
	
	
	/**
	 * the player's y location on the map
	 */
	private static int locationY;
	
	
	/**
	 * the monsters in the current cell, empty if there are none
	 */
	private static ArrayList<Monster> monsters;
	
	
	/**
	 * this is the window, don't fuck with it
	 */
	static TestFrame test = new TestFrame();
	
	
	/**
	 * runs once to setup JFrame
	 * 
	 * @param args
	 *            - not used
	 */
	public static void main(String[] args) {
		test.setVisible(true);
		playerIsAlive = false;
		writeToMain("What is your name?");
	}
	
	
	/**
	 * updates the player, weapons, armor, monsters, dropped items, and player's pouch displays on the game window
	 */
	private static void updateInfo() {
		test.playerInfo.setText("Defence: " + player.getDefense() + "\nHP: " + player.getCurrentHitPoints() + "\nWallet: $" + player.getMoney());
		test.monsterInfo.setText("");
		if (monsters != null || !monsters.isEmpty()) {
			for (int i = 0; i < monsters.size(); i++) {
				test.monsterInfo.append(monsters.get(i).getTitle() + " Dmg: " + monsters.get(i).getDamage() + " HP: " + monsters.get(i).getCurrentHitPoints() + "\n");
			}
		}
		test.weaponInfo.setText(player.getMeleeWeapon().getName() + ": Dmg: " + player.getMeleeWeapon().getDamage() + "\n" + player.getRangedWeapon().getName() + ": Dmg: " + player.getRangedWeapon().getDamage() + "\n\n");
		for (int i = 0; i < player.getEquippedArmor().length; i++) {
			if (!player.getEquippedArmor()[i].getName().equalsIgnoreCase("")) {
				test.weaponInfo.append(player.getEquippedArmor()[i].getName() + ": Def: " + player.getEquippedArmor()[i].getDefense() + "\n");
			}
		}
		test.droppedItemDisplay.setText("");
		if (map.grid[locationX][locationY].getGold() > 0) {
			test.droppedItemDisplay.append(map.grid[locationX][locationY].getGold() + " gold coins. ");
		}
		ArrayList<Item> items = map.grid[locationX][locationY].getItems();
		if (!items.isEmpty()) {
			for (int i = 0; i < items.size(); i++) {
				if (i != 0) {
					test.droppedItemDisplay.append(", ");
				}
				test.droppedItemDisplay.append(items.get(i).getName());
			}
		}
		test.pouchInfo.setText("");
		if (!player.getPouch().isEmpty()) {
			for (int i = 0; i < player.getPouch().size(); i++) {
				Item item = player.getPouch().get(i);
				if (i != 0) {
					test.pouchInfo.append(", ");
				}
				test.pouchInfo.append(item.getName());
				if (item.getClass().getName().matches("(?i)ITEMS.HELM|ITEMS.CHESTPIECE|ITEMS.GLOVES|ITEMS.LEGGINGS|ITEMS.BOOTS")) {
					test.pouchInfo.append(": Def = " + ((Armor) item).getDefense());
				} // 75 chars fit in the pane
				else if (item.getClass().getName().matches("(?i)ITEMS.MELEE|ITEMS.RANGED")) {
					test.pouchInfo.append(": Dmg = " + ((Weapon) item).getDamage());
				}
			}
		}
	}
	
	
	/**
	 * runs the first time to setup the game, initializes the player, map, location coordinates, gives the player a melee and ranged weapon, a piece of armor,
	 * informs the user of the controls, then outputs initial flavor text
	 * 
	 * @param command
	 *            - the command entered to do things to the game
	 * @throws FileNotFoundException
	 */
	private static void firstRun(String command) throws FileNotFoundException {
		playerIsAlive = true;
		player = new PlayerCharacter(command);
		map = new World("world1-1.txt");
		locationX = map.getStartX();
		locationY = map.getStartY();
		monsters = map.grid[locationX][locationY].getEnemies();
		
		test.playerPane.setText(player.getName());
		test.weaponPane.setText("Weapons & Armor\n");
		test.MonsterPane.setText("Monsters\n");
		
		player.equip(map.getRangedWeapon(1));
		player.equip(map.getMeleeWeapon(1));
		player.equip(map.getArmor(1, ItemType.CHESTPIECE));
		
		writeToMain("Welcome " + player.getName() + ". Say LOOK to look around, LOOT ITEMNAME to pick up any items you see laying around, and EQUIP ITEMNAME to equip. To move around the habitat, use the directional commands NORTH, SOUTH, EAST, and WEST. In the column to the right, the top box contains your stats, the second your weapons and armor, and the third stats for any monsters in the area.");
		writeToMain(map.grid[locationX][locationY].getFlavor());
		
		if (monsters != null && !monsters.isEmpty()) {
			writeToMain("Look Out! There's a monster!");
		}
	}
	
	
	/**
	 * runs every time a command is entered, calls whichever method is relevant
	 * 
	 * @param command
	 *            -the command entered to do things to the game
	 * @throws FileNotFoundException
	 *             - because files
	 */
	public static void gameCommand(String command) throws FileNotFoundException {
		if (playerIsAlive) {
			if (command.equals("KILL")) {
				// hard kill the program
				System.exit(0);
			}
			else if (monsters != null && monsters.size() > 0) {
				// if there are monsters, then you must be in combat and can't do anything other than fight
				attack(command);
			}
			else if (command.length() >= 4 && command.substring(0, 4).equalsIgnoreCase("LOOT") && !map.grid[locationX][locationY].getItems().isEmpty()) {
				command = command.substring(4).trim(); // remove the word loot from the beginning and remove whitespace
				// gives the player whatever item they asked for if it was dropped in the cell
				loot(command);
			}
			else if (command.length() >= 4 && command.substring(0, 4).equalsIgnoreCase("SELL") && !player.getPouch().isEmpty()) {
				command = command.substring(4).trim(); // remove the word sell from the beginning and remove whitespace
				player.sell(command);
			}
			else if (command.length() >= 5 && command.substring(0, 5).equalsIgnoreCase("EQUIP") && !player.getPouch().isEmpty()) {
				command = command.substring(5).trim(); // remove the word equip from the beginning and remove whitespace
				// equips whatever item they asked for if they have it
				player.equip(command);
			}
			else if (command.equalsIgnoreCase("LOOK")) {
				// give the flavor text, same as when you enter an area
				writeToMain(map.grid[locationX][locationY].getFlavor());
			}
			else if (command.equals("Soft Reset")) {
				firstRun(player.getName());
			}
			else if (command.equals("rAre dR0p")) {
				player.equip(map.getRangedWeapon("furry's gaze"));
				player.equip(map.getMeleeWeapon(30));
				player.equip(map.getArmor(7, ItemType.HELM));
				player.equip(map.getArmor(7, ItemType.CHESTPIECE));
				player.equip(map.getArmor(7, ItemType.GLOVES));
				player.equip(map.getArmor(7, ItemType.LEGGINGS));
				player.equip(map.getArmor(7, ItemType.BOOTS));
				
			}
			else {
				// handles movement
				move(command);
			}
		}
		else {
			// only runs when the player is dead and first run;
			firstRun(command);
		}
		// runs every time, updating all the displays
		updateInfo();
	}
	
	
	/**
	 * runs through all items in the cell and gives the player the first one with the given name
	 * 
	 * @param command
	 *            - the given name
	 */
	private static void loot(String command) {
		if (command.equalsIgnoreCase("gold")) {
			player.addMoney(map.grid[locationX][locationY].takeGold());
			return;
		}
		player.addItem(map.grid[locationX][locationY].getItem(command));
	}
	
	
	/**
	 * runs when there is a monster in the area
	 * 
	 * @param command
	 *            -the command entered to do things to the game
	 */
	private static void attack(String command) {
		if (command.equalsIgnoreCase("MELEE")) {
			monsters.get(0).takeDamage(player.getMeleeWeapon().getDamage());
		}
		else if (command.equalsIgnoreCase("RANGED")) {
			monsters.get(0).takeDamage(player.getRangedWeapon().getDamage());
		}
		
		if (!monsters.get(0).isAlive()) {
			ArrayList<Item> items = monsters.get(0).getPouch();
			map.grid[locationX][locationY].addItems(items);
			map.grid[locationX][locationY].addGold(monsters.get(0).getMoney());
			writeToMain("You killed a " + monsters.get(0).getTitle() + "!");
			monsters.remove(0);
		}
		if (!monsters.isEmpty()) {
			for (int i = 0; i < monsters.size(); i++) {
				player.takeDamage(monsters.get(i).getDamage());
			}
		}
		else {
			writeToMain("All monsters in the area are dead.");
		}
		if (!player.isAlive()) {
			playerIsAlive = false;
			writeToMain("Oh no! You died. To start again, enter your name:");
		}
	}
	
	
	/**
	 * runs when there is not a monster in the area
	 * 
	 * @param command
	 *            - the command entered to do things to the game
	 */
	private static void move(String command) {
		boolean moved = false;
		if (command.equalsIgnoreCase("NORTH")) {
			if (locationX > 0) {
				map.grid[locationX][locationY].resetEnemies();
				locationX -= 1;
				moved = true;
			}
		}
		else if (command.equalsIgnoreCase("SOUTH")) {
			if (locationX < map.grid.length - 1) {
				map.grid[locationX][locationY].resetEnemies();
				locationX += 1;
				moved = true;
			}
		}
		else if (command.equalsIgnoreCase("EAST")) {
			if (locationY < map.grid[0].length - 1) {
				map.grid[locationX][locationY].resetEnemies();
				locationY += 1;
				moved = true;
			}
		}
		else if (command.equalsIgnoreCase("WEST")) {
			if (locationY > 0) {
				map.grid[locationX][locationY].resetEnemies();
				locationY -= 1;
				moved = true;
			}
		}
		else {
			monsters = map.grid[locationX][locationY].getEnemies();
			
			if (monsters != null && !monsters.isEmpty()) {
				writeToMain("Look Out! There's a monster!");
			}
			return;
		}
		if (moved) {
			player.heal(1);
			writeToMain(map.grid[locationX][locationY].getFlavor());
		}
		else {
			writeToMain("There is nothing to the " + command);
		}
		
		monsters = map.grid[locationX][locationY].getEnemies();
		
		if (monsters != null && !monsters.isEmpty()) {
			writeToMain("Look Out! There's a monster!");
		}
	}
	
	
	/**
	 * adds the given text to the bottom of the console
	 * 
	 * @param newText
	 *            - whatever needs printed
	 */
	private static void writeToMain(String newText) {
		test.console.setText(test.console.getText() + ">>" + newText + "\n");
	}
	
}
